package com.example.duantotnghiepgiaythethaonova.config;

import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private NguoiDung_VaiTroRepository nguoiDungVaiTroRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    TrangThaiRepository trangThaiRepository;

    @Bean
    public PasswordEncoder passwordEncorder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // vai trò
        if (vaiTroRepository.findByTenVaiTro("ADMIN") == null) {
            VaiTro vaiTro = new VaiTro();
            vaiTro.setTenVaiTro("Quản trị viên");
            vaiTro.setCode("ADMIN");
            vaiTroRepository.save(vaiTro);
        }

        if (vaiTroRepository.findByTenVaiTro("STAFF") == null) {
            VaiTro vaiTro = new VaiTro();
            vaiTro.setTenVaiTro("Nhân viên");
            vaiTro.setCode("STAFF");
            vaiTroRepository.save(vaiTro);
        }

        if (vaiTroRepository.findByTenVaiTro("CUSTOMER") == null) {
            VaiTro vaiTro = new VaiTro();
            vaiTro.setTenVaiTro("Khách hàng");
            vaiTro.setCode("CUSTOMER");
            vaiTroRepository.save(vaiTro);
        }

        //Trạng thái hóa đơn
        if (trangThaiRepository.findByTenTrangThai("Chờ xác nhận") == null) {
            TrangThai trangThai = new TrangThai();
            trangThai.setName("Chờ xác nhận");
            trangThaiRepository.save(trangThai);
        }

        if (trangThaiRepository.findByTenTrangThai("Chờ giao hàng") == null) {
            TrangThai trangThai = new TrangThai();
            trangThai.setName("Chờ giao hàng");
            trangThaiRepository.save(trangThai);
        }

        if (trangThaiRepository.findByTenTrangThai("Đang giao hàng") == null) {
            TrangThai trangThai = new TrangThai();
            trangThai.setName("Đang giao hàng");
            trangThaiRepository.save(trangThai);
        }

        if (trangThaiRepository.findByTenTrangThai("Đã giao hàng") == null) {
            TrangThai trangThai = new TrangThai();
            trangThai.setName("Đã giao hàng");
            trangThaiRepository.save(trangThai);
        }

        if (trangThaiRepository.findByTenTrangThai("Đã hủy") == null) {
            TrangThai trangThai = new TrangThai();
            trangThai.setName("Đã hủy");
            trangThaiRepository.save(trangThai);
        }

        if (trangThaiRepository.findByTenTrangThai("Đang bán") == null) {
            TrangThai trangThai = new TrangThai();
            trangThai.setName("Đang bán");
            trangThaiRepository.save(trangThai);
        }

        if (trangThaiRepository.findByTenTrangThai("Đã thanh toán") == null) {
            TrangThai trangThai = new TrangThai();
            trangThai.setName("Đã thanh toán");
            trangThaiRepository.save(trangThai);
        }

        if (trangThaiRepository.findByTenTrangThai("Hủy đơn") == null) {
            TrangThai trangThai = new TrangThai();
            trangThai.setName("Hủy đơn");
            trangThaiRepository.save(trangThai);
        }

        // Admin account
        if (nguoiDungRepository.findByEmail("admin@gmail.com") == null) {
            NguoiDung admin = new NguoiDung();
            admin.setEmail("admin@gmail.com");
            admin.setMatKhau(passwordEncorder().encode("123456"));
            admin.setTenNguoiDung("Admin");
            admin.setSoDienThoai("0368028006");
            admin.setTrangThai(0);
            admin.setDaXoa(false);
            admin.setMaNguoiDung("NV01");
            admin.setAuthProvider(AuthenticationProvider.LOCAL);
            nguoiDungRepository.save(admin);
            List<NguoiDung_VaiTro> listNguoiDungVaiTro = new ArrayList<>();
            NguoiDung_VaiTro nguoiDungVaiTroAdmin = new NguoiDung_VaiTro();
            nguoiDungVaiTroAdmin.setNguoiDung(nguoiDungRepository.findByEmail("admin@gmail.com"));
            nguoiDungVaiTroAdmin.setVaiTro(vaiTroRepository.findByTenVaiTro("ADMIN"));
            listNguoiDungVaiTro.add(nguoiDungVaiTroAdmin);
            for (NguoiDung_VaiTro nguoiDungVaiTro : listNguoiDungVaiTro) {
                nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
            }
        }
        if (khachHangRepository.findByEmail("admin@gmail.com") == null) {
            KhachHang admin = new KhachHang();
            admin.setEmail("admin@gmail.com");
            admin.setMatKhau(passwordEncorder().encode("123456"));
            admin.setHoTen("Nguyễn Linh");
            admin.setSoDienThoai("0368028003");
            admin.setTrangThai(1);
            admin.setSoLanMua(0);
            admin.setAuthProvider(AuthenticationProvider.LOCAL);
            admin = khachHangRepository.save(admin);
            GioHang gioHang = new GioHang(null,null, null, admin, 1, 0, null);
            gioHangRepository.save(gioHang);
        }

        // Member account
        if (nguoiDungRepository.findByEmail("staff@gmail.com") == null) {
            NguoiDung staff = new NguoiDung();
            staff.setEmail("staff@gmail.com");
            staff.setMatKhau(passwordEncorder().encode("123456"));
            staff.setTenNguoiDung("Staff");
            staff.setSoDienThoai("0981479425");
            staff.setTrangThai(0);
            staff.setDaXoa(false);
            staff.setMaNguoiDung("NV02");
            staff.setAuthProvider(AuthenticationProvider.LOCAL);
            nguoiDungRepository.save(staff);
            List<NguoiDung_VaiTro> listNguoiDungVaiTro = new ArrayList<>();
            NguoiDung_VaiTro nguoiDungVaiTroSTAFF = new NguoiDung_VaiTro();
            nguoiDungVaiTroSTAFF.setNguoiDung(nguoiDungRepository.findByEmail("staff@gmail.com"));
            nguoiDungVaiTroSTAFF.setVaiTro(vaiTroRepository.findByTenVaiTro("STAFF"));
            listNguoiDungVaiTro.add(nguoiDungVaiTroSTAFF);
            for (NguoiDung_VaiTro nguoiDungVaiTro : listNguoiDungVaiTro) {
                nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
            }
        }
        if (khachHangRepository.findByEmail("staff@gmail.com") == null) {
            KhachHang staff = new KhachHang();
            staff.setEmail("staff@gmail.com");
            staff.setMatKhau(passwordEncorder().encode("123456"));
            staff.setHoTen("Staff");
            staff.setSoDienThoai("0981479425");
            staff.setTrangThai(1);
            staff.setSoLanMua(0);
            staff.setAuthProvider(AuthenticationProvider.LOCAL);
            staff = khachHangRepository.save(staff);
            GioHang gioHang = new GioHang(null,null, null, staff, 1, 0, null);
            gioHangRepository.save(gioHang);
        }
        if (nguoiDungRepository.findByEmail("customer@gmail.com") == null) {
            NguoiDung member = new NguoiDung();
            member.setEmail("customer@gmail.com");
            member.setMatKhau(passwordEncorder().encode("123456"));
            member.setTenNguoiDung("Customer");
            member.setSoDienThoai("0967978324");
            member.setTrangThai(0);
            member.setDaXoa(false);
            member.setMaNguoiDung("NV03");
            member.setAuthProvider(AuthenticationProvider.LOCAL);
            nguoiDungRepository.save(member);
            List<NguoiDung_VaiTro> listNguoiDungVaiTro = new ArrayList<>();
            NguoiDung_VaiTro nguoiDungVaiTroCUSTOMER = new NguoiDung_VaiTro();
            nguoiDungVaiTroCUSTOMER.setNguoiDung(nguoiDungRepository.findByEmail("customer@gmail.com"));
            nguoiDungVaiTroCUSTOMER.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
            listNguoiDungVaiTro.add(nguoiDungVaiTroCUSTOMER);
            for (NguoiDung_VaiTro nguoiDungVaiTro : listNguoiDungVaiTro) {
                nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
            }
        }
        if (khachHangRepository.findByEmail("customer@gmail.com") == null) {
            KhachHang customer = new KhachHang();
            customer.setEmail("customer@gmail.com");
            customer.setMatKhau(passwordEncorder().encode("123456"));
            customer.setHoTen("Customer");
            customer.setSoDienThoai("0967978324");
            customer.setTrangThai(1);
            customer.setSoLanMua(0);
            customer.setAuthProvider(AuthenticationProvider.LOCAL);
            customer = khachHangRepository.save(customer);
            GioHang gioHang = new GioHang(null,null, null, customer, 1, 0, null);
            gioHangRepository.save(gioHang);
        }
    }
}