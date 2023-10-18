package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DayGiayService {

    // get all
    List<DayGiay> selectAllKieuDangExist();

    //tim id
    Optional<DayGiay> findById(Integer id);

    //add
    <S extends DayGiay> S save(S entity);

    //phanTrang
    Page<DayGiay> selectAllKieuDangExist(Pageable pageable);

    //xoa
    void delete(DayGiay entity);

    Page<DayGiay> getKieuDangExistByName(String tenDayGiay, Pageable pageable);
}
