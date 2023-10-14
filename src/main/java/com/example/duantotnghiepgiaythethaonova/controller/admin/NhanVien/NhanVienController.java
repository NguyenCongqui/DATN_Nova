package com.example.duantotnghiepgiaythethaonova.controller.admin.NhanVien;

import com.example.duantotnghiepgiaythethaonova.dto.NguoiDungDTO;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDungRepository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDung_VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.repository.VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NhanVienController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private NguoiDung_VaiTroRepository nguoiDung_vaiTroRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    private List<NguoiDungDTO> convertPageToList(Page<NguoiDung> page){
        List<NguoiDungDTO> listNguoiDungDTO = new ArrayList<>();
        for(NguoiDung nguoiDung : page.getContent()){
            NguoiDungDTO nguoiDungDTO = new NguoiDungDTO();
            nguoiDungDTO.setMaNhanVien(nguoiDung.getMaNguoiDung());
            nguoiDungDTO.setTenNguoiDung(nguoiDung.getTenNguoiDung());
            nguoiDungDTO.setEmail(nguoiDung.getEmail());
            nguoiDungDTO.setSoDienThoai(nguoiDung.getSoDienThoai());
            nguoiDungDTO.setNgayTao(nguoiDung.getNgayTao());
            nguoiDungDTO.setAnhNhanVien(nguoiDung.getAnhNhanVien());
            nguoiDungDTO.setTrangThai(nguoiDung.getTrangThai());
            nguoiDung.setIdNguoiDung(nguoiDung.getIdNguoiDung());

            listNguoiDungDTO.add(nguoiDungDTO);
        }
        return listNguoiDungDTO;
    }



    @GetMapping("/admin/NhanVien/danhSach")
    public String getUsers(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "4") Integer size,
                           Model model){
        NguoiDungDTO nguoiDungDTO = new NguoiDungDTO();
        PageRequest pageable =PageRequest.of(page - 1 ,size , Sort.by(Sort.DEFAULT_DIRECTION.DESC, "id"));
        Page<NguoiDung> staffs =nguoiDungRepository.findAllNguoiDung(pageable);
        List<NguoiDungDTO> listNguoiDungDTO = convertPageToList(staffs);
        nguoiDungDTO.setListNguoiDungDTO(listNguoiDungDTO);
        model.addAttribute("NguoiDungDTO" , nguoiDungDTO);
        model.addAttribute("totalPages" , staffs.getTotalPages());
        model.addAttribute("page" , page);
        model.addAttribute("size" , size);
        return "/admin/NhanVien/danhsach";
    }


}
