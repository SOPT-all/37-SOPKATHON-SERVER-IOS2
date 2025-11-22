package com.sopt.sopkathon.place.api;

import static com.sopt.sopkathon.common.code.SuccessCode.HOME_UV_FETCHED;
import static com.sopt.sopkathon.common.code.SuccessCode.SPOT_DETAIL_FETCHED;

import com.sopt.sopkathon.common.code.SuccessCode;
import com.sopt.sopkathon.common.dto.ApiResponseBody;
import com.sopt.sopkathon.place.dto.HomeUvResponse;
import com.sopt.sopkathon.place.dto.HotPlaceResponse;
import com.sopt.sopkathon.place.dto.PlaceDetailResponse;
import com.sopt.sopkathon.place.dto.PlaceSearchResponse;
import com.sopt.sopkathon.place.service.PlaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@Operation(summary = "서핑 스팟 상세 조회", description = "특정 서핑 스팟의 자외선 정보를 반환합니다.")
	@GetMapping("/places/{placeId}/detail")
	public ApiResponseBody<PlaceDetailResponse> getPlaceDetail(
		@PathVariable Long placeId
	) {
		PlaceDetailResponse placeDetailResponse = placeService.getDetailPlace(placeId);
		return ApiResponseBody.onSuccess(SPOT_DETAIL_FETCHED, placeDetailResponse);
	}

	@Operation(summary = "현재 위치의 자외선 관련 정보 조회", description = "홈화면의 현재 위치 자외선 정보를 반환합니다.")
	@GetMapping("/home/uv")
	public ApiResponseBody<HomeUvResponse> getHomeUV(
		@RequestParam(name = "latitude") double latitude,
		@RequestParam(name = "longitude") double longitude
	) {
		HomeUvResponse homeUvResponse = placeService.getHomeUv(latitude, longitude);

		return ApiResponseBody.onSuccess(HOME_UV_FETCHED, homeUvResponse);
	}
}