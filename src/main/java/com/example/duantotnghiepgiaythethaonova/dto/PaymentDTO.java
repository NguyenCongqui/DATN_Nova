package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTO {
    private String orderCode;

    private long amount;

    private String diaChiGiaoHang;

    private String nguoiNhan;

    private String emailNguoiNhann;

    private BigDecimal tienGiamGia;

    private String nameGiamGia;

    private String sdtNguoiNhan;

    private String ghiChu;

    private BigDecimal tienShipHD;
}
