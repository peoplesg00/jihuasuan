package com.atli.consumer.controller;

import com.atli.consumer.service.AccountService;
import entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author: LI
 * @Date: 2020/7/26 15:23
 * @Description: @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
 */
@RestController
@Slf4j
@RefreshScope
@RabbitListener(queues = "provider_account")
public class AccountConsumerController {

    private final AccountService accountService;

    public AccountConsumerController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/consumer/insert")
    public Object insert(@RequestBody Account account) {
        return accountService.insert(account);
    }

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @GetMapping(value = "/consumer/account/queryAllByLimit")
    public Object queryAllByLimit(@RequestParam("offset") int offset,
                                  @RequestParam("limit") int limit,
                                  @RequestParam("founder") String founder) {
        return accountService.queryAllByLimit(offset, limit, founder);
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/consumer/update")
    public Object update(@RequestBody Account account) {
        return accountService.update(account);
    }

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
    @GetMapping(value = "/consumer/delete")
    public Object deleteById(@RequestParam("id") String id,
                             @RequestParam("offset") int offset,
                             @RequestParam("limit") int limit,
                             @RequestParam("founder") String founder,
                             @RequestParam("type") String type,
                             @RequestParam("grade") String grade) {
        return accountService.deleteById(id, offset, limit, founder, type, grade);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping(value = "/consumer/queryById")
    public Object queryById(@RequestParam("id") String id) {
        return accountService.queryById(id);
    }

    /**
     * 按月份查询数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @GetMapping(value = "/consumer/selectByMonth")
    public Object selectByMonth(@RequestParam("offset") int offset,
                                @RequestParam("limit") int limit,
                                @RequestParam("founder") String founder) {
        return accountService.selectByMonth(offset, limit, founder);
    }


    /**
     * 按年份查询数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @GetMapping(value = "/consumer/selectByYear")
    public Object selectByYear(@RequestParam("offset") int offset,
                               @RequestParam("limit") int limit,
                               @RequestParam("founder") String founder) {
        return accountService.selectByYear(offset, limit, founder);
    }


    /**
     * 按照月份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/consumer/adminByMonth")
    public Object adminByMonth(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize) {
        return accountService.adminByMonth(pageNum, pageSize);
    }

    /**
     * 按照年份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/consumer/adminByYear")
    public Object adminByYear(@RequestParam("pageNum") int pageNum,
                              @RequestParam("pageSize") int pageSize) {
        return accountService.adminByYear(pageNum, pageSize);
    }

    /**
     * 按照年份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/consumer/adminByAll")
    public Object adminByAll(@RequestParam("pageNum") int pageNum,
                             @RequestParam("pageSize") int pageSize) {
        return accountService.adminByAll(pageNum, pageSize);
    }

    @RabbitHandler
    private void getMsg(String msg) {
        log.warn(msg);
    }
}
