package com.wineder.util;

public class DistanceUtil {
    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double WALKING_SPEED_MPS = 1.11; // 4km/h ≈ 1.11 m/s

    // Haversine 공식을 이용한 직선거리(meter) 계산
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {


        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c * 1000; // meter
    }

    // 거리(meter)에 따른 도보 소요시간(sec) 리턴
    public static int estimateWalkingDuration(double distanceMeter) {
        return (int) (distanceMeter / WALKING_SPEED_MPS); // 초 단위 반환
    }
}
