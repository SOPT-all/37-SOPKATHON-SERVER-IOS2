package com.sopt.sopkathon.saved_place.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopt.sopkathon.common.code.ErrorCode;
import com.sopt.sopkathon.common.exception.CustomException;
import com.sopt.sopkathon.place.domain.Place;
import com.sopt.sopkathon.place.repository.PlaceRepository;
import com.sopt.sopkathon.saved_place.domain.SavedPlace;
import com.sopt.sopkathon.saved_place.dto.SavePlaceResponse;
import com.sopt.sopkathon.saved_place.dto.SavedPlaceListItemResponse;
import com.sopt.sopkathon.saved_place.repository.SavedPlaceRepository;
import com.sopt.sopkathon.user.domain.User;
import com.sopt.sopkathon.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SavedPlaceService {
	private final SavedPlaceRepository savedPlaceRepository;
	private final PlaceRepository placeRepository;
	private final UserRepository userRepository;

	private static final Long FIXED_USER_ID = 1L;

	@Transactional
	public SavePlaceResponse saveMyPlace(Long placeId) {
		if (savedPlaceRepository.existsByUser_IdAndPlace_Id(FIXED_USER_ID, placeId)) {
			throw new CustomException(ErrorCode.ALREADY_SAVED);
		}

		Place place = placeRepository.findById(placeId)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

		User user = userRepository.getReferenceById(FIXED_USER_ID);

		SavedPlace saved = savedPlaceRepository.save(
			SavedPlace.builder()
				.user(user)
				.place(place)
				.build()
		);

		return new SavePlaceResponse(saved.getId(), place.getName());
	}

	@Transactional(readOnly = true)
	public List<SavedPlaceListItemResponse> getMySavedPlaces() {
		if (!userRepository.existsById(FIXED_USER_ID)) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}

		return savedPlaceRepository.findAllByUser_Id(FIXED_USER_ID).stream()
			.map(sp -> {
				var p = sp.getPlace();
				return new SavedPlaceListItemResponse(
					sp.getId(),
					p.getId(),
					p.getName(),
					p.getImageUrl(),
					p.getLatitude(),
					p.getLongitude()
				);
			})
			.collect(Collectors.toList());
	}

	@Transactional
	public void deleteMyPlace(Long savedPlaceId) {
		if (!userRepository.existsById(FIXED_USER_ID)) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		// 존재하지 않아도 실패 응답 정의가 없으므로 idempotent 처리
		savedPlaceRepository.deleteByIdAndUser_Id(savedPlaceId, FIXED_USER_ID);
	}
}