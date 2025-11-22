package com.sopt.sopkathon.place.dto;

public record PlaceDetailResponse(
    Long placeId,
    String name,
    double uv,
    String description,
    double latitude,
    double longitude
) {

}
