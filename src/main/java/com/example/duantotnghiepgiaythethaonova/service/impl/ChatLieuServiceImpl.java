package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import com.example.duantotnghiepgiaythethaonova.repository.ChatLieuRepository;
import com.example.duantotnghiepgiaythethaonova.service.ChatLieuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ChatLieuServiceImpl implements ChatLieuService {
    private ChatLieuRepository chatLieuRepository;
    private static Logger logger = LoggerFactory.getLogger(ChatLieuServiceImpl.class);

    @Override
    public Page<ChatLieu> selectAllChatLieuExist(Pageable pageable) {
        logger.info("Select all chat lieu exist");
        return chatLieuRepository.selectAllChatLieuExist(pageable);
    }

    @Override
    public List<ChatLieu> selectAllChatLieuExist() {
        logger.info("Select all chat lieu exist");
        return chatLieuRepository.selectAllChatLieuExist();
    }

    @Override
    public <S extends ChatLieu> S save(S entity) {
        entity.setDaXoa(false);
        return chatLieuRepository.save(entity);
    }

    @Override
    public Optional<ChatLieu> findById(Integer id) {
        return chatLieuRepository.findById(id);
    }

    @Override
    public Page<ChatLieu> getChatLieuExistByName(String tenChatLieu, Pageable pageable) {
        return chatLieuRepository.getChatLieuExistByName(tenChatLieu, pageable);
    }

    @Override
    public void delete(ChatLieu entity) {
        entity.setDaXoa(true);
        chatLieuRepository.save(entity);
    }

}
