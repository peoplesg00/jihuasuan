package com.atli.consumer.service.fallback;

import com.atli.consumer.service.UserService;
import entity.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import response.ResultEnum;
import response.ResultText;

/**
 * @author: LI
 * @Date: 2020/7/23 14:12
 * @Description:
 */
@Slf4j
@Service
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable cause) {
        return new UserService() {
            @Override
            public Object selectOne(Long id) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.NOT_DATA);
            }

            @Override
            public Object login(User user) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.LOGIN_FAIL);
            }

            @Override
            public Object code() {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.DATA_FAIL);
            }

            @Override
            public Object loginOut(String username) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.DATA_FAIL);
            }

            @Override
            public Object selectByUsername(String username) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.DATA_FAIL);
            }

            @Override
            public Object resetPassword(String password, String answer) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.DATA_FAIL);
            }

            @Override
            public Object register(User user) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.DATA_FAIL);
            }

            @Override
            public Object queryAllByLimit(int offset, int limit) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.DATA_FAIL);
            }

            @Override
            public Object upgrade(int grade, int id) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.DATA_FAIL);
            }

            @Override
            public Object deleteById(Long id) {
                log.error("出现异常{0}", cause);
                return ResultText.error(ResultEnum.DATA_FAIL);
            }
        };
    }
}
