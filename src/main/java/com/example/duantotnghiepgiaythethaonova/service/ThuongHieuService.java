package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import com.example.duantotnghiepgiaythethaonova.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ThuongHieuService {
    List<ThuongHieu> selectAllLoaiHangExist();

    Optional<ThuongHieu> findById(Integer id);

    <S extends ThuongHieu> S save(S entity);

    Page<ThuongHieu> selectAllThuongHieuExist(Pageable page);

    Page<ThuongHieu> getThuongHieuExistByName(String tenThuongHieu, Pageable page);

    void delete(ThuongHieu entity);

}
