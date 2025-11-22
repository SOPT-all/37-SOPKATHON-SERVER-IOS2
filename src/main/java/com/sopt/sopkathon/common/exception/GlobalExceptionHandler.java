package com.sopt.sopkathon.common.exception;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sopt.sopkathon.common.code.ErrorCode;
import com.sopt.sopkathon.common.dto.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse<Void>> handleGeneralException(CustomException e) {
		log.warn("[CustomException] Message = {}", e.getErrorStatus().getMessage());
		return ResponseEntity
			.status(e.getErrorStatus().getHttpStatus())
			.body(ApiResponse.onFailure(e.getErrorStatus()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
		String errorMessage = e.getBindingResult().getFieldErrors().stream()
			.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
			.collect(Collectors.joining(", "));

		log.warn("[Validation Exception] : {}", errorMessage);

		return ResponseEntity
			.status(ErrorCode.BAD_REQUEST.getHttpStatus())
			.body(ApiResponse.onFailure(ErrorCode.BAD_REQUEST, errorMessage));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
		log.error("[Internal Server Error] : ", e);
		return ResponseEntity
			.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
			.body(ApiResponse.onFailure(ErrorCode.INTERNAL_SERVER_ERROR));
	}
}
