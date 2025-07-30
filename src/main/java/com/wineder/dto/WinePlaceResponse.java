package com.wineder.dto;

import com.wineder.domain.WinePlace;
import lombok.*;

@Getter
@AllArgsConstructor
public class WinePlaceResponse {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String instaUrl;
    private String website;

    public static WinePlaceResponse fromEntity(WinePlace place) {
        return new WinePlaceResponse(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getPhone(),
                place.getInstaUrl(),
                place.getWebsite()
        );
    }
}
