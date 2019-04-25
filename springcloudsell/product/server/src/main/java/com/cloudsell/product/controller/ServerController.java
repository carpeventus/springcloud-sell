package com.cloudsell.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Haiyu
 * @date 2019/3/17 9:56
 */
@RestController
public class ServerController {
    @GetMapping("/msg")
    public String msg() {
        return "From PRODUCT: This is a message.";
    }
}
