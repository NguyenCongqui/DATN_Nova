package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class BestSellerDTO {

    private Integer idSanPham;
    private String tenSanPham;
    private String anhChinhs;
    private Double giaBan;
    private int soLuong;
    private String thuongHieu;
    private String kieuDang;
    private String chatLieu;
    private String kichCo;
    private String mauSac;
    private String dayGiay;
    private String deGiay;
    private String lotGiay;
    private Long doanhThu;
    private BigDecimal tongDoanhthu;

    public BestSellerDTO(Integer idSanPham, Long doanhThu) {
        this.idSanPham = idSanPham;
        this.doanhThu = doanhThu;
    }
}
