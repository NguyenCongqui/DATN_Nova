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
import com.example.duantotnghiepgiaythethaonova.repository.INguoiDungPaginRepository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDungRepository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDung_VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NguoiDungService {

    private static NguoiDungRepository nguoiDungRepository;
    private static Map<Integer, NguoiDung> nguoiDungMap;
    private final INguoiDungPaginRepository iNguoiDungPaginRepository;

    @Autowired
    private NguoiDungConvertor nguoiDungConvertor;

    @Autowired
    private NguoiDungVaiTroConvertor nguoiDungVaiTroConvertor;

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private NguoiDung_VaiTroRepository nguoiDung_vaiTroRepository;

    @Autowired
    public NguoiDungService(NguoiDungRepository nguoiDungRepository, INguoiDungPaginRepository iNguoiDungPaginRepository) {
        this.iNguoiDungPaginRepository = iNguoiDungPaginRepository;
        this.nguoiDungRepository = nguoiDungRepository;
    }

    public List<NguoiDung> getAllUser() {
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
        return nguoiDungRepository.findAllNguoiDung(pageable);

    }

    public Page<NguoiDung> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iNguoiDungPaginRepository.GetAll(pageable);
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

    public void updateUserStatus(Integer id, Integer trangThai) {
        NguoiDung user = nguoiDungRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại !"));
        user.setTrangThai(trangThai);
        nguoiDungRepository.save(user);
    }

    public NguoiDungDTO findByEmailAndTrangThaiAndDaXoa(String email) {
        NguoiDungDTO nguoiDungDTO = null;
        try {
            List<NguoiDung_VaiTroDTO> listNguoiDung_VaiTroDTO = new ArrayList<NguoiDung_VaiTroDTO>();
            if (email != null) {
                nguoiDungDTO = new NguoiDungDTO();
                NguoiDung nguoiDung = nguoiDungRepository.findByEmailAndTrangThaiAndDaXoa(email);
                for (NguoiDung_VaiTro nguoiDung_vaiTro : nguoiDung.getListNguoiDungVaiTro()) {
                    NguoiDung_VaiTroDTO nguoiDung_vaiTroDTO = new NguoiDung_VaiTroDTO();
                    nguoiDung_vaiTroDTO = nguoiDungVaiTroConvertor.toDTO(nguoiDung_vaiTro);
                    listNguoiDung_VaiTroDTO.add(nguoiDung_vaiTroDTO);
                }
                nguoiDungDTO = nguoiDungConvertor.toDTO(nguoiDung);
                nguoiDungDTO.setListNguoiDungVaiTroDTO(listNguoiDung_VaiTroDTO);
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


    public void taoNguoiDungSauKhiDangNhapMangXaHoiThanhCong(String email, String fullname,
                                                             AuthenticationProvider google) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setTenNguoiDung(fullname);
        nguoiDung.setEmail(email);
        nguoiDung.setAuthProvider(google);
        nguoiDung.setDaXoa(false);
        nguoiDung.setTrangThai(0);
        nguoiDung = nguoiDungRepository.save(nguoiDung);
        NguoiDung_VaiTro nguoiDung_vaiTro = new NguoiDung_VaiTro();
        nguoiDung_vaiTro.setNguoiDung(nguoiDung);
        nguoiDung_vaiTro.setVaiTro(vaiTroRepository.findByTenVaiTro("CUSTOMER"));
        nguoiDung_vaiTroRepository.save(nguoiDung_vaiTro);
    }


    public void capNhatNguoiDungSauKhiDangNhapVoiMangXaHoiThanhCong(String email, String fullname,
                                                                    AuthenticationProvider google) {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(email);
        nguoiDung.setTenNguoiDung(fullname);
        nguoiDung.setEmail(email);
        nguoiDung.setAuthProvider(google);
        nguoiDung.setDaXoa(false);
        nguoiDung.setTrangThai(0);
        nguoiDung = nguoiDungRepository.save(nguoiDung);
    }

}
