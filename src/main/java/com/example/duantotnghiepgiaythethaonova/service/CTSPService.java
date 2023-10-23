package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamProductManageDTO;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CTSPService {
    List<ChiTietSanPham> getLstSanPhamChiTietExist();

    Optional<ChiTietSanPham> findById(Integer id);

    List<ChiTietSanPham> getLstSanPhamChiTietBySanPhamId(Integer id);

    Page<ChiTietSanPham> searchProductDetailExist(SPAndSPCTSearchDto data, Pageable pageable);

    void delete(ChiTietSanPham entity);

    <S extends ChiTietSanPham> S save(S entity);

    List<ChiTietSanPham> getLstSanPhamChiTietAddImg(Integer id);

    List<Integer> getLstMauSacBySanPhamId(Integer sanPhamId);

    Optional<ChiTietSanPham> getSanPhamChiTietByMauSacSizeSanPhamId(Integer sanPhamId, Integer mauSacId, Integer kichCoId);

    int selectCountSanPhamChiTietDuplicate(Integer mauSacId, Integer kichCoId, Integer sanPhamId);

    int getCountSanPhamChiTietExistBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId);

    int getCountSanPhamChiTietExistBySanPhamId(Integer sanPhamId);

    int getSumSoLuongBySanPhamId(Integer id);

    Optional<ChiTietSanPham> selectSanPhamChiTietDuplicate(Integer mauSacId, Integer kichCoId, Integer sanPhamId);

   void WritingToExcelProduct(List<SanPhamProductManageDTO> lstDto) throws IOException;
}
