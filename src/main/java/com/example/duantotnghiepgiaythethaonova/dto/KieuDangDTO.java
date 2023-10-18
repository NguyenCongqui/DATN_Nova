package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KieuDangDTO extends BaseDTO<KieuDangDTO>{

    private String tenKieuDang;

    private Boolean daXoa;


}
