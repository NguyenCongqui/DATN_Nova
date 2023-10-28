package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhDTO {
	
	private Integer hinhAnhid;
	
	private String tenAnh;
	
	private Boolean laAnhChinh;

	private Boolean coHienThi;
}
