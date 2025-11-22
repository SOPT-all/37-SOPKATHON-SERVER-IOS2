package com.sopt.sopkathon.common.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

	//TODO : 핵심 기능 or 도메인 별로 에러를 분류해주세요! ex. 검색, 필터링, 회원가입/로그인, 등등..

	// 공통
	OK(HttpStatus.OK, "요청이 성공했습니다."),
	CREATED(HttpStatus.CREATED, "생성에 성공했습니다."),

	// 마이페이지
	MY_PLACE_SAVED(HttpStatus.OK, "서핑 스팟 저장 성공")

	;

	private final HttpStatus httpStatus;
	private final String message;

}


