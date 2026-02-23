package com.example.advanced_spring;

import com.example.advanced_spring.proxy.config.v1_proxy.ConcreteProxyConfig;
import com.example.advanced_spring.trace.logtrace.LogTrace;
import com.example.advanced_spring.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(ConcreteProxyConfig.class)
@SpringBootApplication(scanBasePackages = "com.example.advanced_spring.proxy.config.v1_proxy")
public class AdvancedSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedSpringApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
