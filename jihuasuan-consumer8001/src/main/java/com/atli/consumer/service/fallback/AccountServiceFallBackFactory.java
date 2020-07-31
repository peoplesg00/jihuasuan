package com.atli.consumer.service.fallback;

import com.atli.consumer.service.AccountService;
import entity.Account;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import response.ResultEnum;
import response.ResultText;

/**
 * @author: LI
 * @Date: 2020/7/26 14:23
 * @Description:
 */
@Service
@Slf4j
public class AccountServiceFallBackFactory implements FallbackFactory<AccountService> {
    @Override
    public AccountService create(Throwable cause) {
        return new AccountService() {
            @Override
            public Object insert(Account account) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object queryAllByLimit(int offset, int limit, String founder) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object update(Account account) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object deleteById(String id, int offset, int limit, String founder, String type, String grade) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object queryById(String id) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object selectByMonth(int offset, int limit, String founder) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object selectByYear(int offset, int limit, String founder) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object adminByMonth(int pageNum, int pageSize) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object adminByYear(int pageNum, int pageSize) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object adminByAll(int pageNum, int pageSize) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }
        };
    }
}
