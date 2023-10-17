package com.example.duantotnghiepgiaythethaonova.dto.composite;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamTaiQuayDTO {

    private List<ShowSanPhamdto> lstShowSanPhamTaiQuayDTO;
    private Integer hoaDonId;

    @NotNull(message = "Kích cỡ không được để trống")
    @Min(value = 0, message = "Kích cỡ không được nhỏ hơn 0")
    private Integer kichCoId;

    @NotNull(message = "Màu sắc không được để trống")
    @Min(value = 0, message = "Màu sắc không được nhỏ hơn 0")
    private Integer mauSacId;

    private Integer sanPhamIdSPTQ;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
    private Integer SoLuong;
}
