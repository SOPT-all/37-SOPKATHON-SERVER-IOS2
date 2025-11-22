package com.sopt.sopkathon.place.controller;

import com.sopt.sopkathon.common.dto.ApiResponseBody;
import com.sopt.sopkathon.place.dto.PlaceDetailResponse;
import com.sopt.sopkathon.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/place/{placeId}/detail")
    public ResponseEntity<ApiResponseBody<PlaceDetailResponse>> getPlaceDetail(
        @PathVariable Long placeId
    ) {

        return null;
    }
}
