package com.sopt.sopkathon.place.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sopt.sopkathon.common.code.SuccessCode;
import com.sopt.sopkathon.common.dto.ApiResponseBody;
import com.sopt.sopkathon.place.dto.HotPlaceResponse;
import com.sopt.sopkathon.place.dto.PlaceSearchResponse;
import com.sopt.sopkathon.place.service.PlaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Place", description = "스팟 검색/인기 스팟 API")
public class PlaceController {
	private final PlaceService placeService;

	@Operation(summary = "스팟 검색", description = "키워드가 이름에 포함된 장소 목록을 반환합니다.")
	@GetMapping("/places")
	public ApiResponseBody<List<PlaceSearchResponse>> searchPlaces(@RequestParam(required = false) String keyword) {
		List<PlaceSearchResponse> data = placeService.searchPlaces(keyword);
		return ApiResponseBody.onSuccess(SuccessCode.PLACE_SEARCH_FETCHED, data);
	}

	@Operation(summary = "인기 스팟 상위 3개", description = "조회수 기준 상위 3개 스팟을 반환합니다.")
	@GetMapping("/search/hotplace")
	public ApiResponseBody<List<HotPlaceResponse>> getHotPlaces() {
		List<HotPlaceResponse> data = placeService.getHotPlaces();
		return ApiResponseBody.onSuccess(SuccessCode.HOT_PLACES_FETCHED, data);
	}
}