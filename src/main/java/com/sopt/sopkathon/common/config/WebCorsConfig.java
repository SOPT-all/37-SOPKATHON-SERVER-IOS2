package com.sopt.sopkathon.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(
				"http://localhost:8080",
				"http://13.125.29.35:8080"
			)
			.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
			.allowCredentials(true);
	}
}