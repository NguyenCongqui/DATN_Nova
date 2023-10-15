package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaiTroDTO extends BaseDTO<VaiTroDTO>{

    private String Code;

    private String TenVaiTro;

    private List<NguoiDung_VaiTroDTO> listNguoiDungVaiTroDTO = new ArrayList<NguoiDung_VaiTroDTO>();
}
