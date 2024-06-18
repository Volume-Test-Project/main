package com.volumeTest.volume.common.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

      http
              .csrf().disable() // CSRF 비활성화 (REST API에서는 일반적으로 불필요)
              .cors(withDefaults()) // CORS 기본 설정으로 활성화
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안 함
              .and()
              .authorizeHttpRequests(authorize -> authorize
                      .requestMatchers(PathRequest.toH2Console()).permitAll() // H2 콘솔 접근 허용
                      .requestMatchers(HttpMethod.POST, "/member/signup").permitAll() // 회원 가입 허용
                      .requestMatchers(HttpMethod.POST, "/member/login").permitAll() // 로그인 허용
                      .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
              )
              .headers(headers -> headers
                      .frameOptions().sameOrigin() // H2 콘솔 프레임 접근 허용
              )
              .formLogin().disable() // 폼 로그인 비활성화
              .httpBasic().disable(); // HTTP Basic 인증 비활성화

      return http.build();
    }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();

    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.addAllowedOrigin("http://localhost:3000"); // 허용할 Origin 추가
    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    corsConfiguration.addAllowedHeader("Authorization");
    corsConfiguration.addAllowedHeader("Content-Type");

    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
