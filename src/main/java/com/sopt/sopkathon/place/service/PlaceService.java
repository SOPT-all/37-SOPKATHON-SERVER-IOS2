package com.sopt.sopkathon.place.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopt.sopkathon.place.domain.Place;
import com.sopt.sopkathon.place.dto.PlaceSearchResponse;
import com.sopt.sopkathon.place.repository.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceService {
	private final PlaceRepository placeRepository;

	@Transactional(readOnly = true)
	public List<PlaceSearchResponse> searchPlaces(String keyword) {
		String q = keyword == null ? "" : keyword.trim();
		List<Place> places = q.isEmpty()
			? List.of()
			: placeRepository.findByNameContainingIgnoreCase(q);

		return places.stream()
			.map(p -> new PlaceSearchResponse(p.getId(), p.getName(), p.getImageUrl()))
			.toList();
	}
}
