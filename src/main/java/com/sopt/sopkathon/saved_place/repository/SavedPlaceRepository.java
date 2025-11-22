package com.sopt.sopkathon.saved_place.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sopt.sopkathon.saved_place.domain.SavedPlace;

public interface SavedPlaceRepository extends JpaRepository<SavedPlace, Long> {
	boolean existsByUser_IdAndPlace_Id(Long userId, Long placeId);

	@EntityGraph(attributePaths = {"place"})
	List<SavedPlace> findAllByUser_Id(Long userId);
}