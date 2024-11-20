package com.lara.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaibing
 * @date 2024-06-24
 */
@RestController
public class Hello {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
