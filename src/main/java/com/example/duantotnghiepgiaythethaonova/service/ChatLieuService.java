package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChatLieuService {
    Page<ChatLieu> selectAllChatLieuExist(Pageable pageable);
    //phan trang

    Optional<ChatLieu> findById(Integer id);
    //tim theo id

    <S extends  ChatLieu> S save(S entity);
    //them

    List<ChatLieu> selectAllChatLieuExist();
    //goi cac chat lieu ton tai

    Page<ChatLieu> getChatLieuExistByName(String tenChatLieu, Pageable pageable);
    //tim chat lieu theo ten , phan trang

    void delete(ChatLieu entity);
    //xoa
}
