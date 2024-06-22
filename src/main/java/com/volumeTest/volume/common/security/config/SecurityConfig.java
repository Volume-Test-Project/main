package com.volumeTest.volume.common.security.config;

import com.volumeTest.volume.common.security.filter.TokenAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@RequiredArgsConstructor
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  TokenAuthenticationFilter tokenAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
//            .csrf((csrf) -> csrf.ignoringRequestMatchers(PathRequest.toH2Console())) // H2 콘솔 접근 허용
            .csrf(AbstractHttpConfigurer::disable) // CSRF 보안 토큰 disable
            .headers((headers) -> headers.frameOptions(FrameOptionsConfig::sameOrigin))

            .cors(withDefaults())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
//            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
    corsConfiguration.addAllowedOrigin("http://localhost:8080"); // 허용할 Origin 추가
    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    corsConfiguration.addAllowedHeader("Authorization");
    corsConfiguration.addAllowedHeader("Content-Type");

    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
