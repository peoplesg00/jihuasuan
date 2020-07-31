package com.atli.provider.service.impl;


import cn.hutool.core.lang.UUID;
import com.atli.provider.dao.AccountDao;
import com.atli.provider.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Account;
import entity.Const;
import entity.Token;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import response.ResultEnum;
import response.ResultText;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * (Account)表服务实现类
 *
 * @author makejava
 * @since 2020-06-20 12:52:15
 */
@Service("accountService")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ResultText queryById(String id) {
        if (id == null) {
            return ResultText.error(ResultEnum.NOT_DATA);
        }
        Account account = null;
        Token token = (Token) redisTemplate.opsForValue().get(id);
        if (token == null) {
            account = this.accountDao.queryById(id);
            if (account != null) {
                Token token1 = new Token(id, account);
                redisTemplate.opsForValue().set(id, token1, 30, TimeUnit.MINUTES);
            }
        }
        return account != null ?
                ResultText.success(ResultEnum.SELECT_SUCCESS, account) : ResultText.error(ResultEnum.ACCOUNT_NOT_EXISTS);
    }

    /**
     * 查询多条数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @Override
    public ResultText queryAllByLimit(int offset, int limit, String founder) {
        if (limit == 0) {
            return ResultText.error(ResultEnum.DATA_FAIL);
        }
        PageHelper.startPage(offset, limit);
        List<Account> accountList;
        PageInfo<Account> pageInfo;
        Token token = (Token) redisTemplate.opsForValue().get(offset + limit + founder + Const.ORDINARY + Const.ALL);
        if (token == null) {
            accountList = this.accountDao.queryAllByLimit(founder);
            pageInfo = new PageInfo<>(accountList);
            if (accountList != null) {
                Token token1 = new Token(offset + limit + founder + Const.ORDINARY + Const.ALL, pageInfo);
                redisTemplate.opsForValue().set(offset + limit + founder + Const.ORDINARY + Const.ALL, token1, 30, TimeUnit.MINUTES);
            }
        } else {
            pageInfo = token.getPageInfo();
        }
        rabbitTemplate.convertAndSend("account", "account.queryAllByLimit", "用户查询所有账单成功：");
        return pageInfo != null ?
                ResultText.success(ResultEnum.SELECT_SUCCESS, pageInfo) : ResultText.error(ResultEnum.ACCOUNT_NOT_EXISTS);
    }

    /**
     * 按月份查询数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @Override
    public ResultText selectByMonth(int offset, int limit, String founder) {
        if (limit == 0) {
            return ResultText.error(ResultEnum.DATA_FAIL);
        }
        PageHelper.startPage(offset, limit);
        List<Account> accountList;
        PageInfo<Account> pageInfo;
        Token token = (Token) redisTemplate.opsForValue().get(offset + limit + founder + Const.ORDINARY + Const.MONTH);
        if (token == null) {
            accountList = this.accountDao.selectByMonth(founder);
            pageInfo = new PageInfo<>(accountList);
            if (accountList != null) {
                Token token1 = new Token(offset + limit + founder + Const.ORDINARY + Const.MONTH, pageInfo);
                redisTemplate.opsForValue().set(offset + limit + founder + Const.ORDINARY + Const.MONTH, token1, 30, TimeUnit.MINUTES);
            }
        } else {
            pageInfo = token.getPageInfo();
        }
        return pageInfo != null ?
                ResultText.success(ResultEnum.SELECT_SUCCESS, pageInfo) : ResultText.error(ResultEnum.ACCOUNT_NOT_EXISTS);
    }

    /**
     * 按年份查询数据
     *
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @param founder 持有者
     * @return 对象列表
     */
    @Override
    public ResultText selectByYear(int offset, int limit, String founder) {
        if (limit == 0) {
            return ResultText.error(ResultEnum.DATA_FAIL);
        }
        PageHelper.startPage(offset, limit);
        List<Account> accountList;
        PageInfo<Account> pageInfo;
        Token token = (Token) redisTemplate.opsForValue().get(offset + limit + founder + Const.ORDINARY + Const.YEAR);
        if (token == null) {
            accountList = this.accountDao.selectByYear(founder);
            pageInfo = new PageInfo<>(accountList);
            if (accountList != null) {
                Token token1 = new Token(offset + limit + founder + Const.ORDINARY + Const.YEAR, pageInfo);
                redisTemplate.opsForValue().set(offset + limit + founder + Const.ORDINARY + Const.YEAR, token1, 30, TimeUnit.MINUTES);
            }
        } else {
            pageInfo = token.getPageInfo();
        }
        return pageInfo != null ?
                ResultText.success(ResultEnum.SELECT_SUCCESS, pageInfo) : ResultText.error(ResultEnum.ACCOUNT_NOT_EXISTS);
    }

    /**
     * 按月份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @Override
    public ResultText adminByMonth(int pageNum, int pageSize) {
        if (pageSize == 0) {
            return ResultText.error(ResultEnum.DATA_FAIL);
        }
        List<Account> accountList;
        Token token = (Token) redisTemplate.opsForValue().get(pageNum + pageSize + Const.ADMIN + Const.MONTH);
        if (token == null) {
            accountList = this.accountDao.adminByMonth(pageNum, pageSize);
            if (accountList != null) {
                Token token1 = new Token(pageNum + pageSize + Const.ADMIN + Const.MONTH, accountList);
                redisTemplate.opsForValue().set(pageNum + pageSize + Const.ADMIN + Const.MONTH, token1, 30, TimeUnit.MINUTES);
            }
        } else {
            accountList = token.getList();
        }
        return accountList != null ?
                ResultText.success(ResultEnum.SELECT_SUCCESS, accountList) : ResultText.error(ResultEnum.ACCOUNT_NOT_EXISTS);
    }

    /**
     * 按年份查询
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @Override
    public ResultText adminByYear(int pageNum, int pageSize) {
        if (pageSize == 0) {
            return ResultText.error(ResultEnum.DATA_FAIL);
        }
        List<Account> accountList;
        Token token = (Token) redisTemplate.opsForValue().get(pageNum + pageSize + Const.ADMIN + Const.YEAR);
        if (token == null) {
            accountList = this.accountDao.adminByYear(pageNum, pageSize);
            if (accountList != null) {
                Token token1 = new Token(pageNum + pageSize + Const.ADMIN + Const.YEAR, accountList);
                redisTemplate.opsForValue().set(pageNum + pageSize + Const.ADMIN + Const.YEAR, token1, 30, TimeUnit.MINUTES);
            }
        } else {
            accountList = token.getList();
        }
        return accountList != null ?
                ResultText.success(ResultEnum.SELECT_SUCCESS, accountList) : ResultText.error(ResultEnum.ACCOUNT_NOT_EXISTS);
    }

    /**
     * 查询所有数据
     *
     * @param pageNum  第几页
     * @param pageSize 几条数据
     * @return 对象列表
     */
    @Override
    public ResultText adminByAll(int pageNum, int pageSize) {
        if (pageSize == 0) {
            return ResultText.error(ResultEnum.DATA_FAIL);
        }
        List<Account> accountList;
        Token token = (Token) redisTemplate.opsForValue().get(pageNum + pageSize + Const.ADMIN + Const.ALL);
        if (token == null) {
            accountList = this.accountDao.adminByAll(pageNum, pageSize);
            if (accountList != null) {
                Token token1 = new Token(pageNum + pageSize + Const.ADMIN + Const.ALL, accountList);
                redisTemplate.opsForValue().set(pageNum + pageSize + Const.ADMIN + Const.ALL, token1, 30, TimeUnit.MINUTES);
            }
        } else {
            accountList = token.getList();
        }
        rabbitTemplate.convertAndSend("account", "account.adminByAll", "管理员查询所有账单成功：");
        return accountList != null ?
                ResultText.success(ResultEnum.SELECT_SUCCESS, accountList) : ResultText.error(ResultEnum.ACCOUNT_NOT_EXISTS);
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public ResultText insert(Account account) {
        if (account == null) {
            return ResultText.error(ResultEnum.NOT_DATA);
        }
        account.setId(UUID.fastUUID().toString().replaceAll("-", ""));
        int b = this.accountDao.insert(account);
        rabbitTemplate.convertAndSend("account", "account.insert", "账单新增成功：" + account.getId());
        return b > 0 ?
                ResultText.success(ResultEnum.INSERT_SUCCESS) : ResultText.error(ResultEnum.INSERT_FAIL);
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public ResultText update(Account account) {
        if (account == null) {
            return ResultText.error(ResultEnum.NOT_DATA);
        }
        return this.accountDao.update(account) > 0 ?
                ResultText.success(ResultEnum.UPDATE_SUCCESS) : ResultText.error(ResultEnum.UPDATE_FAIL);
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
    @Override
    public ResultText deleteById(@NotNull String id, int offset, int limit, String founder, String type, String grade) {
        if (id.isEmpty() || founder.isEmpty()) {
            return ResultText.error(ResultEnum.NOT_DATA);
        }
        if (id.length() <= Const.TIME_LENGTH) {
            return this.deleteByTime(id, offset, limit, founder, type, grade);
        }
        int num = this.accountDao.deleteById(id);
        boolean flag = deleteByRedis(offset, limit, founder, type, grade);
        rabbitTemplate.convertAndSend("account", "account.deleteById", "账单删除成功：" + id);
        return (num > 0 && flag) ?
                ResultText.success(ResultEnum.DELETE_SUCCESS) : ResultText.error(ResultEnum.DELETE_FAIL);
    }

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
    @Override
    public ResultText deleteByTime(String createTime, int offset, int limit, String founder, String type, String grade) {
        int num = this.accountDao.deleteByTime(createTime.trim().length(), createTime, founder);
        boolean flag = deleteByRedis(offset, limit, founder, type, grade);
        rabbitTemplate.convertAndSend("account", "account.deleteByTime", "账单删除成功：" + createTime);
        return (num > 0 && flag) ?
                ResultText.success(ResultEnum.DELETE_SUCCESS) : ResultText.error(ResultEnum.DELETE_FAIL);
    }

    /**
     * 删除数据时删除缓存中的相同数据
     *
     * @param offset  起始位置
     * @param limit   数据条数
     * @param founder 持有者
     * @param type    类型
     * @param grade   权限
     * @return 删除结果
     */
    private synchronized Boolean deleteByRedis(int offset, int limit, String founder, String type, String grade) {
        String id;
        if (Objects.equals(type, Const.M)) {
            id = Const.MONTH;
        } else if (Objects.equals(type, Const.Y)) {
            id = Const.YEAR;
        } else {
            id = Const.ALL;
        }
        rabbitTemplate.convertAndSend("account", "account.deleteByRedis", "缓存清理成功：");
        return Integer.parseInt(grade) > Const.ORDINARY ?
                redisTemplate.delete(offset + limit + Const.ADMIN + id) : redisTemplate.delete(offset + limit + founder + Const.ORDINARY + id);
    }
}