package com.sopt.sopkathon.place.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopt.sopkathon.common.code.ErrorCode;
import com.sopt.sopkathon.common.exception.CustomException;
import com.sopt.sopkathon.place.domain.Place;
import com.sopt.sopkathon.place.dto.HotPlaceResponse;
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

	@Transactional(readOnly = true)
	public List<HotPlaceResponse> getHotPlaces() {
		return placeRepository.findTop3ByOrderByViewCountDesc().stream()
			.map(p -> new HotPlaceResponse(
				p.getId(), p.getName(), p.getImageUrl(), p.getLatitude(), p.getLongitude()
			))
			.toList();
	}

	@Transactional
	public void increaseViewCount(Long placeId) {
		Place place = placeRepository.findById(placeId)
			.orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST));
		place.increaseViewCount();
	}

}
