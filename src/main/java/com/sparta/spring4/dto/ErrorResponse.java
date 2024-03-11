package com.sparta.spring4.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final int statusCode;
    private final String errorMessage;
}