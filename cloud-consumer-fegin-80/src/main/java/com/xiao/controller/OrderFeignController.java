package com.xiao.controller;


import com.xiao.entity.CommonResult;
import com.xiao.entity.Payment;
import com.xiao.service.PaymentFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderFeignController {

    @Autowired
    private PaymentFeginService paymentFeginService;


    @GetMapping(value = "/consumer/payment/get")
    public CommonResult<Payment> get(Long id){

        return paymentFeginService.get(id);
    }



    @GetMapping(value = "/feign/timeout")

    public String paymentFeigntime(){

        return paymentFeginService.paymentFeigntime();
    }

}
