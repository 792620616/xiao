package com.xiao.service;

import com.xiao.entity.CommonResult;
import com.xiao.entity.Payment;
import javafx.beans.DefaultProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeginService {


    @GetMapping(value = "/payment/get/{id}")   // 这家伙把提交方式都改成  post了
    public CommonResult get(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")  // 这家伙默认是  1s  报错
    public String paymentFeigntime();
}
