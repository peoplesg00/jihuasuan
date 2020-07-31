package com.atli.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: LI
 * @Date: 2020/5/27 17:02
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class NacosConsumerMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain8001.class, args);
    }
}
