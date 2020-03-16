package com.paic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/23 12:35
 */
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.paic.common"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages={"com.paic.mongodb.*"})
public class CostCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CostCommonApplication.class,args);
    }
}
