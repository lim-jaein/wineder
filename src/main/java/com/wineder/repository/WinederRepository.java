package com.wineder.repository;


import com.wineder.domain.WinePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WinederRepository extends JpaRepository<WinePlace, Long> {
    Optional<WinePlace> findByNameAndAddress(String name, String address);

    // 가격 범위 조건에 해당하는 와인바 목록 조회
    @Query("SELECT w FROM WinePlace w " +
            "WHERE (:min IS NULL OR w.maxPrice >= :min) " +
            "  AND (:max IS NULL OR w.minPrice <= :max) ")
    List<WinePlace> findByPriceRange(@Param("min") Integer min, @Param("max") Integer max);

}