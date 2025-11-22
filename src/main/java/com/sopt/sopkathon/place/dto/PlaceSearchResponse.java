package com.sopt.sopkathon.place.dto;

public record PlaceSearchResponse(
	Long placeId,
	String name,
	String imageUrl
) {}