package com.wineder.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "wine_place",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "address"})
        }
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WinePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;        // 이름
    private String address;     // 주소
    private Double lng;         // 위도
    private Double lat;         // 경도
    private String phone;       // 전화번호
    private String instaUrl;    // 인스타그램 링크
    private String website;     // 공식사이트 링크
}
