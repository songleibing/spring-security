package com.garen.example.springsecurity.configurer.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author leibing.song
 * @since 2019-01-28
 */
@Slf4j
@Service
public class ConsumeUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 数据库密码应该是加密后的密码，在用户注册的时候调用<code>encode</code>方法
         * @see PasswordEncoder#encode(CharSequence)
         */
        String password = passwordEncoder.encode("123456");
        log.info("数据库密码：{}", password);
        return new User("garen", password, true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
