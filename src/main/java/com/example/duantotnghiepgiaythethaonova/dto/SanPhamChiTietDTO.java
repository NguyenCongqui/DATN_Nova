package com.example.duantotnghiepgiaythethaonova.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO extends BaseDTO<BaseDTO> {

	private Integer sanPhamId;

	private Integer kichCoId;

	private Integer mauSacId;

	private Integer dayGiayId;

	private Integer deGiayId;

	private Integer lotGiayId;


	@NotNull(message = "Số lượng không được để trống")
	@Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
	private Integer soLuong;

	private Boolean coHienThi;

	private Boolean daXoa;

	private String tenKichCo ;

	private String tenMauSac ;

	private String tenDayGiay ;

	private String tenDeGiay ;

	private String tenLotGiay ;

	private SanPhamDTO sanPhamDTO ;

}
