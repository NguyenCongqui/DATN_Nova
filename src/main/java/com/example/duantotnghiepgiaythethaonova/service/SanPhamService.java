package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SanPhamService {
    Optional<SanPham> findById(Integer id);

    <S extends SanPham> S save(S entity);

    Page<SanPham> getSanPhamExist(Pageable pageable);

    Page<SanPham> searchProductExist(SPAndSPCTSearchDto data, Pageable pageable);

    void delete(SanPham entity);

    Page<SanPham> showSanPhamExistHomePage(Pageable pageable);

    int selectCountSanPhamByLoaiSanPhamId(Integer loaiSanPhamId);

    int selectCountSanPhamByPhongCachId(Integer phongCachId);

    int selectCountSanPhamByChatLieuId(Integer chatLieuId);

}
