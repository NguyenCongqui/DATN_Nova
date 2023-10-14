package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KichCoDTO extends BaseDTO<BaseDTO> {
    private Integer IdKichCo;
    private String TenKichCo;
    private Boolean DaXoa;
}
