package com.example.duantotnghiepgiaythethaonova.dto.composite;

import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSanPhamdto {
    private Integer sanPhamId;

    private String tenSanPham;

    private BigDecimal gia;

    private Integer loaiSanPhamId;

    private List<String> anhChinhs;

    private List<KichCo> lstKichCo;

    private List<MauSac> lstMauSac;

    private int soLuongConLai;
}

