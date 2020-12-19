package com.xiao.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.xiao.entity.CommonResult;
import com.xiao.entity.Payment;
import com.xiao.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;


    @RequestMapping("/add")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        paymentService.create(payment);
        return new CommonResult(200,"成功");

    }


    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id")  Long id){
        Payment payment = paymentService.getPaymentById(id);

        return new CommonResult(8001,"成功",payment);

    }

    @GetMapping("/lb/{id}")
    public CommonResult lib(@PathVariable("id")  Long id){

        return new CommonResult(200,"成功");

    }
    @GetMapping(value = "/feign/timeout")
    public String paymentFeigntime(){

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "8001";
    }

}
