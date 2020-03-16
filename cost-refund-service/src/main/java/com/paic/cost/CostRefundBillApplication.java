package com.paic.cost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/22 22:13
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.paic.cost"})
public class CostRefundBillApplication {

    public static void main(String[] args) {
        SpringApplication.run(CostRefundBillApplication.class,args);
    }
}
