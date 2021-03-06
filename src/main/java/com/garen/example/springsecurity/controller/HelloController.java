package com.garen.example.springsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leibing.song
 * @since 2019-01-28
 */
@Slf4j
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        log.info("hello() ... ");
        return "hello";
    }

}
