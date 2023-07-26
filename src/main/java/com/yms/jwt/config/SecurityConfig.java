package com.yms.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
public class SecurityConfig {
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth //authorizeHttpRequests는 HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다는 의미
        .requestMatchers(new AntPathRequestMatcher("/api/hello")).permitAll() // /api/hello에 대한 요청은 인증없이 접근을 허용하겠다는 의미
        .anyRequest().authenticated()); // 나머지 요청들은 모두 인증되어야 한다는 의미
    
    return http.build();
  }
}
