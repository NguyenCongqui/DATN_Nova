package com.example.duantotnghiepgiaythethaonova.service;


import com.example.duantotnghiepgiaythethaonova.config.BcryptedPasswordEncoderConfig;
import com.example.duantotnghiepgiaythethaonova.convertor.NguoiDungConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.NguoiDungVaiTroConvertor;
import com.example.duantotnghiepgiaythethaonova.dto.NguoiDungDTO;
import com.example.duantotnghiepgiaythethaonova.dto.NguoiDung_VaiTroDTO;
import com.example.duantotnghiepgiaythethaonova.dto.TaiKhoanDTO;
import com.example.duantotnghiepgiaythethaonova.entity.AuthenticationProvider;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
import com.example.duantotnghiepgiaythethaonova.repository.INguoiDungPaginRespository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDungRepository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDung_VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.duantotnghiepgiaythethaonova.QEntityGenarate.QNguoiDung.nguoiDung;

@Service
public class NguoiDungService {
    private static NguoiDungRepository nguoiDungRepository;
    private static Map<Integer, NguoiDung> nguoiDungMap;
    private final INguoiDungPaginRespository iNguoiDungPaginRespository;

    @Autowired
    private NguoiDungConvertor nguoiDungConvertor;

    @Autowired
    private NguoiDungVaiTroConvertor nguoiDungVaiTroConvertor;

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private NguoiDung_VaiTroRepository nguoiDungVaiTroRepository;

    @Autowired
    public NguoiDungService(NguoiDungRepository nguoiDungRepository, INguoiDungPaginRespository iNguoiDungPaginRespository) {
        this.nguoiDungRepository = nguoiDungRepository;
        this.iNguoiDungPaginRespository = iNguoiDungPaginRespository;
    }

    public List<NguoiDung> getAllUsers() {
//        List<NguoiDung> nguoiDungs = nguoiDungRepository.GetAll();
//
//        // Loại bỏ các đối tượng có MaNguoiDung là null
//        nguoiDungs.removeIf(nguoiDung -> nguoiDung.getMaNguoiDung() == null);

        return nguoiDungRepository.findAll();
    }

    public NguoiDung getEdit(Integer id) {
        return nguoiDungRepository.findById(id).get();
    }

    public void edit(Integer id, NguoiDung productsEntity) {
        nguoiDungMap.put(id, productsEntity);
    }

    public void save(NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }

    public Page<NguoiDung> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iNguoiDungPaginRespository.findAll(pageable);
    }


    public Page<NguoiDung> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iNguoiDungPaginRespository.findAll(pageable);
    }

    public void saveNguoiDung(NguoiDung nguoiDung) {
        nguoiDungRepository.save(nguoiDung);
    }

    public NguoiDung getNguoiDungById(Integer id) {
        if (id != null) {
            Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findById(id);
            if (optionalNguoiDung.isPresent()) {
                return optionalNguoiDung.get();
            }
        }
        return null;
    }

    public void updateUserStatus(Integer id, int trangThai) {
        NguoiDung user = nguoiDungRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));
        user.setTrangThai(trangThai);
        nguoiDungRepository.save(user);
    }

    public NguoiDungDTO findByEmailAndTrangThaiAndDaXoa(String email) {
        NguoiDungDTO nguoiDungDTO = null;
        try {
            List<NguoiDung_VaiTroDTO> listNguoiDungVaiTroDTO = new ArrayList<NguoiDung_VaiTroDTO>();
            if (email != null) {
                nguoiDungDTO = new NguoiDungDTO();
                NguoiDung nguoiDung = nguoiDungRepository.findByEmailAndTrangThaiAndDaXoa(email);
                for (NguoiDung_VaiTro nguoiDungVaiTro : nguoiDung.getListNguoiDungVaiTro()) {
                    NguoiDung_VaiTroDTO nguoiDungVaiTroDTO = new NguoiDung_VaiTroDTO();
                    nguoiDungVaiTroDTO = nguoiDungVaiTroConvertor.toDTO(nguoiDungVaiTro);
                    listNguoiDungVaiTroDTO.add(nguoiDungVaiTroDTO);
                }
                nguoiDungDTO = nguoiDungConvertor.toDTO(nguoiDung);
                nguoiDungDTO.setListNguoiDungVaiTroDTO(listNguoiDungVaiTroDTO);
                return nguoiDungDTO;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void capNhatMatKhau(TaiKhoanDTO taiKhoanDTO) {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(taiKhoanDTO.getEmail());
        if (nguoiDung != null) {
            nguoiDung.setMatKhau(passwordEncoder.encode(taiKhoanDTO.getPassword()));
        }
        nguoiDungRepository.save(nguoiDung);
    }

    public NguoiDung findByEmail(String email) {
        if (email != null) {
            return nguoiDungRepository.findByEmail(email);
        } else {
            return null;
        }
    }


    public void taoNguoiDungSauKhiDangNhapVoiMangXaHoiThanhCong(String email, String fullname,
                                                                AuthenticationProvider google) {
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
        NguoiDung entity = new NguoiDung();
        entity.setEmail(email);
        entity.setMaNguoiDung(ma);
        entity.setTenNguoiDung(fullname);
        entity.setAuthProvider(google);
        entity.setDaXoa(false);
        entity.setTrangThai(0);
        entity = nguoiDungRepository.save(entity);
        NguoiDung_VaiTro nguoiDungVaiTro = new NguoiDung_VaiTro();
        nguoiDungVaiTro.setNguoiDung(entity);
        nguoiDungVaiTro.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
        nguoiDungVaiTroRepository.save(nguoiDungVaiTro);
    }

    public void capNhatNguoiDungSauKhiDangNhapVoiMangXaHoiThanhCong(String email, String fullname,
                                                                    AuthenticationProvider google) {
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

        NguoiDung entity = findByEmail(email);
        entity.setEmail(email);
        entity.setMaNguoiDung(ma);
        entity.setTenNguoiDung(fullname);
        entity.setAuthProvider(google);
        entity.setDaXoa(false);
        entity.setTrangThai(0);
        entity = nguoiDungRepository.save(entity);
    }


}
