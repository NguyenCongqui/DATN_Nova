package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.HinhAnh;

import java.util.List;
import java.util.Optional;

public interface HinhAnhService {
    void delete(HinhAnh entity);

    Optional<HinhAnh> findById(Integer id);

    <S extends HinhAnh> S save(S entity);

    List<HinhAnh> getLstHinhAnhMauSacBySanPhamId(Integer sanPhamId);

    List<Integer> getDistinctMauSacInHinhAnhBySanPhamId(Integer sanPhamId);

    List<HinhAnh> getHinhAnhChinhExist();

    Optional<HinhAnh> getHinhAnhByName(String tenAnh);

    Optional<HinhAnh> getHinhAnhChinhBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId);

    List<HinhAnh> getHinhAnhChinhBySanPhamIdAndMauSacIds(Integer sanPhamId, List<Integer> mauSacIds);

    int getCountHinhAnhChinhBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId);

    List<HinhAnh> getHinhAnhChinhBySanPhamId(Integer sanPhamId);

    List<HinhAnh> getHinhAnhBySanPhamIdAndMauSacIds(Integer sanPhamId, List<Integer> mauSacIds);

    List<HinhAnh> getHinhAnhBySanPhamId(Integer sanPhamId);

    List<HinhAnh> getHinhAnhBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId);

    int getCountHinhAnhBySanPhamId(Integer sanPhamId);

    int getCountHinhAnhChoPhepThemBySanPhamId(Integer sanPhamId);
}
