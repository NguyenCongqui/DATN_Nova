package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KieuDangDTO extends BaseDTO<BaseDTO> {
	private Integer IdKieuDang;

	private int soSanPhamCungKieuDang;
	
	private String TenKieuDang;
	
	private Boolean DaXoa;
}
