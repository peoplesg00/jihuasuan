package com.atli.provider.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.img.ImgUtil;
import com.atli.provider.service.UserService;
import entity.Const;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import response.ResultEnum;
import response.ResultText;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * (User)表控制层
 *
 * @author makejava
 * @CrossOrigin(allowCredentials = "true", allowedHeaders = "*") 表示可以跨域
 * @RefreshScope 只需要在需要动态读取配置的类上添加此注解就可以
 * @since 2020-06-17 13:20:26
 */
@RestController
@RequestMapping(value = "/user")
@Slf4j
@RefreshScope
public class UserController {
    /**
     * 服务对象
     */
    private final UserService userService;

    private final RedisTemplate<Object, Object> redisTemplate;

    private final HttpServletResponse response;

    private final HttpServletRequest request;

    private final RabbitTemplate rabbitTemplate;

    public UserController(UserService userService, RedisTemplate<Object, Object> redisTemplate,
                          HttpServletResponse response, HttpServletRequest request,
                          RabbitTemplate rabbitTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
        this.response = response;
        this.request = request;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping(value = "/selectOne/{id}")
    public User selectOne(@PathVariable Long id) {
        return this.userService.queryById(id);
    }

    /**
     * 登录
     *
     * @param user 用户
     * @return 登录状态
     */
    @PostMapping(value = "/provider/login")
    public ResultText login(@RequestBody User user) throws UnsupportedEncodingException {
        rabbitTemplate.convertAndSend("account", "user.login", "用户开始登录");
        return userService.login(user.getUsername(), user.getPassword());
    }

    /**
     * 获取验证码
     *
     * @return 获取验证码状态
     */
    @GetMapping(value = "/provider/code")
    public ResultText code() {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(80, 30, 5, 4);
        String code = captcha.getCode();
        log.info("验证码生成成功：" + code);
        File file = new File("D:\\java程序设计\\jihuasuan\\api-commons\\src\\main\\resources\\static\\img\\one.png");
        if (!file.exists()) {
            file.mkdir();
        }
        ImgUtil.write(captcha.getImage(), file);
        Cookie cookie = new Cookie(Const.CODE, code);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 3);
        response.addCookie(cookie);
        rabbitTemplate.convertAndSend("account", "user.code", "验证码生成：" + code);
        return ResultText.success(ResultEnum.CREATE_IMG, code);
    }

    /**
     * 退出登录
     *
     * @return 退出状态
     */
    @GetMapping(value = "/provider/loginOut")
    public ResultText loginOut(@NotNull String username) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            log.info("缓存失效》》》");
            redisTemplate.delete(Const.TOKEN + username);
            return ResultText.success(ResultEnum.OUT);
        }
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), Const.LOGIN) ||
                    Objects.equals(cookie.getName(), Const.GRADE) ||
                    Objects.equals(cookie.getName(), Const.CODE)) {
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                redisTemplate.delete(Const.TOKEN + username);
                log.info("缓存失效》》》cookie:" + cookie.getName() + ":" + cookie.getValue());
            }
        }
        rabbitTemplate.convertAndSend("account", "user.loginOut", "用户退出登录");
        return ResultText.success(ResultEnum.OUT);
    }

    /**
     * 通过用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    @PostMapping(value = "/provider/selectByUsername")
    public ResultText selectByUsername(String username) {
        return userService.selectByUserName(username);
    }

    /**
     * 修改密码
     *
     * @param password 新的密码
     * @return 修改状态
     */
    @PostMapping(value = "/provider/resetPassword")
    public ResultText resetPassword(String password, String answer) {
        rabbitTemplate.convertAndSend("account", "user.resetPassword", "用户开始修改密码");
        return userService.resetPassword(password, answer);
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 注册状态
     */
    @PostMapping(value = "/provider/register")
    public ResultText register(@RequestBody User user) {
        rabbitTemplate.convertAndSend("account", "user.register", "用户开始注册");
        return userService.insert(user);
    }

    /**
     * 查询
     *
     * @param offset 起始位置
     * @param limit  数据条数
     * @return 对象列表
     */
    @GetMapping(value = "/provider/queryAllByLimit")
    public ResultText queryAllByLimit(int offset, int limit) {
        return this.userService.queryAllByLimit(offset, limit);
    }

    /**
     * 升降级操作
     *
     * @param grade 级别
     * @param id    主键
     * @return 升级状态
     */
    @PostMapping(value = "/provider/upgrade")
    public ResultText upgrade(int grade, int id) {
        return this.userService.upgrade(grade, id);
    }

    /**
     * 删除用户
     *
     * @param id 主键
     * @return 删除状态
     */
    @GetMapping(value = "/provider/deleteById")
    public ResultText deleteById(Long id) {
        rabbitTemplate.convertAndSend("account", "user.deleteById", "用户id为" + id + "被删除");
        return this.userService.deleteById(id);
    }
}