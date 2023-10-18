package com.example.duantotnghiepgiaythethaonova.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface MailService {

    public SimpleMailMessage sendMail(String from , String to , String subject , String text);
}
