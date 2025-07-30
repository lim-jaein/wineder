package com.wineder.service;

import com.wineder.domain.WinePlace;
import com.wineder.dto.WinePlaceRequest;
import com.wineder.dto.WinePlaceResponse;
import com.wineder.exception.PlaceNotFoundException;
import com.wineder.repository.WinederRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WinederService {

    private final WinederRepository winederRepository;

    public WinePlaceResponse register(WinePlaceRequest request) {

        winederRepository.findByNameAndAddress(request.getName(), request.getAddress())
                .ifPresent(p -> {
                    throw new IllegalArgumentException("이미 등록된 장소입니다.");
                });

        WinePlace winePlace = WinePlace.builder()
                .name(request.getName())
                .address(request.getAddress())
                .lat(request.getLat())
                .lng(request.getLng())
                .minPrice(request.getMinPrice())
                .maxPrice(request.getMaxPrice())
                .phone(request.getPhone())
                .instaUrl(request.getInstaUrl())
                .website(request.getWebsite())
                .build();

        WinePlace saved = winederRepository.save(winePlace);

        return WinePlaceResponse.fromEntity(saved);
    }

    public List<WinePlaceResponse> findAll() {

        return winederRepository.findAll().stream()
                .map(WinePlaceResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public WinePlaceResponse findById(Long id) {

        WinePlace winePlace = winederRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
        return WinePlaceResponse.fromEntity(winePlace);
    }

    public List<WinePlaceResponse> searchByPrice(Integer min, Integer max) {

        return winederRepository.findByPriceRange(min, max).stream()
                .map(WinePlaceResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
