package com.wineder.controller;

import com.wineder.domain.WinePlace;
import com.wineder.dto.WinePlaceRequest;
import com.wineder.dto.WinePlaceResponse;
import com.wineder.service.WinederService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/wineder")
public class WinederController {

    private final WinederService winederService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody WinePlaceRequest request) {

        try {
            WinePlaceResponse saved = winederService.register(request);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<WinePlaceResponse> getAllPlaces() {
        return winederService.findAll();
    }

    @GetMapping("/{id}")
    public WinePlaceResponse getPlace(@PathVariable Long id) {
        return winederService.findById(id);
    }
}
