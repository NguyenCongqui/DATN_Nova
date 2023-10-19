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
    private Integer doanhSo;
    private String kieuDang;
    private String chatLieu;
    private String kichCo;
    private String mauSac;

    public BestSellerDTO(Integer id, Integer doanhSo){
        this.idSanPham = id;
        this.doanhSo = doanhSo;
        this.anhChinhs = "";
        this.tenSanPham = "";
        this.giaBan = 0D;
    }
}
