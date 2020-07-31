package com.atli.provider.predicate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author: LI
 * @Date: 2020/7/24 11:57
 * @Description: //这是一个自定义的路由断言工厂类，要求有两个
 * //1名字必须是 配置 + RoutePredicateFactory
 * //2必须继承AbstractRoutePredicateFactory<配置类>
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {
    public static final String DATETIME_KEY = "age";

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    /**
     * 读取配置文件的参数值，给赋值到配置类的属性上
     *
     * @return 对应的值
     */
    @Override
    public List<String> shortcutFieldOrder() {
        //与配置类上的属性一一对应
        return Arrays.asList("minAge", "maxAge");
    }

    /**
     * 断言逻辑
     *
     * @param config 配置类
     * @return 结果
     */
    @Override
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String first = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                if (StringUtils.isNotEmpty(first)) {
                    int age = Integer.parseInt(first);
                    return age < config.getMaxAge() && age > config.getMinAge();
                }
                return false;
            }
        };
    }

    /**
     * 配置类，用于接收配置文件中的对应参数
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private int minAge;
        private int maxAge;
    }
}