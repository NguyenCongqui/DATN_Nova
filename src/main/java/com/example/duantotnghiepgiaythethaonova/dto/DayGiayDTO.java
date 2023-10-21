package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayGiayDTO extends BaseDTO<BaseDTO> {
    private Integer IdDayGiay;
    private String TenDayGiay;
    private Boolean DaXoa;
}
