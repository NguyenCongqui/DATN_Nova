package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeGiayDTO extends BaseDTO<DeGiayDTO> {

    private Integer IdDeGiay;

    private int soSanPhamCungChatLieu;
    private String TenDeGiay;
    private Boolean DaXoa;
}
