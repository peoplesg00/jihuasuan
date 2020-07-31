package com.atli.consumer.service;

import com.atli.consumer.service.fallback.UserServiceFallBackFactory;
import entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LI
 * @Date: 2020/7/16 21:28
 * @Description:
 */
@FeignClient(value = "user-provider", fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService {

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping(value = "/user/selectOne/{id}")
    Object selectOne(@PathVariable("id") Long id);

    /**
     * 登录
     *
     * @param user 用户
     * @return 登录状态
     */
    @PostMapping(value = "/user/provider/login")
    Object login(@RequestBody User user);

    /**
     * 获取验证码
     *
     * @return 获取验证码状态
     */
    @GetMapping(value = "/user/provider/code")
    Object code();

    /**
     * 退出登录
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/provider/loginOut")
    Object loginOut(@RequestParam("username") String username);

    /**
     * 通过用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    @PostMapping(value = "/user/provider/selectByUsername")
    Object selectByUsername(@RequestParam("username") String username);

    /**
     * 修改密码
     *
     * @param password 新的密码
     * @param answer   密保
     * @return 修改状态
     */
    @PostMapping(value = "/user/provider/resetPassword")
    Object resetPassword(@RequestParam("password") String password,
                         @RequestParam("answer") String answer);

    /**
     * 注册
     *
     * @param user 用户信息
     * @return 注册状态
     */
    @PostMapping(value = "/user/provider/register")
    Object register(@RequestBody User user);


    /**
     * 查询
     *
     * @param offset 起始位置
     * @param limit  数据条数
     * @return 对象列表
     */
    @GetMapping(value = "/user/provider/queryAllByLimit")
    Object queryAllByLimit(@RequestParam("offset") int offset,
                           @RequestParam("limit") int limit);

    /**
     * 升降级操作
     *
     * @param grade 级别
     * @param id    主键
     * @return 升级状态
     */
    @PostMapping(value = "/user/provider/upgrade")
    Object upgrade(@RequestParam("grade") int grade,
                   @RequestParam("id") int id);

    /**
     * 删除用户
     *
     * @param id 主键
     * @return 删除状态
     */
    @GetMapping(value = "/user/provider/deleteById")
    Object deleteById(@RequestParam("id") Long id);
}
