package com.atli.consumer.controller;

import com.atli.consumer.service.UserService;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LI
 * @Date: 2020/5/27 17:07
 * @Description: @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
 */
@RestController
@Slf4j
@RefreshScope
@RabbitListener(queues = "provider_user")
public class UserConsumerController {


    private final UserService userService;


    public UserConsumerController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping(value = "/consumer/queryById/{id}")
    public Object queryById(@PathVariable("id") Long id) {
        return userService.selectOne(id);
    }

    /**
     * 登录
     *
     * @param user 用户
     * @return 登录状态
     */
    @PostMapping(value = "/consumer/login")
    public Object login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * 获取验证码
     *
     * @return 获取验证码状态
     */
    @GetMapping(value = "/consumer/code")
    public Object code() {
        return userService.code();
    }

    /**
     * 退出登录
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/consumer/loginOut")
    public Object loginOut(@RequestParam("username") String username) {
        return userService.loginOut(username);
    }

    /**
     * 通过用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    @PostMapping(value = "/consumer/selectByUsername")
    public Object selectByUsername(@RequestParam("username") String username) {
        return userService.selectByUsername(username);
    }

    /**
     * 修改密码
     *
     * @param password 新的密码
     * @param answer   密保
     * @return 修改状态
     */
    @PostMapping(value = "/consumer/resetPassword")
    public Object resetPassword(@RequestParam("password") String password,
                                @RequestParam("answer") String answer) {
        return userService.resetPassword(password, answer);
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 注册状态
     */
    @PostMapping(value = "/consumer/register")
    public Object register(@RequestBody User user) {
        return userService.register(user);
    }


    /**
     * 查询
     *
     * @param offset 起始位置
     * @param limit  数据条数
     * @return 对象列表
     */
    @GetMapping(value = "/consumer/user/queryAllByLimit")
    public Object queryAllByLimit(@RequestParam("offset") int offset,
                                  @RequestParam("limit") int limit) {
        return userService.queryAllByLimit(offset, limit);
    }

    /**
     * 升降级操作
     *
     * @param grade 级别
     * @param id    主键
     * @return 升级状态
     */
    @PostMapping(value = "/consumer/upgrade")
    public Object upgrade(@RequestParam("grade") int grade,
                          @RequestParam("id") int id) {
        return userService.upgrade(grade, id);
    }

    /**
     * 删除用户
     *
     * @param id 主键
     * @return 删除状态
     */
    @GetMapping(value = "/consumer/deleteById")
    public Object deleteById(@RequestParam("id") Long id) {
        return userService.deleteById(id);
    }

    @RabbitHandler
    private void getMsg(String msg) {
        log.warn(msg);
    }
}
