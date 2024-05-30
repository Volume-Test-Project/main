//package com.volumeTest.volume.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.auditing.DateTimeProvider;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
//public class JpaAuditingConfig {
//
//  @Bean
//  public AuditorAware<String> auditorProvider() {
//    return () -> Optional.of("test@gmail.com"); // TODO: 테스트용 사용자 정보
//  }
//
//  @Bean
//  public DateTimeProvider dateTimeProvider() {
//    return () -> Optional.of(LocalDateTime.now());
//  }
//}
