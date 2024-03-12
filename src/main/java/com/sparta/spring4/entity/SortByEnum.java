package com.sparta.spring4.entity;

public enum SortByEnum {
    lectureName, price, createdAt;

    public String getFieldName() {
        return switch (this) {
            case lectureName -> "lectureName";
            case price -> "price";
            default -> "lectureId";
        };
    }
}
