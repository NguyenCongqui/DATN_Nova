package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeGiayDTO extends BaseDTO<DeGiayDTO> {

    private Integer IdDeGiay;

    private int soSanPhamChiTietCungDeGiay;
    private String TenDeGiay;
    private Boolean DaXoa;
}
