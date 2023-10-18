package com.example.duantotnghiepgiaythethaonova.dto.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HinhAnhSanPhamChiTietDTO {
	private Integer sanPhamChiTietId;
	
	private String anhChinh;
	
	private Integer mauSacId;
}
