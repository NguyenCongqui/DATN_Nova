package com.example.duantotnghiepgiaythethaonova.dto.composite;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamTaiQuayDTO {

    private List<ShowSanPhamdto> lstShowSanPhamTaiQua;
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

    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá không được nhỏ hơn 0")
    private BigDecimal gia;

    private Date ngayTao;
}
