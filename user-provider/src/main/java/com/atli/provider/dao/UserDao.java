package com.atli.provider.dao;

import entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-17 13:20:25
 */
@Mapper
public interface UserDao {

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
    User selectByUserName(@Param("username") String userName);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录状态
     */
    User login(@Param("username") String username, @Param("password") String password);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<User> queryAllByLimit();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过主键修改密码
     *
     * @param id       主键
     * @param password 新的密码
     * @return 修改状态
     */
    int resetPassword(Long id, String password);

    /**
     * 升降级操作
     *
     * @param grade 级别
     * @param id    主键
     * @return 升级状态
     */
    int upgrade(@Param("grade") int grade, @Param("id") int id);
}