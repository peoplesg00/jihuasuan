package com.atli.provider.service;

import entity.User;
import org.springframework.transaction.annotation.Transactional;
import response.ResultText;

import java.io.UnsupportedEncodingException;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-06-17 13:20:25
 */
@Transactional(rollbackFor = Exception.class)
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 通过用户名查询
     *
     * @param userName 用户名
     * @return 用户信息
     */
    ResultText selectByUserName(String userName);

    /**
     * 修改密码
     *
     * @param password 新的密码
     * @param answer   密保答案
     * @return 修改状态
     */
    ResultText resetPassword(String password, String answer);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录状态
     */
    ResultText login(String username, String password) throws UnsupportedEncodingException;

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    ResultText queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    ResultText insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    ResultText deleteById(Long id);

    /**
     * 升降级操作
     *
     * @param grade 级别
     * @param id    主键
     * @return 升级状态
     */
    ResultText upgrade(int grade, int id);
}