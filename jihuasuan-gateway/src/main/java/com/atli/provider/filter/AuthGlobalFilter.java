package com.atli.provider.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: LI
 * @Date: 2020/7/24 13:47
 * @Description: 自定义全局过滤器
 */
@Slf4j
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 过滤器逻辑
     *
     * @param exchange 一个HTTP请求-响应交互的契约
     * @param chain    网关过滤链表
     * @return 结果
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (!StringUtils.equals("admin", token)) {
            log.info("认证失败..............");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 返回值越低，权重越高
     *
     * @return 权重等级
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
