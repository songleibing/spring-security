package com.garen.example.springsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录失败处理器
 *
 * @author leibing.song
 * @since 2019-01-28
 */
@Slf4j
@Controller
@RequestMapping("/signinFailure")
public class SigninFailureController {

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String handleBrowser(@RequestAttribute AuthenticationException authenticationException) {
        log.info("浏览器登录失败 - {} : {}", getClass().getSimpleName(), authenticationException);
        return "redirect:/signin";
    }

    @RequestMapping
    public ResponseEntity<AuthenticationException> handleAPP(@RequestAttribute AuthenticationException authenticationException) {
        log.error("APP登录失败 - {} : {}", getClass().getSimpleName(), authenticationException);
        return new ResponseEntity<>(authenticationException, HttpStatus.UNAUTHORIZED);
    }
}
