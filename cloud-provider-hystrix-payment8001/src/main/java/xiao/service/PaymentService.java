package xiao.service;


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class  PaymentService {


    public String paymentInfor_OK(Integer id){
        return "线程池： "+Thread.currentThread().getName()+"    paymentInfo_OK,id  "+id+"哈哈——————";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){


        int timeNumber=2;
        if(id>0){
            int num= 10/id;
        }else{
            int num= 10/0;
        }
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+"    paymentInfo_OUT ,id  "+id+"哈哈——————";
    }


    public String paymentInfo_TimeOutHandler(Integer id){

        return id+":::8001机器 超时返回数据";
    }



    // 服务熔断  HystrixCommandproperty  里面有属性设置
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  // 是否开启短路
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" ,value = "10"),  // 十次访问
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),  // 时间窗口其
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" ,value = "60")   // 故障60%

    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("*************id 不能是负数");
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功"+ s;
    }



    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){

        return "id 不能是负数";
    }

}
