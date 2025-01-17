package com.paic.isee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 14:00
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.paic.isee"})
public class IseeCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(IseeCommonApplication.class,args);
    }
}
