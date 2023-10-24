package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import com.example.duantotnghiepgiaythethaonova.repository.SanPhamRepository;
import com.example.duantotnghiepgiaythethaonova.repository.SanPhamSearchRepository;
import com.example.duantotnghiepgiaythethaonova.service.SanPhamService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class SanPhamServiceImpl implements SanPhamService {

    private final SanPhamRepository sanPhamRepository;

//    private final SanPhamSearchRepository sanPhamSearchRepository;


    @Override
    public <S extends SanPham> S save(S entity) {
        entity.setDaXoa(false);
        return sanPhamRepository.save(entity);
    }

    @Override
    public Optional<SanPham> findById(Integer id) {
        return sanPhamRepository.findById(id);
    }

    @Override
    public Page<SanPham> getSanPhamExist(Pageable pageable) {
        return sanPhamRepository.getSanPhamExist(pageable);
    }

//    @Override
//    public Page<SanPham> searchProductExist(SPAndSPCTSearchDto data, Pageable pageable) {
//        return sanPhamSearchRepository.searchProductExist(data, pageable);
//    }

    @Override
    public void delete(SanPham entity) {
        entity.setDaXoa(true);
        sanPhamRepository.save(entity);
    }

    @Override
    public Page<SanPham> showSanPhamExistHomePage(Pageable pageable) {
        return sanPhamRepository.showSanPhamExistHomePage(pageable);
    }

    @Override
    public int selectCountSanPhamByLoaiSanPhamId(Integer loaiSanPhamId) {
        return sanPhamRepository.selectCountSanPhamByLoaiSanPhamId(loaiSanPhamId);
    }

    @Override
    public int selectCountSanPhamByPhongCachId(Integer phongCachId) {
        return sanPhamRepository.selectCountSanPhamByPhongCachId(phongCachId);
    }

    @Override
    public int selectCountSanPhamByChatLieuId(Integer chatLieuId) {
        return sanPhamRepository.selectCountSanPhamByChatLieuId(chatLieuId);
    }
}
