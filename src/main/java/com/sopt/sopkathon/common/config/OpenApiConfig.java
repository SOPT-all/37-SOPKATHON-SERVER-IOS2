package com.sopt.sopkathon.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI suntAOpenAPI() {
		return new OpenAPI()
			.addServersItem(new Server().url("http://localhost:8080").description("Local"))
			.addServersItem(new Server().url("http://13.125.29.35:8080").description("Main Server"))
			.info(new Info()
				.title("SUNTA, 살타 할아버지 Swagger")
				.description("""
				Sopkathon IOS 2팀, SUNTA API 문서입니다.
				- 저장 스팟 관리(마이): 저장/조회/삭제/선크림 체크리스트
				- 장소 검색: 키워드 검색, 인기 스팟 조회
				""")
				.version("v1.0.0")
				.contact(new Contact().name("SUNTA Backend").email("backend@sunta.example"))
				.license(new License().name("MIT"))
			)
			.addTagsItem(new Tag().name("MyPlace").description("내가 저장한 스팟 관리 API"))
			.addTagsItem(new Tag().name("Sunscreen").description("선크림 활성화/상태/체크리스트 API"))
			.addTagsItem(new Tag().name("Place").description("스팟 검색/인기 스팟 API"));
	}
}