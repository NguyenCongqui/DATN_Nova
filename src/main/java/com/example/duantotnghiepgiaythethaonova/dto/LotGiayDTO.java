package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotGiayDTO extends BaseDTO<BaseDTO> {
    private Integer IdLotGiay;

    private int soSanPhamChiTietCungLotGiay;

    private String TenLotGiay;
    private Boolean DaXoa;
}
