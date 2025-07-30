package com.wineder.service;

import com.wineder.domain.WinePlace;
import com.wineder.dto.WinePlaceRequest;
import com.wineder.repository.WinederRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WinederService {

    private final WinederRepository winederRepository;

    public WinePlace register(WinePlaceRequest request) {

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

        return winederRepository.save(winePlace);
    }

    public List<WinePlace> getAll() {
        return winederRepository.findAll();
    }
}
