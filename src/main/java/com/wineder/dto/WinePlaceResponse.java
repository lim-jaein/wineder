package com.wineder.dto;

import com.wineder.domain.WinePlace;
import com.wineder.util.DistanceUtil;
import lombok.*;

@Getter
@AllArgsConstructor
public class WinePlaceResponse {

    private Long id;

    private String name;
    private String address;

    private Integer minPrice;
    private Integer maxPrice;

    private String phone;
    private String instaUrl;
    private String website;

    private double distance;    // 실시간 거리정보
    private int duration;       // 도보 소요시간

    @Builder
    private WinePlaceResponse(Long id, String name, String address, int minPrice, int maxPrice, String phone,
                              String instaUrl, String website, double distance, int duration) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.phone = phone;
        this.instaUrl = instaUrl;
        this.website = website;
        this.distance = distance;
        this.duration = duration;
    }

    public static WinePlaceResponse from(WinePlace place, double userLat, double userLng) {
        double distance = DistanceUtil.calculateDistance(userLat, userLng, place.getLat(), place.getLng());
        int duration = DistanceUtil.estimateWalkingDuration(distance);

        return WinePlaceResponse.builder()
                .id(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .minPrice(place.getMinPrice())
                .maxPrice(place.getMaxPrice())
                .phone(place.getPhone())
                .instaUrl(place.getInstaUrl())
                .website(place.getWebsite())
                .distance(distance)
                .duration(duration)
                .build();
    }
}
