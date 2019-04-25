package com.cloudsell.order.controller;

import com.cloudsell.product.client.ProductClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author Haiyu
 * @date 2019/4/21 10:21
 */
@RestController
@RequestMapping("/hystrix")
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    @Autowired
    private ProductClient productClient;

//    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
//    @HystrixCommand
    @GetMapping("/getProductList")
    public String getProductList(@RequestParam("num") Integer num) {
        // 目标服务不能正常提供服务（product应用关闭）会触发降级，本服务内部发生异常也会触发降级
//        throw new RuntimeException();
        if (num % 2 == 0) {
            return "success";
        }
//        return productClient.productMsg();
        return productClient.listForOrder(Collections.singletonList("157875196366160022")).toString();
    }

    private String fallback() {
        return "太拥挤了，请稍后再试~~";
    }

    private String defaultFallback() {
        return "默认提示：太拥挤了，请稍后再试~~";
    }
}
