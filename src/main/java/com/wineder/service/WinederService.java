package com.wineder.service;

import com.wineder.domain.WinePlace;
import com.wineder.dto.WinePlaceRequest;
import com.wineder.dto.WinePlaceResponse;
import com.wineder.repository.WinederRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new EntityNotFoundException("해당 장소가 없습니다: " + id));
        return WinePlaceResponse.fromEntity(winePlace);
    }
}
