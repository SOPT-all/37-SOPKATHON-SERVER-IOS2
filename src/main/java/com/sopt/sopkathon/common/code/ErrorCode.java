package com.sopt.sopkathon.common.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseCode {

	//TODO : 핵심 기능 or 도메인 별로 에러를 분류해주세요! ex. 검색, 필터링, 회원가입/로그인, 등등..

	// 공통
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 자원을 찾을 수 없습니다."),
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허가되지 않은 HTTP 메소드입니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),

	// 마이페이지
	ALREADY_SAVED(HttpStatus.CONFLICT, "이미 저장된 서핑 스팟입니다."),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보를 읽어올 수 없습니다."),
	SAVED_PLACE_NOT_FOUND(HttpStatus.NOT_FOUND, "저장되지 않은 장소 정보입니다.")
	;

	private final HttpStatus httpStatus;
	private final String message;

}

