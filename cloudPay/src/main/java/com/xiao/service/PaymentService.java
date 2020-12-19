package com.xiao.service;

import com.xiao.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);

    default Integer getdata(){
        return 1;
    }
}
