package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.DeGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DeGiayService {
    List<DeGiay> selectAllKichCoExist();

    Optional<DeGiay> findById(Integer id);

    <S extends DeGiay> S save(S entity);

    List<DeGiay> selectAllKichCoBySanPhamId(Integer sanPhamId);

    Page<DeGiay> selectAllKichCoExist(Pageable pageable);

    Page<DeGiay> getKichCoExistByName(String tenKichCo, Pageable pageable);

    void delete(DeGiay entity);

}
