package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DayGiayService {
    List<DayGiay> selectAllKichCoExist();

    Optional<DayGiay> findById(Integer id);

    <S extends DayGiay> S save(S entity);

    List<DayGiay> selectAllKichCoBySanPhamId(Integer sanPhamId);

    Page<DayGiay> selectAllKichCoExist(Pageable pageable);

    Page<DayGiay> getKichCoExistByName(String tenKichCo, Pageable pageable);

    void delete(DayGiay entity);
}
