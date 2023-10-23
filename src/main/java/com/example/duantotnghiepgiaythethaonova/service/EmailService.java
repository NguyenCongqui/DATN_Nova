package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.HoaDon;
import com.example.duantotnghiepgiaythethaonova.entity.HoaDonChiTiet;
import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import com.example.duantotnghiepgiaythethaonova.repository.HinhAnhRepository;
import com.example.duantotnghiepgiaythethaonova.repository.HoaDonChiTietRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

//    public void sendOrderConfirmationEmail(String recipientEmail,
//                                           HoaDon hoaDon) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setFrom("datn.ud15@gmail.com");
//        helper.setTo(recipientEmail);
//        helper.setSubject("Đơn hàng của bạn đã được đặt thành công");
//
//        long idHoaDon = hoaDon.getId();
//
//        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
//        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(idHoaDon);
//        List<String> tenAnhChinhList = new ArrayList<>();
//        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
//            MauSac mauSac = hoadonCT.getSanPhamChiTiet().getMauSac();
//            Long sanPhamId = hoadonCT.getSanPhamChiTiet().getSanPham().getId();
//
//            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getId(), sanPhamId);
//            tenAnhChinhList.add(tenAnhChinh);
//        }
//
//        // Tạo context và thêm thông tin đơn hàng vào mẫu email
//        Context context = new Context();
//        context.setVariable("id", hoaDon.getId());
//        context.setVariable("nguoiNhan", hoaDon.getNguoiNhan());
//        context.setVariable("tongTien", hoaDon.getTongTienHoaDon());
//        context.setVariable("hoaDonChiTiet", hoaDon.getHoaDonChiTiets());
//        context.setVariable("hoaDon", hoaDon);
//        context.setVariable("trangThai", hoaDon.getTrangThai().getName());
//        context.setVariable("tenAnhChinhList", tenAnhChinhList);
//        // Thêm các thông tin khác của đơn hàng vào context nếu cần
//
//        String emailContent = templateEngine.process("admin/banHang/banHangOnline/guiMail", context);
//
//        helper.setText(emailContent, true);
//
//        mailSender.send(message);
//    }

}
