package com.xiao.service.impl;

import com.xiao.dao.PaymentDao;
import com.xiao.entity.Payment;
import com.xiao.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;
    public int create(Payment payment) {
        int i = paymentDao.create(payment);

        return i;
    }

    public Payment getPaymentById(Long id) {
        Payment byId = paymentDao.getPaymentById(id);
        return byId;
    }
}
