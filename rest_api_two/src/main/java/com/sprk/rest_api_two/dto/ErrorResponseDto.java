package com.sprk.rest_api_two.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;

    private int errorCode;

    private String errorMessage;

    private LocalDateTime errorTime;

}
