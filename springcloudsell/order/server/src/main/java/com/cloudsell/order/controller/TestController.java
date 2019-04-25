package com.cloudsell.order.controller;

import com.cloudsell.order.config.PersonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haiyu
 * @date 2019/3/28 10:14
 */
@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {
    @Autowired
    private PersonConfig personConfig;

    @RequestMapping("/person")
    public String  person() {
        return personConfig.getName() + " " + personConfig.getAge();
    }
}
