package com.wineder.repository;


import com.wineder.domain.WinePlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WinederRepository extends JpaRepository<WinePlace, Long> {
    Optional<WinePlace> findByNameAndAddress(String name, String address);
}