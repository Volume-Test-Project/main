package com.volumeTest.volume.security.config;

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
            .csrf()
            .ignoringRequestMatchers(PathRequest.toH2Console())
            .and()
            .headers()
            .frameOptions().sameOrigin()
            .and()
            .cors(withDefaults())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .and()
            .authorizeHttpRequests(authorize ->
              authorize
                .requestMatchers(PathRequest.toH2Console()).permitAll() // H2 콘솔 접근 허용
                .requestMatchers(HttpMethod.POST, "/member/signup").permitAll() // 회원 가입 허용
                .requestMatchers(HttpMethod.POST, "/member/login").permitAll() // 로그인 허용
                .anyRequest().permitAll() // 나머지 요청도 일단 전체 허용
            );

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
