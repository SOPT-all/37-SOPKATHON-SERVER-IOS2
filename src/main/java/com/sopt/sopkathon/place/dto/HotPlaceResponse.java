package com.sopt.sopkathon.place.dto;

public record HotPlaceResponse(
	Long placeId,
	String name,
	String imageUrl,
	double latitude,
	double longitude
) {}
