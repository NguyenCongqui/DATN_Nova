package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDungRepository;
import com.example.duantotnghiepgiaythethaonova.security.NguoiDungDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class NguoiDungDetailsService implements UserDetailsService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmailAndTrangThaiAndDaXoa(email);
        if(nguoiDung == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với email này !");
        }
        return new NguoiDungDetails(nguoiDung);
    }

}
