package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.KieuDang;
import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LotGiayService {

    // get all
    List<LotGiay> selectAllKieuDangExist();

    //tim id
    Optional<LotGiay> findById(Integer id);

    //add
    <S extends LotGiay> S save(S entity);

    //phanTrang
    Page<LotGiay> selectAllKieuDangExist(Pageable pageable);

    //xoa
    void delete(LotGiay entity);

    Page<LotGiay> getKieuDangExistByName(String tenLotGiay, Pageable pageable);






}
