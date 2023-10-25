package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MauSacDTO extends BaseDTO<BaseDTO> {
    private Integer IdMauSac;

    private String TenMauSac;

    private String MaMauSac;

    private Boolean DaXoa;
}
