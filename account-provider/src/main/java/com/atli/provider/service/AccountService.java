package com.atli.provider.service;

import entity.Account;
import org.springframework.transaction.annotation.Transactional;
import response.ResultText;

import java.text.ParseException;

/**
 * @author: LI
 * @Date: 2020/6/9 13:27
 * @Description:
 */
@Transactional(rollbackFor = Exception.class)
public interface AccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ResultText queryById(String id);

    /**
     * 插入数据
     *
     * @param account 插入数据
     * @return 结果
     */
    ResultText insert(Account account) throws ParseException;

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    ResultText queryAllByLimit(int offset, int limit, String founder);

    /**
     * 按月份查询数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    ResultText selectByMonth(int offset, int limit, String founder);

    /**
     * 按年份查询数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    ResultText selectByYear(int offset, int limit, String founder);

    /**
     * 查询按月份分类的账单
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    ResultText adminByMonth(int pageNum, int pageSize);

    /**
     * 查询按月份分类的账单
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    ResultText adminByYear(int pageNum, int pageSize);

    /**
     * 查询所有账单
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return
     */
    ResultText adminByAll(int pageNum, int pageSize);

    /**
     * 通过主键删除数据
     *
     * @param id      主键
     * @param offset  起始位置
     * @param limit   数据条数
     * @param founder 持有者
     * @param type    类型
     * @param grade   权限
     * @return 是否成功
     */
    ResultText deleteById(String id, int offset, int limit, String founder, String type, String grade);

    /**
     * 通过时间删除数据
     *
     * @param createTime 时间
     * @param offset     起始位置
     * @param limit      数据条数
     * @param founder    持有者
     * @param type       类型
     * @param grade      权限
     * @return 影响行数
     */
    ResultText deleteByTime(String createTime, int offset, int limit, String founder, String type, String grade);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    ResultText update(Account account);
}
