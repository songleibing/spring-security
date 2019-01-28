package com.garen.example.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * 登录处理
 *
 * @author leibing.song
 * @since 2019-01-28
 */
@Controller
public class SigninController {

    /**
     * 浏览器发起，跳转到登录页
     *
     * @return
     */
    @RequestMapping(path = "/signin", produces = MediaType.TEXT_HTML_VALUE)
    public String handleBrowser() {
        return "login";
    }

    /**
     * app 发起，则返回401状态码和错误报文
     *
     * @return
     */
    @RequestMapping("/signin")
    public ResponseEntity handleAPP() {
        HashMap<String, String> body = new HashMap<String, String>() {{
            this.put("respCode", "00009");
            this.put("respDesc", "请先登录！");
        }};
        return new ResponseEntity(body, HttpStatus.UNAUTHORIZED);
    }
}
