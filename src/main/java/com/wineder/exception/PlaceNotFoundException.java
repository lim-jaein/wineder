package com.wineder.exception;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(Long id) {
        super("해당 장소가 없습니다: " + id);
    }
}
