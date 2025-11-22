package com.sopt.sopkathon.saved_place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sopt.sopkathon.saved_place.domain.SavedPlace;

public interface SavedPlaceRepository extends JpaRepository<SavedPlace, Long> {
	boolean existsByUser_IdAndPlace_Id(Long userId, Long placeId);
}