package com.courseonline.platform.online_education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class OnlineEducationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineEducationApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}	


}
