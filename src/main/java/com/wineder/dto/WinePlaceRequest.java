package com.wineder.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WinePlaceRequest {

    private String name;
    private String address;

    private Double lng;
    private Double lat;

    private Integer minPrice;
    private Integer maxPrice;

    private String phone;
    private String instaUrl;
    private String website;
}
