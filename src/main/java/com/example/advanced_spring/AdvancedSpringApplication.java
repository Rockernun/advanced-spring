package com.example.advanced_spring;

import com.example.advanced_spring.proxy.v2.config.AppV2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AppV2Config.class)
@SpringBootApplication(scanBasePackages = "com.example.advanced_spring.proxy")
public class AdvancedSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedSpringApplication.class, args);
	}

}
