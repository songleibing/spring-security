package com.garen.example.springsecurity.configurer.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 *
 * @author leibing.song
 * @since 2019-01-28
 */
@Slf4j
@Component
public class ConsumeAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败 - {}", getClass().getSimpleName());
        request.setAttribute("authenticationException", exception);
        request.getRequestDispatcher("/signinFailure").forward(request, response);
    }
}
