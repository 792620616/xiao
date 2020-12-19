package com.xiao;

import com.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE" ,configuration = MyselfRule.class)
public class OrdersMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrdersMain80.class,args);
    }
}
