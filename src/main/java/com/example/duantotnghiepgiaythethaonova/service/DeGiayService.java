package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.DeGiay;
import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DeGiayService {

    List<DeGiay> selectAllDeGiayExist();

    Optional<DeGiay> findById(Integer id);

    <S extends DeGiay> S save(S entity);

    List<DeGiay> selectAllDeGiayBySanPhamId(Integer sanPhamId);

    Page<DeGiay> selectAllDeGiayExist(Pageable pageable);

    Page<DeGiay> getDeGiayExistByName(String tenDeGiay, Pageable pageable);

    void delete(DeGiay entity);

}
