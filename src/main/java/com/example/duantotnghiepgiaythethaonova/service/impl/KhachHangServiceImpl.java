package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.convertor.DiaChiConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.KhachHangConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.NguoiDungConvertor;
import com.example.duantotnghiepgiaythethaonova.dto.DiaChiDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KhachHangDTO;
import com.example.duantotnghiepgiaythethaonova.dto.TaiKhoanDTO;
import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import com.example.duantotnghiepgiaythethaonova.service.KhachHangService;
import com.example.duantotnghiepgiaythethaonova.service.MailService;
import com.example.duantotnghiepgiaythethaonova.util.RanDomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private KhachHangConvertor khachHangConvertor;

    @Autowired
    private DiaChiConvertor diaChiConvertor;

    @Autowired
    private NguoiDungConvertor nguoiDungConvertor;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private NguoiDung_VaiTroRepository nguoiDungVaiTroRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<KhachHangDTO> findAllByTrangThaiCoPhanTrang(Integer trangThai, Pageable pageable) {
        List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
        List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
        KhachHangDTO dto = null;
        DiaChiDTO diaChiDTO = null;
        if (trangThai != null) {
            if (trangThai != 2) {
                listKhachHang = khachHangRepository.findAllByTrangThaiCoPhanTrang(trangThai, pageable).getContent();
                for (KhachHang khachHang : listKhachHang) {
                    dto = new KhachHangDTO();
                    dto = khachHangConvertor.toDTO(khachHang);
                    List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
                    for (DiaChi diaChi : khachHang.getListDiaChi()) {
                        diaChiDTO = new DiaChiDTO();
                        diaChiDTO = diaChiConvertor.toDTO(diaChi);
                        listDiaChiDTO.add(diaChiDTO);
                    }
                    dto.setListDiaChiDTO(listDiaChiDTO);
                    listKhachHangDTO.add(dto);
                }
            }
        }
        return listKhachHangDTO;
    }

    @Override
    public List<KhachHangDTO> findAllByInputVaTrangThaiCoPhanTrang(String input, Integer trangThai, Pageable pageable) {
        List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
        List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
        KhachHangDTO dto = null;
        DiaChiDTO diaChiDTO = null;
        if (trangThai != null) {
            if (trangThai != 2) {
                listKhachHang = khachHangRepository
                        .findAllByTrangThaiVaSoDienThoaiCoPhanTrang(trangThai, input, pageable).getContent();
                for (KhachHang khachHang : listKhachHang) {
                    dto = new KhachHangDTO();
                    dto = khachHangConvertor.toDTO(khachHang);
                    List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
                    for (DiaChi diaChi : khachHang.getListDiaChi()) {
                        diaChiDTO = new DiaChiDTO();
                        diaChiDTO = diaChiConvertor.toDTO(diaChi);
                        listDiaChiDTO.add(diaChiDTO);
                    }
                    dto.setListDiaChiDTO(listDiaChiDTO);
                    listKhachHangDTO.add(dto);
                }
            }
        }
        return listKhachHangDTO;
    }

    @Override
    @Transactional
    public void capNhatTrangThaiThanhDangHoatDongTheoMa(Integer[] ids) {
        for (Integer id : ids) {
            KhachHang khachHangEntity = khachHangRepository.findById(id).get();
            NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
            khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(id);
            if (nguoiDungEntity != null) {
                nguoiDungRepository.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDungEntity.getIdNguoiDung());
            }

        }
    }

    @Override
    @Transactional
    public void capNhatTrangThaiThanhKhongHoatDongTheoMa(Integer[] ids) {
        for (Integer id : ids) {
            KhachHang khachHangEntity = khachHangRepository.findById(id).get();
            NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
            khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(id);
            if (nguoiDungEntity != null) {
                nguoiDungRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDungEntity.getIdNguoiDung());
            }

        }
    }

    @Override
    public KhachHangDTO findById(Integer id) {
        KhachHangDTO dto = null;
        KhachHang entity = khachHangRepository.findById(id).get();
        if (entity != null) {
            dto = new KhachHangDTO();
            dto = khachHangConvertor.toDTO(entity);
            List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
            for (DiaChi diaChi : entity.getListDiaChi()) {
                DiaChiDTO diaChiDTO = new DiaChiDTO();
                diaChiDTO = diaChiConvertor.toDTO(diaChi);
                listDiaChiDTO.add(diaChiDTO);
            }
            dto.setListDiaChiDTO(listDiaChiDTO);
        }
        return dto;
    }

    @Override
    @Transactional
    public KhachHangDTO save(KhachHangDTO dto) {
        KhachHangDTO khachHangDTO = new KhachHangDTO();
        char[] password = RanDomUtil.ranDomFull();
        KhachHang khachHangEntity = null;
        NguoiDung nguoiDungEntity = null;
        List<KhachHang> listKhachHang = khachHangRepository.findAll();
        try {
            // Cập nhật
            if (dto.getId() != null) {
                khachHangEntity = khachHangRepository.findById(dto.getId()).get();
                nguoiDungEntity = new NguoiDung();
                NguoiDung oldNguoiDungEntity = nguoiDungRepository.findByEmail(khachHangEntity.getEmail());
                if (!dto.getEmail().equalsIgnoreCase(khachHangEntity.getEmail())) {
                    for (KhachHang khachHang : listKhachHang) {
                        if (khachHang.getEmail().equalsIgnoreCase(dto.getEmail())) {
                            return null;
                        }
                    }
                }
                dto.setSoLanMua(khachHangEntity.getSoLanMua());
                khachHangEntity.setEmail(dto.getEmail());
                khachHangEntity.setHoTen(dto.getHoTen());
                khachHangEntity.setSoDienThoai(dto.getSoDienThoai());
                khachHangEntity.setMatKhau(passwordEncoder.encode(dto.getMatKhau()));
                khachHangEntity.setSoLanMua(khachHangRepository.findById(dto.getId()).get().getSoLanMua());
                khachHangEntity.setTrangThai(dto.getTrangThai());
                oldNguoiDungEntity.setEmail(dto.getEmail());
                oldNguoiDungEntity.setSoDienThoai(dto.getSoDienThoai());
                oldNguoiDungEntity.setTenNguoiDung(dto.getHoTen());
                oldNguoiDungEntity.setMatKhau(passwordEncoder.encode(dto.getMatKhau()));
                if (dto.getTrangThai() == 1) {
                    oldNguoiDungEntity.setTrangThai(0);
                }
                if (dto.getTrangThai() == 0) {
                    oldNguoiDungEntity.setTrangThai(1);
                }
                oldNguoiDungEntity.setMaNguoiDung(oldNguoiDungEntity.getMaNguoiDung());
                nguoiDungEntity = nguoiDungRepository.save(oldNguoiDungEntity);

            } else {
                // Thêm mới
                khachHangEntity = new KhachHang();
                nguoiDungEntity = new NguoiDung();
                for (KhachHang khachHang : listKhachHang) {
                    if (khachHang.getEmail().equalsIgnoreCase(dto.getEmail())) {
                        return null;
                    }
                }
                dto.setSoLanMua(0);
                dto.setMatKhau(new String(password));
                dto.setNgayTao(new Date());
                DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
                ZonedDateTime now = ZonedDateTime.now();
                String time = f.format(now);
                khachHangEntity = khachHangConvertor.toEntity(dto);
                mailService.sendMail("datn.novashoes@gmail.com",
                        dto.getEmail(),
                        "Bạn đã đăng ký tài khoản thành công lúc " + time + " !",
                        "Họ tên : " + dto.getHoTen() + "\n" +
                                "Số điện thoại : " + dto.getSoDienThoai() + "\n" +
                                "Mật khẩu : " + new String(password) + "\n" +
                                "Nếu bạn có bất kì câu hỏi nào, vui lòng liên hệ với chúng tôi: datn.novashoes@gmail.com" + "\n" +
                                "Hoặc địa chỉ : 48 Ngõ 99 Cầu Diễn, Từ Liêm, Hà Nội."
                );


                nguoiDungEntity = nguoiDungConvertor.toEntityByKhachHangDTO(dto);
                nguoiDungEntity = nguoiDungRepository.save(nguoiDungEntity);
                NguoiDung_VaiTro nguoiDungVaiTro = new NguoiDung_VaiTro();
                nguoiDungVaiTro.setNguoiDung(nguoiDungEntity);
                nguoiDungVaiTro.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
                nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
                // LỖI NÊN COMMENT
                GioHang gioHang = new GioHang(null, null, null, khachHangEntity, 1, 0, null);
                gioHangRepository.save(gioHang);

            }
            khachHangEntity = khachHangRepository.save(khachHangEntity);
            khachHangDTO = khachHangConvertor.toDTO(khachHangEntity);
            return khachHangDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<KhachHangDTO> findAll(Pageable pageable) {
        List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
        List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
        KhachHangDTO dto = null;
        DiaChiDTO diaChiDTO = null;

        listKhachHang = khachHangRepository.findAll(pageable).getContent();
        for (KhachHang khachHang : listKhachHang) {
            dto = new KhachHangDTO();
            dto = khachHangConvertor.toDTO(khachHang);
            List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
            for (DiaChi diaChi : khachHang.getListDiaChi()) {
                diaChiDTO = new DiaChiDTO();
                diaChiDTO = diaChiConvertor.toDTO(diaChi);
                listDiaChiDTO.add(diaChiDTO);
            }
            dto.setListDiaChiDTO(listDiaChiDTO);
            listKhachHangDTO.add(dto);
        }

        return listKhachHangDTO;
    }

    @Override
    public List<KhachHangDTO> findAll() {
        List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
        List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
        KhachHangDTO dto = null;
        DiaChiDTO diaChiDTO = null;

        listKhachHang = khachHangRepository.findAll();
        for (KhachHang khachHang : listKhachHang) {
            dto = new KhachHangDTO();
            dto = khachHangConvertor.toDTO(khachHang);
            List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
            for (DiaChi diaChi : khachHang.getListDiaChi()) {
                diaChiDTO = new DiaChiDTO();
                diaChiDTO = diaChiConvertor.toDTO(diaChi);
                listDiaChiDTO.add(diaChiDTO);
            }
            dto.setListDiaChiDTO(listDiaChiDTO);
            listKhachHangDTO.add(dto);
        }
        return listKhachHangDTO;
    }

    @Override
    public List<KhachHangDTO> findAllByInputCoPhanTrang(String soDienThoai, Pageable pageable) {
        List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
        List<KhachHang> listKhachHang = new ArrayList<KhachHang>();
        KhachHangDTO dto = null;
        DiaChiDTO diaChiDTO = null;
        if (soDienThoai != null) {
            listKhachHang = khachHangRepository.findAllBySoDienThoaiCoPhanTrang(soDienThoai, pageable).getContent();
            for (KhachHang khachHang : listKhachHang) {
                dto = new KhachHangDTO();
                dto = khachHangConvertor.toDTO(khachHang);
                List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
                for (DiaChi diaChi : khachHang.getListDiaChi()) {
                    diaChiDTO = new DiaChiDTO();
                    diaChiDTO = diaChiConvertor.toDTO(diaChi);
                    listDiaChiDTO.add(diaChiDTO);
                }
                dto.setListDiaChiDTO(listDiaChiDTO);
                listKhachHangDTO.add(dto);
            }
        }
        return listKhachHangDTO;
    }

    @Override
    public int countAll() {
        return (int) khachHangRepository.count();
    }

    @Override
    public int countByTrangThai(Integer trangThai) {
        return khachHangRepository.countByTrangThai(trangThai);
    }

    @Override
    public int countByInput(String input) {
        return khachHangRepository.countByInput(input);
    }

    @Override
    public int countByInputVaTrangThai(String input, Integer trangThai) {
        return khachHangRepository.countByInputVaTrangThai(input, trangThai);
    }

    @Override
    @Transactional
    public void capNhatTrangThaiTheoId(Integer id) {
        KhachHang entity = khachHangRepository.findById(id).get();
        NguoiDung nguoiDungEntity = nguoiDungRepository.findByEmail(entity.getEmail());
        if (entity != null) {
            if (entity.getTrangThai() == 1) {
                if (nguoiDungEntity != null) {
                    nguoiDungRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDungEntity.getIdNguoiDung());
                }
                khachHangRepository.capNhatTrangThaiThanhKhongHoatDongTheoMa(entity.getIdKhachHang());
            }
            if (entity.getTrangThai() == 0) {
                if (nguoiDungEntity != null) {
                    nguoiDungRepository.capNhatTrangThaiThanhHoatDongTheoMa(nguoiDungEntity.getIdNguoiDung());
                }
                khachHangRepository.capNhatTrangThaiThanhHoatDongTheoMa(entity.getIdKhachHang());
            }
        }
    }

    @Override
    @Transactional
    public void updateUserStatus(Integer id, int trangThai) {
        KhachHang user = khachHangRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại!"));
        user.setTrangThai(trangThai);
        user = khachHangRepository.save(user);
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(user.getEmail());
        if (nguoiDung != null) {
            if (user.getTrangThai() == 1) {
                nguoiDungRepository
                        .capNhatTrangThaiThanhHoatDongTheoMa(nguoiDung.getIdNguoiDung());
            }
            if (user.getTrangThai() == 0) {
                nguoiDungRepository
                        .capNhatTrangThaiThanhKhongHoatDongTheoMa(nguoiDung.getIdNguoiDung());
            }
        } else {
            return;
        }
    }

    @Override
    public KhachHangDTO findByEmail(String auth) {
        KhachHangDTO khachHangDT0 = new KhachHangDTO();
        KhachHang khachHang = khachHangRepository.findByEmail(auth);
        if (khachHang != null) {
            khachHangDT0 = khachHangConvertor.toDTO(khachHang);
            return khachHangDT0;
        }
        return null;
    }

    @Override
    public KhachHangDTO findByEmailAndTrangThai(String email, int trangThai) {
        KhachHangDTO khachHangDTO = null;

        KhachHang khachHang = khachHangRepository.findByEmailAndTrangThai(email, trangThai);
        if (khachHang != null) {
            khachHangDTO = new KhachHangDTO();
            khachHangDTO = khachHangConvertor.toDTO(khachHang);
            return khachHangDTO;
        }
        return khachHangDTO;
    }

    @Override
    @Transactional
    public KhachHangDTO register(KhachHangDTO khachHangDTO) {
        char[] password = RanDomUtil.ranDomFull();
        KhachHang khachHang = new KhachHang();
        khachHangDTO.setSoLanMua(0);
        khachHangDTO.setTrangThai(1);
        khachHangDTO.setNgayTao(new Date());
        khachHangDTO.setMatKhau(new String(password));
        khachHang = khachHangConvertor.toEntity(khachHangDTO);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        ZonedDateTime now = ZonedDateTime.now();
        String time = f.format(now);
        mailService.sendMail("datn.novashoes@gmail.com",
                khachHangDTO.getEmail(),
                "Bạn đã đăng ký tài khoản thành công lúc " + time + "!",
                "Họ tên : " + khachHangDTO.getHoTen() + "\n" +
                        "Số điện thoại : " + khachHangDTO.getSoDienThoai() + "\n" +
                        "Mật khẩu : " + new String(password) + "\n" +
                        "Nếu bạn có bất kì câu hỏi nào, vui lòng liên hệ với chúng tôi: datn.novashoes@gmail.com" + "\n" +
                        "Hoặc địa chỉ : 48 Ngõ 99 Cầu Diễn, Từ Liêm, Hà Nội."
        );

        NguoiDung nguoiDung = nguoiDungConvertor.toEntityByKhachHangDTO(khachHangDTO);
        NguoiDung_VaiTro nguoiDungVaiTro = new NguoiDung_VaiTro();
        nguoiDungVaiTro.setNguoiDung(nguoiDung);
        nguoiDungVaiTro.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
        nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
        nguoiDungRepository.save(nguoiDung);
        khachHang = khachHangRepository.save(khachHang);
        GioHang gioHang = new GioHang();
        gioHang.setTongTien(0);
        gioHang.setTrangThai(1);
        gioHang.setKhachHang(khachHang);
        gioHangRepository.save(gioHang);
        return khachHangConvertor.toDTO(khachHang);
    }

    @Override
    public String sendCode(String email) {
        String code = "";
        KhachHang entity = khachHangRepository.findByEmail(email);
        code = new String(RanDomUtil.ranDomNumber());
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        ZonedDateTime now = ZonedDateTime.now();
        String time = f.format(now);
        if (entity.getEmail() != null) {
            mailService.sendMail("datn.novashoes@gmail.com",
                    entity.getEmail(),
                    "Your code reset password " + time + " !",
                    "Code : " + code + "\n" +
                            "Nếu bạn có bất kì câu hỏi nào, vui lòng liên hệ với chúng tôi: datn.novashoes@gmail.com" + "\n" +
                            "Hoặc địa chỉ : 48 Ngõ 99 Cầu Diễn, Từ Liêm, Hà Nội.");
        }
        return code;
    }

    @Override
    public void updatePassword(String email, KhachHangDTO khachHangDTO) {
        KhachHang khachHang = khachHangRepository.findByEmailAndTrangThai(email, 1);
        NguoiDung nguoiDung = nguoiDungRepository.findByEmailAndTrangThaiAndDaXoa(email);
        if (nguoiDung != null) {
            nguoiDung.setMatKhau(passwordEncoder.encode(khachHangDTO.getMatKhau()));
            nguoiDung.setAuthProvider(AuthenticationProvider.LOCAL);
        }
        if (khachHang != null) {
            khachHang.setMatKhau(passwordEncoder.encode(khachHangDTO.getMatKhau()));
        }
        nguoiDungRepository.save(nguoiDung);
        khachHangRepository.save(khachHang);
    }

    @Override
    public void capNhatMatKhau(TaiKhoanDTO taiKhoanDTO) {
        KhachHang khachHang = khachHangRepository.findByEmail(taiKhoanDTO.getEmail());
        if (khachHang != null) {
            khachHang.setMatKhau(passwordEncoder.encode(taiKhoanDTO.getPassword()));
        }
        khachHangRepository.save(khachHang);
    }

    @Override
    public void taoMoiKhachHang(String email, String fullname, AuthenticationProvider provider) {
        KhachHang entity = new KhachHang();
        entity.setEmail(email);
        entity.setHoTen(fullname);
        entity.setSoLanMua(0);
        entity.setTrangThai(1);
        entity.setAuthProvider(provider);
        entity.setSoDienThoai("");
        khachHangRepository.save(entity);
        GioHang gioHang = new GioHang();
        gioHang.setTongTien(0);
        gioHang.setTrangThai(1);
        gioHang.setKhachHang(entity);
        gioHangRepository.save(gioHang);
    }

    @Override
    public void capNhatKhachHang(String email, String fullname, AuthenticationProvider provider) {
        KhachHang entity = khachHangRepository.findByEmail(email);
        entity.setEmail(email);
        entity.setHoTen(fullname);
        entity.setTrangThai(1);
        entity.setAuthProvider(provider);
        khachHangRepository.save(entity);
    }
}
