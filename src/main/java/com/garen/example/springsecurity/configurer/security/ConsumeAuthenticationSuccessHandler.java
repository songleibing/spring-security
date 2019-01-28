package com.garen.example.springsecurity.configurer.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录成功后的处理逻辑
 *
 * @author leibing.song
 * @since 2019-01-28
 */
@Slf4j
@Component
public class ConsumeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("登录成功 - {}", getClass().getSimpleName());
        request.setAttribute("authentication", authentication);
        request.getRequestDispatcher("/signinSuccess").forward(request, response);
    }
}
