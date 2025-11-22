package com.sopt.sopkathon.place.service;

import com.sopt.sopkathon.place.domain.Place;
import com.sopt.sopkathon.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceRepository getPlaceRepository(Long placeId) {
        Place place = placeRepository.findById(placeId).get();

        return placeRepository;
    }
}
