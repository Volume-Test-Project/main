package com.volumeTest.volume.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            .csrf().disable()
            .cors(withDefaults())   // corsConfigurationSource라는 빈을 찾아서 등록해줌
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않음 (JWT 사용)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .and()
            .authorizeHttpRequests(authorize ->
                    authorize.anyRequest().permitAll()
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
