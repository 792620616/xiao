package xiao.conteroller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xiao.service.PaymentHystrixService;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbackMethod")
public class OrderHystirxController {

    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    //@HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id){
       // int i= 10/0;
        String s = paymentHystrixService.paymentInfo_OK(id);
        return s;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
   /* @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "1500")
    })*/
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){

        int i= 10/0;
        String s = paymentHystrixService.paymentInfo_TimeOut(id);
        return s;
    }

    public String paymentInfo_TimeOutHandler(Integer id){

        return id+":::80机器 超时返回数据";
    }

    // global  fallback

    // 下面是全局fallback方法
    public String paymentInfo_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试， /(ToT)/";
    }



}
