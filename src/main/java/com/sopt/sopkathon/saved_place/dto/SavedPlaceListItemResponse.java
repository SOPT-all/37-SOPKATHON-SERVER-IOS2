package com.sopt.sopkathon.saved_place.dto;

public record SavedPlaceListItemResponse(
	Long savedPlaceId,
	Long placeId,
	String name,
	String imageUrl,
	double latitude,
	double longitude
) {}