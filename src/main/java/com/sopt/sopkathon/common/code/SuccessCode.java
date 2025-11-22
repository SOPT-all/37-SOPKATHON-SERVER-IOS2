package com.sopt.sopkathon.common.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

	//TODO : 핵심 기능 or 도메인 별로 에러를 분류해주세요! ex. 검색, 필터링, 회원가입/로그인, 등등..

	// Article
	ARTICLE_GET_SUCCESS(HttpStatus.OK, "오늘의 추천 아티클 조회 성공"),

	// 공통
	OK(HttpStatus.OK, "요청이 성공했습니다."),
	CREATED(HttpStatus.CREATED, "생성에 성공했습니다."),

	// 마이페이지
	MY_PLACE_SAVED(HttpStatus.OK, "서핑 스팟 저장 성공"),
	MY_SAVED_PLACES_FETCHED(HttpStatus.OK, "저장한 서핑 스팟 목록 조회 성공"),
	MY_PLACE_DELETED(HttpStatus.OK, "저장된 스팟 삭제 성공"),
	SUNSCREEN_ACTIVATION_SUCCESS(HttpStatus.OK, "선크림 바르기 활성화를 성공했습니다."),
	SUNSCREEN_ACTIVATION_STATUS_FETCHED(HttpStatus.OK, "선크림 바르기 활성화 여부 조회를 성공했습니다."),

	// 장소 검색
	PLACE_SEARCH_FETCHED(HttpStatus.OK, "서핑 스팟 검색 결과 조회를 성공했습니다."),

	// 선크림 체크리스트
	SUNSCREEN_CHECKLIST_FETCHED(HttpStatus.OK, "선크림 바르는 시간 목록 조회를 성공했습니다.")
	;

	private final HttpStatus httpStatus;
	private final String message;

}


