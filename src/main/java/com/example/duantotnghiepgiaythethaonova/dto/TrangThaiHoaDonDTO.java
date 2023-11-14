package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrangThaiHoaDonDTO extends BaseDTO<TrangThaiHoaDonDTO> {

    private Integer hoaDonId;

    private int trangThai;
}
