package com.example.duantotnghiepgiaythethaonova.controller.admin.NhanVien;

import com.example.duantotnghiepgiaythethaonova.dto.NguoiDungDTO;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
import com.example.duantotnghiepgiaythethaonova.entity.VaiTro;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDungRepository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDung_VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.repository.VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private List<NguoiDungDTO> convertPageToList(Page<NguoiDung> page) {
        List<NguoiDungDTO> listNguoiDungDTO = new ArrayList<>();
        for (NguoiDung nguoiDung : page.getContent()) {
            NguoiDungDTO nguoiDungDTO = new NguoiDungDTO();
            nguoiDungDTO.setMaNhanVien(nguoiDung.getMaNguoiDung());
            nguoiDungDTO.setTenNguoiDung(nguoiDung.getTenNguoiDung());
            nguoiDungDTO.setEmail(nguoiDung.getEmail());
            nguoiDungDTO.setSoDienThoai(nguoiDung.getSoDienThoai());
            nguoiDungDTO.setNgayTao(nguoiDung.getNgayTao());
            nguoiDungDTO.setTrangThai(nguoiDung.getTrangThai());
            nguoiDungDTO.setAnhNhanVien(nguoiDung.getAnhNhanVien());
            nguoiDungDTO.setIdNguoiDung(nguoiDung.getIdNguoiDung());
            listNguoiDungDTO.add(nguoiDungDTO);
        }
        return listNguoiDungDTO;
    }

    //List
    @GetMapping("/admin/NhanVien/danhSach")
    public String getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "4") int size,
            Model model
    ) {
        NguoiDungDTO dto = new NguoiDungDTO();
        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(Sort.DEFAULT_DIRECTION.DESC, "idNguoiDung"));
        Page<NguoiDung> users = nguoiDungRepository.findAllNguoiDung(pageable);
        List<NguoiDungDTO> listNguoiDungDTO = convertPageToList(users);
        dto.setListNguoiDungDTO(listNguoiDungDTO);
        model.addAttribute("NguoiDungDTO", dto);
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "admin/NhanVien/danhSachNhanVien";
    }

    //Add
    @RequestMapping("/NhanVien/themMoi")
    public String themNguoiDung(Model model) {
        NguoiDung nguoiDung = new NguoiDung();
        List<VaiTro> vaiTro = vaiTroRepository.findAll();
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("phanQuyen", vaiTro);
        return "admin/NhanVien/themNhanVien";
    }

    //Update
    @RequestMapping("admin/chinhSuaNhanVien/id-nhan-vien={id}")
    public String chinhSuaNguoiDung(@PathVariable("id") Integer id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id);
        List<VaiTro> vaiTro = vaiTroRepository.findAll();
        model.addAttribute("nguoiDungEdit", nguoiDung);
        model.addAttribute("phanQuyen", vaiTro);
        return "admin/NhanVien/chinhSuaNhanVien";
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam("userId") Integer id, @RequestParam("status") int trangThai) {
        try {
            nguoiDungService.updateUserStatus(id, trangThai);
            return ResponseEntity.ok("Cập nhật trạng thái thành công!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật trạng thái!");
        }
    }


}
