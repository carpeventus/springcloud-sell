package com.cloudsell.apigetway.controller;

import com.cloudsell.apigetway.config.ZuulConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Haiyu
 * @date 2019/4/18 17:07
 */
@RestController
public class TestController {
    @Autowired
    private ZuulProperties zuulProperties;

    @GetMapping("/test")
    public String  test() {
        return zuulProperties.toString();
    }
}
