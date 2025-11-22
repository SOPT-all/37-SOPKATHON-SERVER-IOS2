package com.sopt.sopkathon.place.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sopt.sopkathon.place.domain.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
	List<Place> findTop3ByOrderByViewCountDesc();
	List<Place> findByNameContainingIgnoreCase(String keyword);
}
