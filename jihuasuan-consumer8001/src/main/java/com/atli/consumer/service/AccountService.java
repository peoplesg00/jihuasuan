package com.atli.consumer.service;

import com.atli.consumer.service.fallback.AccountServiceFallBackFactory;
import entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: LI
 * @Date: 2020/7/26 14:11
 * @Description:
 */
@FeignClient(value = "account-provider", fallbackFactory = AccountServiceFallBackFactory.class)
public interface AccountService {

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/provider/insert")
    Object insert(@RequestBody Account account);

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @GetMapping(value = "/provider/queryAllByLimit")
    Object queryAllByLimit(@RequestParam("offset") int offset,
                           @RequestParam("limit") int limit,
                           @RequestParam("founder") String founder);

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/provider/update")
    Object update(@RequestBody Account account);

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
    @GetMapping(value = "/provider/delete")
    Object deleteById(@RequestParam("id") String id,
                      @RequestParam("offset") int offset,
                      @RequestParam("limit") int limit,
                      @RequestParam("founder") String founder,
                      @RequestParam("type") String type,
                      @RequestParam("grade") String grade);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping(value = "/provider/queryById")
    Object queryById(@RequestParam("id") String id);

    /**
     * 按月份查询数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @GetMapping(value = "/provider/selectByMonth")
    Object selectByMonth(@RequestParam("offset") int offset,
                         @RequestParam("limit") int limit,
                         @RequestParam("founder") String founder);


    /**
     * 按年份查询数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @GetMapping(value = "/provider/selectByYear")
    Object selectByYear(@RequestParam("offset") int offset,
                        @RequestParam("limit") int limit,
                        @RequestParam("founder") String founder);


    /**
     * 按照月份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/provider/adminByMonth")
    Object adminByMonth(@RequestParam("pageNum") int pageNum,
                        @RequestParam("pageSize") int pageSize);

    /**
     * 按照年份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/provider/adminByYear")
    Object adminByYear(@RequestParam("pageNum") int pageNum,
                       @RequestParam("pageSize") int pageSize);

    /**
     * 按照年份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/provider/adminByAll")
    Object adminByAll(@RequestParam("pageNum") int pageNum,
                      @RequestParam("pageSize") int pageSize);
}
