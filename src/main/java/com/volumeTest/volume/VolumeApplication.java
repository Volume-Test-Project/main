package com.volumeTest.volume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VolumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolumeApplication.class, args);
	}

}
