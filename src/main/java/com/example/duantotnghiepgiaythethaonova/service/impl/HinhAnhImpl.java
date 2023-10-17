package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.HinhAnh;
import com.example.duantotnghiepgiaythethaonova.repository.HinhAnhRepository;
import com.example.duantotnghiepgiaythethaonova.service.HinhAnhService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HinhAnhImpl implements HinhAnhService {
    @Autowired
    private HinhAnhRepository hinhAnhRepository;


    @Override
    public void delete(HinhAnh entity) {
        hinhAnhRepository.delete(entity);
    }

    @Override
    public Optional<HinhAnh> findById(Integer id) {
        return hinhAnhRepository.findById(id);
    }

    @Override
    public <S extends HinhAnh> S save(S entity) {
        return hinhAnhRepository.save(entity);
    }

    @Override
    public List<HinhAnh> getLstHinhAnhMauSacBySanPhamId(Integer sanPhamId) {
        return hinhAnhRepository.getLstHinhAnhMauSacBySanPhamId(sanPhamId);
    }

    @Override
    public List<Integer> getDistinctMauSacInHinhAnhBySanPhamId(Integer sanPhamId) {
        return hinhAnhRepository.getDistinctMauSacInHinhAnhBySanPhamId(sanPhamId);
    }

    @Override
    public List<HinhAnh> getHinhAnhChinhExist() {
        return hinhAnhRepository.getHinhAnhChinhExist();
    }

    @Override
    public Optional<HinhAnh> getHinhAnhByName(String tenAnh) {
        return hinhAnhRepository.getHinhAnhByName(tenAnh);
    }

    @Override
    public Optional<HinhAnh> getHinhAnhChinhBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId) {
        return hinhAnhRepository.getHinhAnhChinhBySanPhamIdAndMauSacId(sanPhamId, mauSacId);
    }

    @Override
    public List<HinhAnh> getHinhAnhChinhBySanPhamIdAndMauSacIds(Integer sanPhamId, List<Integer> mauSacIds) {
        return hinhAnhRepository.getHinhAnhChinhBySanPhamIdAndMauSacIds(sanPhamId, mauSacIds);
    }

    @Override
    public int getCountHinhAnhChinhBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId) {
        return hinhAnhRepository.getCountHinhAnhChinhBySanPhamIdAndMauSacId(sanPhamId, mauSacId);
    }

    @Override
    public List<HinhAnh> getHinhAnhChinhBySanPhamId(Integer sanPhamId) {
        return hinhAnhRepository.getHinhAnhChinhBySanPhamId(sanPhamId);
    }

    @Override
    public List<HinhAnh> getHinhAnhBySanPhamIdAndMauSacIds(Integer sanPhamId, List<Integer> mauSacIds) {
        return hinhAnhRepository.getHinhAnhBySanPhamIdAndMauSacIds(sanPhamId, mauSacIds);
    }

    @Override
    public List<HinhAnh> getHinhAnhBySanPhamId(Integer sanPhamId) {
        return hinhAnhRepository.getHinhAnhBySanPhamId(sanPhamId);
    }

    @Override
    public List<HinhAnh> getHinhAnhBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId) {
        return hinhAnhRepository.getHinhAnhBySanPhamIdAndMauSacId(sanPhamId, mauSacId);
    }

    @Override
    public int getCountHinhAnhBySanPhamId(Integer sanPhamId) {
        return hinhAnhRepository.getCountHinhAnhBySanPhamId(sanPhamId);
    }

    @Override
    public int getCountHinhAnhChoPhepThemBySanPhamId(Integer sanPhamId) {
        return hinhAnhRepository.getCountHinhAnhChoPhepThemBySanPhamId(sanPhamId);
    }
}
