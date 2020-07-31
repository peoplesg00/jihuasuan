package com.atli.provider.controller;

import com.atli.provider.service.AccountService;
import entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import response.ResultText;

import java.text.ParseException;

/**
 * @author: LI
 * @Date: 2020/6/9 13:30
 * @Description: @RefreshScope 只需要在需要动态读取配置的类上添加此注解就可以
 * @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
 */
@RestController
@Slf4j
@RefreshScope
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/provider/insert")
    public ResultText insert(@RequestBody Account account) throws ParseException {
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
    @GetMapping(value = "/provider/queryAllByLimit")
    public ResultText queryAllByLimit(int offset, int limit, String founder) {
        return this.accountService.queryAllByLimit(offset, limit, founder);
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @PostMapping(value = "/provider/update")
    public ResultText update(@RequestBody Account account) {
        return this.accountService.update(account);
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
    @GetMapping(value = "/provider/delete")
    public ResultText deleteById(String id, int offset, int limit, String founder, String type, String grade) {
        return this.accountService.deleteById(id, offset, limit, founder, type, grade);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping(value = "/provider/queryById")
    public ResultText queryById(String id) {
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
    @GetMapping(value = "/provider/selectByMonth")
    public ResultText selectByMonth(int offset, int limit, String founder) {
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
    @GetMapping(value = "/provider/selectByYear")
    public ResultText selectByYear(int offset, int limit, String founder) {
        return accountService.selectByYear(offset, limit, founder);
    }

    /**
     * 按照月份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/provider/adminByMonth")
    public ResultText adminByMonth(int pageNum, int pageSize) {
        return this.accountService.adminByMonth(pageNum, pageSize);
    }

    /**
     * 按照年份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/provider/adminByYear")
    public ResultText adminByYear(int pageNum, int pageSize) {
        return this.accountService.adminByYear(pageNum, pageSize);
    }

    /**
     * 按照年份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @GetMapping(value = "/provider/adminByAll")
    public ResultText adminByAll(int pageNum, int pageSize) {
        return this.accountService.adminByAll(pageNum, pageSize);
    }
}
