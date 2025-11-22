package com.sopt.sopkathon.place.dto;

import com.sopt.sopkathon.place.domain.Place;
import com.sopt.sopkathon.uv_info.domain.UvInfo;

public record PlaceDetailResponse(
    Long placeId,
    String name,
    double uv,
    String description,
    double latitude,
    double longitude
) {

    public static PlaceDetailResponse of(Place place, double uv, UvInfo uvInfo) {
        return new PlaceDetailResponse(
            place.getId(),
            place.getName(),
            uv,
            uvInfo.getMessage(),
            place.getLatitude(),
            place.getLongitude()
        );
    }
}
