package com.jordy.books.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@RequiredArgsConstructor
@EnableWebSecurity // 1)
public class SecurityConfig extends WebSecurityEnablerConfiguration {
    private final CustomOAuth2UserService customOAuth2UserService;
}
