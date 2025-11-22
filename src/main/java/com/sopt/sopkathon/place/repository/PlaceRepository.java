package com.sopt.sopkathon.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sopt.sopkathon.place.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
