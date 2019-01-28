package com.garen.example.springsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录成功处理器
 *
 * @author leibing.song
 * @since 2019-01-28
 */
@Slf4j
@Controller
public class SigninSuccessController {

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/signinSuccess", produces = MediaType.TEXT_HTML_VALUE)
    public String handleBrowser(@RequestAttribute Authentication authentication) throws Exception {
        log.info("浏览器登录成功 - {} : {}", getClass().getSimpleName(), objectMapper.writeValueAsString(authentication));
        return "redirect:/index";
    }

    @RequestMapping(path = "/signinSuccess")
    public ResponseEntity<Authentication> handleAPP(@RequestAttribute Authentication authentication) throws Exception {
        log.info("APP登录成功 - {} : {}", getClass().getSimpleName(), objectMapper.writeValueAsString(authentication));
        return new ResponseEntity<>(authentication, HttpStatus.OK);
    }
}
