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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlaceController {
	private final PlaceService placeService;

	@GetMapping("/places")
	public ApiResponseBody<List<PlaceSearchResponse>> searchPlaces(@RequestParam(required = false) String keyword) {
		List<PlaceSearchResponse> data = placeService.searchPlaces(keyword);
		return ApiResponseBody.onSuccess(SuccessCode.PLACE_SEARCH_FETCHED, data);
	}

	@GetMapping("/search/hotplace")
	public ApiResponseBody<List<HotPlaceResponse>> getHotPlaces() {
		List<HotPlaceResponse> data = placeService.getHotPlaces();
		return ApiResponseBody.onSuccess(SuccessCode.HOT_PLACES_FETCHED, data);
	}
}