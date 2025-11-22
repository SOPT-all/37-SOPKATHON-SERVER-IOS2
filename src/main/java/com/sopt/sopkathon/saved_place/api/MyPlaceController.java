package com.sopt.sopkathon.saved_place.api;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopt.sopkathon.common.code.SuccessCode;
import com.sopt.sopkathon.common.dto.ApiResponseBody;
import com.sopt.sopkathon.saved_place.dto.SavePlaceRequest;
import com.sopt.sopkathon.saved_place.dto.SavePlaceResponse;
import com.sopt.sopkathon.saved_place.dto.SavedPlaceListItemResponse;
import com.sopt.sopkathon.saved_place.dto.SunscreenActivationStatusResponse;
import com.sopt.sopkathon.saved_place.dto.SunscreenChecklistResponse;
import com.sopt.sopkathon.saved_place.service.SavedPlaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my")
@Tag(name = "MyPlace", description = "내가 저장한 스팟 관리 API")
public class MyPlaceController {

	private final SavedPlaceService savedPlaceService;

	@Operation(summary = "서핑 스팟 저장", description = "사용자(고정 1L)가 선택한 장소를 저장합니다.")
	@PostMapping("/places")
	public ApiResponseBody<SavePlaceResponse> saveMyPlace(@Validated @RequestBody SavePlaceRequest request) {
		SavePlaceResponse response = savedPlaceService.saveMyPlace(request.placeId());
		return ApiResponseBody.onSuccess(SuccessCode.MY_PLACE_SAVED, response);
	}

	@Operation(summary = "저장한 스팟 목록 조회", description = "저장된 스팟 목록을 반환합니다.")
	@GetMapping("/places")
	public ApiResponseBody<List<SavedPlaceListItemResponse>> getMyPlaces() {
		List<SavedPlaceListItemResponse> data = savedPlaceService.getMySavedPlaces();
		return ApiResponseBody.onSuccess(SuccessCode.MY_SAVED_PLACES_FETCHED, data);
	}

	@Operation(summary = "저장한 스팟 삭제", description = "저장 목록에서 지정한 스팟을 삭제합니다.")
	@DeleteMapping("/place/{savedPlaceId}")
	public ApiResponseBody<Void> deleteMyPlace(@PathVariable Long savedPlaceId) {
		savedPlaceService.deleteMyPlace(savedPlaceId);
		return ApiResponseBody.onSuccess(SuccessCode.MY_PLACE_DELETED);
	}

	@Operation(summary = "선크림 바르기 시작", description = "해당 저장 장소의 선크림 활성화를 기록합니다.")
	@PostMapping("/{savedPlaceId}/sunscreen/activation")
	public ApiResponseBody<Void> activateSunscreen(@PathVariable Long savedPlaceId) {
		savedPlaceService.activateSunscreen(savedPlaceId);
		return ApiResponseBody.onSuccess(SuccessCode.SUNSCREEN_ACTIVATION_SUCCESS);
	}

	@Operation(summary = "선크림 활성화 여부 조회", description = "체크리스트 노출 여부(활성화 상태)를 반환합니다.")
	@GetMapping("/{savedPlaceId}/sunscreen/activation")
	public ApiResponseBody<SunscreenActivationStatusResponse> getSunscreenActivationStatus(
		@PathVariable Long savedPlaceId
	) {
		SunscreenActivationStatusResponse data = savedPlaceService.getSunscreenActivationStatus(savedPlaceId);
		return ApiResponseBody.onSuccess(SuccessCode.SUNSCREEN_ACTIVATION_STATUS_FETCHED, data);
	}

	@Operation(summary = "선크림 체크리스트 시간 목록", description = "활성화 시각 기준 0h, +2h, +4h 시간을 반환합니다. 미활성화 시 빈 목록.")
	@GetMapping("/{savedPlaceId}/sunscreen/checklist")
	public ApiResponseBody<SunscreenChecklistResponse> getSunscreenChecklist(
		@PathVariable Long savedPlaceId
	) {
		SunscreenChecklistResponse data = savedPlaceService.getSunscreenChecklist(savedPlaceId);
		return ApiResponseBody.onSuccess(SuccessCode.SUNSCREEN_CHECKLIST_FETCHED, data);
	}
}