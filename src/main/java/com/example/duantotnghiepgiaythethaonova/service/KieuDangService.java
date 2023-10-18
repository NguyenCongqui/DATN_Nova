package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.KieuDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface KieuDangService {

    // get all
    List<KieuDang> selectAllKieuDangExist();

    //tim id
    Optional<KieuDang> findById(Integer id);

    //add
    <S extends KieuDang> S save(S entity);

    //phanTrang
    Page<KieuDang> selectAllKieuDangExist(Pageable pageable);

    //xoa
    void delete(KieuDang entity);

    Page<KieuDang> getKieuDangExistByName(String tenKieuDang, Pageable pageable);
}
