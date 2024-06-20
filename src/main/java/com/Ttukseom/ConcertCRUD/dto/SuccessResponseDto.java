package com.Ttukseom.ConcertCRUD.dto;

import lombok.Getter;

@Getter
public class SuccessResponseDto {
    private boolean success;
    public  SuccessResponseDto(boolean success) {
        this.success = success;
    }
}
