package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung_VaiTroDTO{

    private Integer idNguoiDung_VaiTro;

    private NguoiDungDTO nguoiDungDTO;

    private VaiTroDTO vaiTroDTO;
}
