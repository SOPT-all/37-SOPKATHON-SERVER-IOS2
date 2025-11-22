package com.sopt.sopkathon.saved_place.api;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopt.sopkathon.common.code.SuccessCode;
import com.sopt.sopkathon.common.dto.ApiResponseBody;
import com.sopt.sopkathon.saved_place.dto.SavePlaceRequest;
import com.sopt.sopkathon.saved_place.dto.SavePlaceResponse;
import com.sopt.sopkathon.saved_place.dto.SavedPlaceListItemResponse;
import com.sopt.sopkathon.saved_place.service.SavedPlaceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my/places")
public class MyPlaceController {

	private final SavedPlaceService savedPlaceService;

	@PostMapping
	public ApiResponseBody<SavePlaceResponse> saveMyPlace(@Validated @RequestBody SavePlaceRequest request) {
		SavePlaceResponse response = savedPlaceService.saveMyPlace(request.placeId());
		return ApiResponseBody.onSuccess(SuccessCode.MY_PLACE_SAVED, response);
	}

	@GetMapping
	public ApiResponseBody<List<SavedPlaceListItemResponse>> getMyPlaces() {
		List<SavedPlaceListItemResponse> data = savedPlaceService.getMySavedPlaces();
		return ApiResponseBody.onSuccess(SuccessCode.MY_SAVED_PLACES_FETCHED, data);
	}
}