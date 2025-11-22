package com.sopt.sopkathon.place.api;

import static com.sopt.sopkathon.common.code.SuccessCode.SPOT_DETAIL_FETCHED;

import com.sopt.sopkathon.place.dto.PlaceDetailResponse;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/places/{placeId}/detail")
	public ResponseEntity<ApiResponseBody<PlaceDetailResponse>> getPlaceDetail(
		@PathVariable Long placeId
	) {
		PlaceDetailResponse placeDetailResponse = placeService.getDetailPlace(placeId);
		return ResponseEntity.status(200)
			.body(ApiResponseBody.onSuccess(SPOT_DETAIL_FETCHED, placeDetailResponse));
	}
}