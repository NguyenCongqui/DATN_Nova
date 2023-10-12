package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {

    @Query(value = "SELECT e FROM VaiTro e where e.Code = :vaiTro", nativeQuery = true)
    VaiTro findByTenVaiTro(@Param("vaiTro") String vaiTro);


}
