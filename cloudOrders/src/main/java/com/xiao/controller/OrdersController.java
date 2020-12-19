package com.xiao.controller;

import com.xiao.entity.CommonResult;
import com.xiao.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    //public static final String PAYMENT_URL="http://localhost:8001";
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payment/create")
    public CommonResult getPyment(){

        Payment payment = new Payment(2L, "2");
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "/payment/add", payment, CommonResult.class);

        return commonResult;
    }

    @GetMapping("/consumer/payment/get")
    public CommonResult get(Long id){
        CommonResult forObject = restTemplate.getForObject(PAYMENT_URL + "/payment/get?id="+id, CommonResult.class);
        return forObject;
    }

    @GetMapping("/consumer/payment/getEntity/get")
    public CommonResult<Payment> getEntity(Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get?id=" + id, CommonResult.class);

        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult<Payment>(444,"操作失败");
        }
    }
}
