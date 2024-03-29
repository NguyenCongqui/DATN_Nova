package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.config.BcryptedPasswordEncoderConfig;
import com.example.duantotnghiepgiaythethaonova.entity.AuthenticationProvider;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
import com.example.duantotnghiepgiaythethaonova.entity.VaiTro;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDungRepository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDung_VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.repository.VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.service.MailService;
import com.example.duantotnghiepgiaythethaonova.service.NhanVienService;
import com.example.duantotnghiepgiaythethaonova.util.RanDomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    VaiTroRepository vaiTroRepository;

    @Autowired
    NguoiDung_VaiTroRepository nguoiDung_vaiTroRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;


    @Override
    public Map<String, Object> themMoiNhanVien(String email, String diaChi, String soDienThoai, String ho, String ten, String anhNhanVien, Integer ChucVu, Map<String, Object> response) {
        char[] password = RanDomUtil.ranDomNumber();

        //check rỗng
        if (email.isEmpty() || diaChi.isEmpty() || soDienThoai.isEmpty() || ho.isEmpty() || ten.isEmpty()) {
            response.put("warning", true);
            response.put("error", "Vui lòng nhập đầy đủ thông tin!");
            return response;

        }

        //Check trùng email
        NguoiDung existingNguoiDungByEmail = nguoiDungRepository.findByEmail(email);
        if (existingNguoiDungByEmail != null) {
            response.put("warning", true);
            response.put("error", "Email này đã tồn tại!");
            return response;
        }

        if (!isValidEmail(email)) {
            response.put("warning", true);
            response.put("error", "Email không đúng định dạng!");
            return response;
        }

        // Kiểm tra trùng số điện thoại
        NguoiDung existingNguoiDungBySoDienThoai = nguoiDungRepository.findBysoDienThoai(soDienThoai);
        if (existingNguoiDungBySoDienThoai != null) {
            response.put("warning", true);
            response.put("error", "Số điện thoại đã tồn tại!");
            return response;
        }
        //
        Integer maxId = nguoiDungRepository.getMaxId();
        Integer id;
        String ma;
        if (maxId != null) {
            id = maxId + 1;
            ma = "NV" + id;
        } else {
            id = 1;
            ma = "NV" + id;
        }

        // Lưu mã người dùng vào đối tượng nguoiDung
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setMaNguoiDung(ma);
        nguoiDung.setTenNguoiDung(ho + " " + ten);
        nguoiDung.setEmail(email);
        nguoiDung.setDiaChi(diaChi);
        nguoiDung.setSoDienThoai(soDienThoai);
        nguoiDung.setDaXoa(false);
        nguoiDung.setTrangThai(0);
        nguoiDung.setAnhNhanVien(anhNhanVien);
        nguoiDung.setMatKhau(passwordEncoder.encode(new String(password)));
        nguoiDung.setNgayTao(new Date());
        nguoiDung.setNguoiTao("Linh Create");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        ZonedDateTime now = ZonedDateTime.now();
        String time = f.format(now);
        mailService.sendMail("datn.novashoes@gmail.com",
                nguoiDung.getEmail(),
                "Bạn đã đăng ký thành công lúc " + time + " !",
                "Họ tên : " + nguoiDung.getTenNguoiDung() + "\n" +
                        "Số điện thoại  : " + nguoiDung.getSoDienThoai() + "\n"
                        + "Mật khẩu : " + new String(password) + "\n" +
                        "Nếu bạn có bất kì câu hỏi nào, vui lòng liên hệ với chúng tôi: datn.novashoes@gmail.com" + "\n" +
                        "Hoặc địa chỉ : 48 Ngõ 99 Cầu Diễn, Từ Liêm, Hà Nội."
        );

        // Kiểm tra xem đã lưu thành công vào cơ sở dữ liệu hay chưa
        NguoiDung savedNguoiDung = nguoiDungRepository.save(nguoiDung);

        if (savedNguoiDung != null) {
            Optional<VaiTro> optVaiTro = vaiTroRepository.findById(ChucVu);
            if (optVaiTro.isPresent()) {
                VaiTro vaiTro = optVaiTro.get();
                NguoiDung_VaiTro nguoiDungVaiTro = new NguoiDung_VaiTro();
                nguoiDungVaiTro.setVaiTro(vaiTro);
                nguoiDungVaiTro.setNguoiDung(nguoiDung);
                nguoiDung_vaiTroRepository.save(nguoiDungVaiTro);
            }
            response.put("warning", false);
            response.put("success", true);
        } else {
            response.put("warning", true);
            response.put("error", "Lỗi khi lưu người dùng vào cơ sở dữ liệu!");
        }


        return response;
    }

    @Override
    public Map<String, Object> ChinhSuaNhanVien(Integer idNhanVien, String email, String diaChi, String soDienThoai, String hoTen, String anhNhanVien, Integer ChucVu, Map<String, Object> response) {
        Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(idNhanVien);

        if (!optionalNguoiDung.isPresent()) {
            response.put("success", false);
            response.put("error", "Không tìm thấy người dùng với ID: " + idNhanVien);
            return response;
        }

        NguoiDung nguoiDung = optionalNguoiDung.get();

        // Kiểm tra dữ liệu rỗng
        if (hoTen.isEmpty() || email.isEmpty() || diaChi.isEmpty() || soDienThoai.isEmpty()) {
            response.put("success", false);
            response.put("error", "Vui lòng nhập đầy đủ thông tin!");
            return response;
        }

        // Kiểm tra trùng email
        NguoiDung existingNguoiDungByEmail = nguoiDungRepository.findByEmail(email);
        if (existingNguoiDungByEmail != null && !existingNguoiDungByEmail.getIdNguoiDung().equals(nguoiDung.getIdNguoiDung())) {
            response.put("success", false);
            response.put("error", "Email đã tồn tại!");
            return response;
        }

        // Kiểm tra trùng số điện thoại
        NguoiDung existingNguoiDungBySoDienThoai = nguoiDungRepository.findBysoDienThoai(soDienThoai);
        if (existingNguoiDungBySoDienThoai != null && !existingNguoiDungBySoDienThoai.getIdNguoiDung().equals(nguoiDung.getIdNguoiDung())) {
            response.put("success", false);
            response.put("error", "Số điện thoại đã tồn tại!");
            return response;
        }

        nguoiDung.setEmail(email);
        nguoiDung.setDiaChi(diaChi);
        nguoiDung.setSoDienThoai(soDienThoai);
        nguoiDung.setTenNguoiDung(hoTen);
        nguoiDung.setAnhNhanVien(anhNhanVien);
        nguoiDung.setTrangThai(0);
        nguoiDung.setNgayCapNhat(new Date());
        nguoiDung.setNguoiCapNhat("Linh Update");

        // Thay đổi mật khẩu nếu cần
        // nguoiDung.setMatKhau(passwordEncoder.encode("new_password"));

        nguoiDungRepository.save(nguoiDung);

        Optional<VaiTro> optVaiTro = vaiTroRepository.findById(ChucVu);
        if (optVaiTro.isPresent()) {
            VaiTro vaiTro = optVaiTro.get();
            NguoiDung_VaiTro nguoiDungVaiTro = nguoiDung_vaiTroRepository.findByNguoiDungId(nguoiDung.getIdNguoiDung());
            nguoiDungVaiTro.setVaiTro(vaiTro);
            nguoiDung_vaiTroRepository.save(nguoiDungVaiTro);
        }

        response.put("success", true);
        response.put("message", "Chỉnh sửa thông tin nhân viên thành công !");

        return response;
    }

    @Override
    public ResponseEntity<String> XoaNhanVien(Integer id) {
        Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(id);
        if (optionalNguoiDung.isPresent()) {
            NguoiDung nguoiDung = optionalNguoiDung.get();
            nguoiDung.setDaXoa(true);
            nguoiDungRepository.save(nguoiDung);
            String message = "Xóa thành công!";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy người dùng !";
            return ResponseEntity.notFound().build();
        }

    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
