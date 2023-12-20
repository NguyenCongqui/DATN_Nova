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

import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Optional;
@Service
@AllArgsConstructor
public class SanPhamServiceImpl implements SanPhamService {

    private final SanPhamRepository sanPhamRepository;

    private final SanPhamSearchRepository sanPhamSearchRepository;
//    private static int productCount = 0;
//    public String genMaSP() {
//        productCount++;
//
//        //genMaSP 001 TU TANG
//        String formattedCount = String.format("%03d", productCount);
//
//        String code = "SP" + formattedCount;
//        return code;
//    }
//public  String genMa(){
//    long gen = Instant.now().getEpochSecond();
//    String code = "SP" + gen;
//    return code;
//}
    @Override
    public <S extends SanPham> S save(S entity) {
//        Integer maxId = sanPhamRepository.getMaxId();
//        int idMax;
//        String ma;
//
//        if (maxId != null) {
//            idMax = maxId + 1;
//        } else {
//            idMax = 1;
//        }
//
//        DecimalFormat df = new DecimalFormat("00");
//        String formattedId = df.format(idMax);
//        ma = "SP" + formattedId;
//        entity.setMaSanPham(ma);
//        entity.setMaSanPham(genMa());
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

    @Override
    public Page<SanPham> searchProductExist(SPAndSPCTSearchDto data, Pageable pageable) {
        return sanPhamSearchRepository.searchProductExist(data, pageable);
    }

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
    public int selectCountSanPhamByThuongHieuId(Integer ThuongHieuId) {
        return sanPhamRepository.selectCountSanPhamByThuongHieuId(ThuongHieuId);
    }

    @Override
    public int selectCountSanPhamByKieuDangId(Integer kieuDangId) {
        return sanPhamRepository.selectCountSanPhamByKieuDangId(kieuDangId);
    }

    @Override
    public int selectCountSanPhamByChatLieuId(Integer chatLieuId) {
        return sanPhamRepository.selectCountSanPhamByChatLieuId(chatLieuId);
    }
}
