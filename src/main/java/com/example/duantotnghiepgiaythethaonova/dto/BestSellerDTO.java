package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BestSellerDTO {

    private Integer idSanPham;
    private String tenSanPham;
    private String anhChinhs;
    private Double giaBan;
    private Long doanhSo;
    private String thuongHieu;
    private String kieuDang;
    private String chatLieu;
    private String kichCo;
    private String mauSac;
    private String dayGiay;
    private String deGiay;
    private String lotGiay;

    public BestSellerDTO(Integer id, Long doanhSo){
        this.idSanPham = id;
        this.doanhSo = doanhSo;
        this.anhChinhs = "";
        this.tenSanPham = "";
        this.giaBan = 0D;
    }
}
