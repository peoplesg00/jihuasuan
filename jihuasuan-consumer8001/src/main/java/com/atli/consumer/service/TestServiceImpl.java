package com.atli.consumer.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: LI
 * @Date: 2020/7/23 13:33
 * @Description:
 */
@Service
@Slf4j
public class TestServiceImpl {

    //定义一个资源
    //定义当资源内部发生异常的时候的处理逻辑
    //blockHandler 定义当资源内部发生了BlockException应该进入的方法[捕获的是sentinel定义的异常]
    //fallback定义当资源内部发生了Throwable应该进入的方法

    @SentinelResource(value = "message", blockHandler = "blockHandler", fallback = "fallback")
    public String message(String name) {
        return "message";
    }

    /**
     * blockHandler
     * 要求：
     * 1当前方法的返回值和参数要跟原方法一致
     * 2但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
     *
     * @param name 某值
     * @param e    异常
     * @return 结果
     */
    public String blockHandler(String name, BlockException e) {
        log.error("触发了blockException异常,其内容为：{0}", e);
        return "blockException";
    }

    /**
     * Throwable
     * 要求：
     * 1当前方法的返回值和参数要跟原方法一致
     * 2但是允许在参数列表的最后加入一个参数Throwable，用来接收原方法中发生的异常
     *
     * @param name 某值
     * @param e    异常
     * @return 结果
     */
    public String fallback(String name, Throwable e) {
        log.error("触发了Throwable，其内容为：{0}", e);
        return "Throwable";
    }
}
