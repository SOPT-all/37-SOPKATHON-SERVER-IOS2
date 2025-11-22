package com.sopt.sopkathon.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI suntAOpenAPI() {
		return new OpenAPI()
			.addServersItem(new Server().url("http://localhost:8080").description("Local"))
			.addServersItem(new Server().url("http://13.125.29.35:8080").description("Main Server"))
			.info(new Info()
				.title("SUNTA, 살타 할아버지")
				.description("SOPKATHON 서버 API 명세")
				.version("v1.0.0")
			);
	}
}