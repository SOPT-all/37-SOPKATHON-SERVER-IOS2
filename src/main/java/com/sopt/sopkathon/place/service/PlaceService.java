package com.sopt.sopkathon.place.service;

import com.sopt.sopkathon.place.dto.HomeUvResponse;
import com.sopt.sopkathon.place.dto.PlaceDetailResponse;
import com.sopt.sopkathon.uv_info.domain.UvInfo;
import com.sopt.sopkathon.uv_info.service.OpenUVService;
import com.sopt.sopkathon.uv_info.service.UvInfoService;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopt.sopkathon.common.code.ErrorCode;
import com.sopt.sopkathon.common.exception.CustomException;
import com.sopt.sopkathon.place.domain.Place;
import com.sopt.sopkathon.place.dto.HotPlaceResponse;
import com.sopt.sopkathon.place.dto.PlaceSearchResponse;
import com.sopt.sopkathon.place.repository.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {
	private final PlaceRepository placeRepository;
	private final OpenUVService openUVService;
	private final UvInfoService uvInfoService;

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

	@Transactional(readOnly = true)
	public PlaceDetailResponse getDetailPlace(Long placeId) {
		Place place = placeRepository.findById(placeId)
			.orElseThrow(() -> new CustomException(ErrorCode.BAD_REQUEST));
		log.info("place:{}", place);
		double uv = openUVService.getCurrentUv(place.getLatitude(), place.getLongitude());
		log.info("uv:{}", uv);
		UvInfo uvInfo = uvInfoService.getUvInfoByValue(uv);
		log.info("uvInfo:{} ", uvInfo);
		return PlaceDetailResponse.of(place, uv, uvInfo);
	}

	@Transactional(readOnly = true)
	public HomeUvResponse getHomeUv(double latitude, double longitude) {
		double uv = openUVService.getCurrentUv(latitude, longitude);
		UvInfo uvInfo = uvInfoService.getUvInfoByValue(uv);

		return HomeUvResponse.of(uv, uvInfo);
	}

}
