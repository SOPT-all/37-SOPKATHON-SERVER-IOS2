package com.sopt.sopkathon.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sopt.sopkathon.common.code.BaseCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"status", "message", "data"})
public class ApiResponseBody<T> {

	private final int status;
	private final String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final T data;

	// 성공 시 응답
	public static <T> ApiResponseBody<T> onSuccess(BaseCode code) {
		return new ApiResponseBody<>(code.getHttpStatus().value(), code.getMessage(), null);
	}

	public static <T> ApiResponseBody<T> onSuccess(BaseCode code, T data) {
		return new ApiResponseBody<>(code.getHttpStatus().value(), code.getMessage(), data);
	}

	// 실패 시 응답
	public static <T> ApiResponseBody<T> onFailure(BaseCode code) {
		return new ApiResponseBody<>(code.getHttpStatus().value(), code.getMessage(), null);
	}

	public static <T> ApiResponseBody<T> onFailure(BaseCode code, String customMessage) {
		return new ApiResponseBody<>(code.getHttpStatus().value(), customMessage, null);
	}

}

