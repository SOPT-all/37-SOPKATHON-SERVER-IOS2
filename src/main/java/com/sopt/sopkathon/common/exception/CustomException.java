package com.sopt.sopkathon.common.exception;

import com.sopt.sopkathon.common.code.BaseCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
	private final BaseCode errorStatus;
}
