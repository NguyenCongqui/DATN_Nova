package com.example.duantotnghiepgiaythethaonova.security.oauth;

import com.example.duantotnghiepgiaythethaonova.entity.AuthenticationProvider;
import com.example.duantotnghiepgiaythethaonova.entity.KhachHang;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.repository.GioHangRepository;
import com.example.duantotnghiepgiaythethaonova.repository.KhachHangRepository;
import com.example.duantotnghiepgiaythethaonova.service.KhachHangService;
import com.example.duantotnghiepgiaythethaonova.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private GioHangRepository gioHangRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String fullname = oAuth2User.getFullname();
        NguoiDung nguoiDung = nguoiDungService.findByEmail(email);
        KhachHang khachHang = khachHangRepository.findByEmail(email);

        if (nguoiDung == null) {
            nguoiDungService.taoNguoiDungSauKhiDangNhapVoiMangXaHoiThanhCong(email, fullname, AuthenticationProvider.GOOGLE);
            khachHangService.taoMoiKhachHang(email, fullname, AuthenticationProvider.GOOGLE);
        } else {
            nguoiDungService.capNhatNguoiDungSauKhiDangNhapVoiMangXaHoiThanhCong(email, fullname, AuthenticationProvider.GOOGLE);
            khachHangService.capNhatKhachHang(email, fullname, AuthenticationProvider.GOOGLE);
        }

        redirectStrategy.sendRedirect(request, response, "/khachhang/home");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
