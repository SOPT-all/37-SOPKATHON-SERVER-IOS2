package com.sopt.sopkathon.saved_place.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SunscreenChecklistResponse(
	List<LocalDateTime> checklistTimes
) {}
