package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu,Integer> {
    @Query(value = "SELECT * FROM ChatLieu c WHERE  c.DaXoa = false ORDER BY c.IdChatLieu DESC ",nativeQuery = true)
    Page<ChatLieu> selectAllChatLieuExist(Pageable pageable);

    @Query(value = "SELECT * FROM ChatLieu c WHERE  c.DaXoa = false ORDER BY c.IdChatLieu DESC ",nativeQuery = true)
    List<ChatLieu> selectAllChatLieuExist();

    @Query(value = "SELECT * FROM ChatLieu c WHERE  c.DaXoa = false AND c.TenChatLieu like %:TenChatLieu% ORDER BY c.IdChatLieu DESC ",nativeQuery = true)
    Page<ChatLieu> getChatLieuExistByName(@Param("TenChatLieu") String tenChatLieu, Pageable pageable);

}
