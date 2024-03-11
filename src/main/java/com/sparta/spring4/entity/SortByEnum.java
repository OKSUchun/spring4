package com.sparta.spring4.entity;

public enum SortByEnum {
    lectureName, price, createdAt;

    public String getFieldName() {
        switch (this) {
            case lectureName:
                return "lectureName";
            case price:
                return "price";
            default:
                return "lectureId";
        }
    }
}
