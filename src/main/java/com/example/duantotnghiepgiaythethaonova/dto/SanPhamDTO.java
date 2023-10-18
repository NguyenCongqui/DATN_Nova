package com.example.duantotnghiepgiaythethaonova.dto;

import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.HinhAnh;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
    private Integer idSanPhamDTO;

    private Integer kieuDangId;

    private Integer chatLieuId;

    private Integer loaiHangId;

    private Integer phongCachId;

    private String tenSanPham;

    private BigDecimal giaBan;

    private BigDecimal gia;

    private String moTa;

    private Boolean daXoa;

    private String tenChatLieu;

    private String TenKieuDang;

    private String tenPhongCach;

    private String tenLoaiSanPham;

    private List<HinhAnh> hinhAnhs;

    private String tenHinhAnh;
    
    private List<ChiTietSanPham> sanPhamChiTiets;
}
