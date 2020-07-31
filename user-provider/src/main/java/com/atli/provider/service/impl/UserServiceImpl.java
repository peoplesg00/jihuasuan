package com.atli.provider.service.impl;

import com.atli.provider.dao.UserDao;
import com.atli.provider.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Const;
import entity.Token;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import response.ResultEnum;
import response.ResultText;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-06-17 13:20:25
 */
@Service("userService")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RedisTemplate<Object, Object> redisTemplate;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    public UserServiceImpl(UserDao userDao, RedisTemplate<Object, Object> redisTemplate,
                           HttpServletRequest request, HttpServletResponse response) {
        this.userDao = userDao;
        this.redisTemplate = redisTemplate;
        this.request = request;
        this.response = response;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }

    /**
     * 通过用户名查询
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public ResultText selectByUserName(@NotNull String userName) {
        if (userName.isEmpty()) {
            return ResultText.error(ResultEnum.NOT_DATA);
        }
        User user = userDao.selectByUserName(userName);
        if (user == null) {
            return ResultText.error(ResultEnum.USER_NOT_EXISTS);
        }
        String id = UUID.randomUUID().toString();
        Token token = new Token(id, user);
        Cookie cookie = new Cookie(Const.FORGET, id);
        cookie.setMaxAge(60 * 3);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        redisTemplate.opsForValue().set(Const.FORGET, token, 3, TimeUnit.MINUTES);
        return ResultText.success(ResultEnum.SELECT_SUCCESS, user);
    }

    /**
     * 修改密码
     *
     * @param password 新的密码
     * @param answer   密保答案
     * @return 修改状态
     */
    @Override
    public ResultText resetPassword(@NotNull String password, String answer) {
        if (password.isEmpty()) {
            return ResultText.error(ResultEnum.NOT_DATA);
        }
        Token token = (Token) redisTemplate.opsForValue().get(Const.FORGET);
        Cookie[] cookies = request.getCookies();
        if (token == null ||
                cookies == null ||
                !Objects.equals(token.getUser().getAnswer(), DigestUtils.md5DigestAsHex(answer.getBytes()))) {
            return ResultText.error(ResultEnum.REST_PASSWORD_FAIL);
        }
        for (Cookie cookie : cookies) {
            if (token.getId().equals(cookie.getValue())) {
                int num = userDao.resetPassword(token.getUser().getId(), DigestUtils.md5DigestAsHex(password.getBytes()));
                if (num > 0) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return ResultText.success(ResultEnum.REST_PASSWORD_SUCCESS);
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录状态
     */
    @Override
    public ResultText login(@NotNull String username, String password) throws UnsupportedEncodingException {
        if (username.isEmpty() || password.isEmpty()) {
            return ResultText.error(ResultEnum.NOT_DATA);
        }
        User user = userDao.login(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user == null) {
            return ResultText.error(ResultEnum.LOGIN_FAIL);
        }
        user.setPassword(null);
        String id = username;
        Token token = new Token(id, user);
        Cookie cookieForUser = new Cookie(Const.LOGIN, username);
        Cookie cookieForGrade = new Cookie(Const.GRADE, user.getGrade().toString());
        cookieForUser.setPath("/");
        cookieForGrade.setPath("/");
        cookieForUser.setMaxAge(60 * 30);
        cookieForGrade.setMaxAge(60 * 30);
        response.addCookie(cookieForUser);
        response.addCookie(cookieForGrade);
        redisTemplate.opsForValue().set(Const.TOKEN + username, token, 30, TimeUnit.MINUTES);
        if (user.getGrade().equals(Const.ORDINARY)) {
            return ResultText.success(ResultEnum.LOGIN_SUCCESS, Const.ORDINARY);
        }
        return ResultText.success(ResultEnum.LOGIN_SUCCESS, Const.ADMIN);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public ResultText queryAllByLimit(int offset, int limit) {
        if (offset == 0 || limit < 0) {
            return ResultText.error(ResultEnum.DATA_FAIL);
        }
        PageHelper.startPage(offset, limit);
        List<User> userList = this.userDao.queryAllByLimit();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return userList == null ?
                ResultText.error(ResultEnum.NOT_DATA) : ResultText.success(ResultEnum.SELECT_SUCCESS, pageInfo);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public ResultText insert(User user) {
        if (user == null) {
            return ResultText.error(ResultEnum.NOT_DATA);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setAnswer(DigestUtils.md5DigestAsHex(user.getAnswer().getBytes()));
        user.setGrade(0);
        int num = this.userDao.insert(user);
        if (num > 0) {
            return ResultText.success(ResultEnum.REGISTER_SUCCESS);
        }
        return ResultText.error(ResultEnum.REGISTER_FAIL);
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public ResultText deleteById(Long id) {
        int num = this.userDao.deleteById(id);
        return num > 0 ?
                ResultText.success(ResultEnum.DELETE_SUCCESS) : ResultText.error(ResultEnum.DELETE_FAIL);
    }

    /**
     * 升降级操作
     *
     * @param grade 级别
     * @param id    主键
     * @return 升级状态
     */
    @Override
    public ResultText upgrade(int grade, int id) {
        if (id < Const.ADMIN) {
            return ResultText.error(ResultEnum.DATA_FAIL);
        }
        int num = this.userDao.upgrade(grade, id);
        return num > 0 ?
                ResultText.success(ResultEnum.UPDATE_SUCCESS) : ResultText.error(ResultEnum.UPDATE_FAIL);
    }
}