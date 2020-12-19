package xiao.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackService implements PaymentHystrixService{
    public String paymentInfo_OK(Integer id) {
        return "800出问题了";
    }

    public String paymentInfo_TimeOut(Integer id) {
        return "800出问题了";
    }
}
