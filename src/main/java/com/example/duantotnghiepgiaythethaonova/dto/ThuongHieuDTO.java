package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ThuongHieuDTO {
    
    private Integer IdThuongHieu;

    private int soSanPhamCungThuongHieu;

    private String tenThuongHieu;

    private String tenThuongHieuSearch;

    private Boolean daXoa;

}
