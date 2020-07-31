package com.atli.provider.dao;


import entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Account)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-16 21:06:44
 */
@Mapper
public interface AccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Account queryById(String id);

    /**
     * 按月份查询数据
     *
     * @param founder 持有者
     * @return 数据
     */
    List<Account> selectByMonth(@Param("founder") String founder);

    /**
     * 查询按月份分类的账单
     *
     * @param pageNum  起始位置
     * @param pageSize 数据条数
     * @return 对象列表
     */
    List<Account> adminByMonth(@Param("offset") int pageNum, @Param("limit") int pageSize);

    /**
     * 查询按年份分类的账单
     *
     * @param pageNum  起始位置
     * @param pageSize 数据条数
     * @return 对象列表
     */
    List<Account> adminByYear(@Param("offset") int pageNum, @Param("limit") int pageSize);

    /**
     * 查询按年份分类的账单
     *
     * @param pageNum  起始位置
     * @param pageSize 数据条数
     * @return 对象列表
     */
    List<Account> adminByAll(@Param("offset") int pageNum, @Param("limit") int pageSize);

    /**
     * 按年份查询数据
     *
     * @param founder 持有者
     * @return 数据
     */
    List<Account> selectByYear(@Param("founder") String founder);

    /**
     * 查询指定行数据
     *
     * @param founder 持有者
     * @return 对象列表
     */
    List<Account> queryAllByLimit(@Param("founder") String founder);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @return 对象列表
     */
    List<Account> queryAll(Account account);

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int insert(Account account);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    int update(Account account);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 通过时间删除数据
     *
     * @param length     长度
     * @param createTime 时间
     * @param founder    持有者
     * @return 影响行数
     */
    int deleteByTime(@Param("length") int length, @Param("create_time") String createTime, @Param("founder") String founder);

    /**
     * 查询所有账单
     *
     * @return 账单
     */
    List<Account> getAllAccount();
}