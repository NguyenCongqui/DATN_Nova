package com.example.duantotnghiepgiaythethaonova.service;

//import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LotGiayService {
    List<LotGiay> selectAllKichCoExist();

    Optional<LotGiay> findById(Integer id);

    <S extends LotGiay> S save(S entity);

    List<LotGiay> selectAllKichCoBySanPhamId(Integer sanPhamId);

    Page<LotGiay> selectAllKichCoExist(Pageable pageable);

    Page<LotGiay> getKichCoExistByName(String tenKichCo, Pageable pageable);

    void delete(LotGiay entity);
}
