package com.sopt.sopkathon.saved_place.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record SavePlaceRequest(
	@NotNull Long placeId
) {}
