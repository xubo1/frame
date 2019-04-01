package com.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.frame.dao")
@SpringBootApplication
public class FrameApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameApplication.class, args);
	}

}

