package com.example.duantotnghiepgiaythethaonova.controller.admin.SanPham;

import com.example.duantotnghiepgiaythethaonova.dto.ChatLieuDTO;
import com.example.duantotnghiepgiaythethaonova.dto.ThuongHieuDTO;
import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import com.example.duantotnghiepgiaythethaonova.entity.ThuongHieu;
import com.example.duantotnghiepgiaythethaonova.service.ChatLieuService;
import com.example.duantotnghiepgiaythethaonova.service.SanPhamService;
import com.example.duantotnghiepgiaythethaonova.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("")
    public String chatLieu(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
                           @RequestParam("messageDanger") Optional<String> messageDanger) {
        String[] tenThuongHieuSearchs = request.getParameterValues("tenThuongHieuSearch");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<ThuongHieu> resultPage = null;
        if (tenThuongHieuSearchs == null) {
            List<ThuongHieuDTO> dtos = new ArrayList<>();
            resultPage = thuongHieuService.selectAllThuongHieuExist(pageable);
            for (ThuongHieu thuongHieu : resultPage.getContent()) {
                ThuongHieuDTO dto = new ThuongHieuDTO();
                dto.setIdThuongHieu(thuongHieu.getIdThuongHieu());
                dto.setTenThuongHieu(thuongHieu.getTenThuongHieu());
                dtos.add(dto);
            }
            model.addAttribute("thuongHieus", dtos);
        } else {
            if(!tenThuongHieuSearchs[0].isEmpty()) {
                List<ThuongHieuDTO> dtos = new ArrayList<>();
                resultPage = thuongHieuService.getThuongHieuExistByName(tenThuongHieuSearchs[0], pageable);
                for (ThuongHieu thuongHieu : resultPage.getContent()) {
                    ThuongHieuDTO dto = new ThuongHieuDTO();
                    dto.setIdThuongHieu(thuongHieu.getIdThuongHieu());
                    dto.setTenThuongHieu(thuongHieu.getTenThuongHieu());
                    dtos.add(dto);
                }
                model.addAttribute("thuongHieus", dtos);
            }else {
                List<ThuongHieuDTO> dtos = new ArrayList<>();
                resultPage = thuongHieuService.selectAllThuongHieuExist(pageable);
                for (ThuongHieu thuongHieu : resultPage.getContent()) {
                    ThuongHieuDTO dto = new ThuongHieuDTO();
                    dto.setIdThuongHieu(thuongHieu.getIdThuongHieu());
                    dto.setTenThuongHieu(thuongHieu.getTenThuongHieu());
                    dtos.add(dto);
                }
                model.addAttribute("thuongHieus", dtos);
            }
        }
        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) {
                    start = end - 5;
                } else if (start == 1) {
                    end = start + 5;
                }
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        if (messageSuccess.isPresent()) {
            model.addAttribute("messageSuccess", messageSuccess.get());
        }
        if (messageSuccess.isPresent()) {
            model.addAttribute("messageDanger", messageDanger.get());
        }
        model.addAttribute("resultPage", resultPage);
        return "admin/thuongHieu/thuongHieuManage";
    }

    @PostMapping("createOrUpdate")
    public String createOrUpdate(RedirectAttributes redirect,
                                 @RequestParam("tenThuongHieuCreateOrUpdate") String tenThuongHieu,
                                 @RequestParam("thuongHieuIdCreateOrUpdate") String thuongHieuId) {
        final String redirectUrl = "redirect:/admin/thuong-hieu";
        if(tenThuongHieu != null && thuongHieuId!= null) {
            if(thuongHieuId.isEmpty()) {
                redirect.addFlashAttribute("messageDanger","Tên thương hiệu không được để trống");
                return redirectUrl;
            }
            Optional<ThuongHieu> opt = thuongHieuService.findById(Integer.parseInt(thuongHieuId));
            if(opt.isPresent()) {
                opt.get().setTenThuongHieu(tenThuongHieu);
                redirect.addFlashAttribute("messageSuccess","Cập nhật  thương hiệu thành công");
                thuongHieuService.save(opt.get());
                return redirectUrl;
            }else {
                ThuongHieu cl = new ThuongHieu();
                cl.setTenThuongHieu(tenThuongHieu);
                redirect.addFlashAttribute("messageSuccess","Thêm mới  thương hiệu thành công");
                thuongHieuService.save(cl);
                return redirectUrl;
            }
        }else {
            redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi cập nhật  thương hiệu");
            return redirectUrl;
        }
    }

    @GetMapping("info/{id}")
    public String info(@PathVariable("id") Integer id, Model model,RedirectAttributes redirect) {
        Optional<ThuongHieu> opt = thuongHieuService.findById(id);
        if(opt.isPresent()) {
            model.addAttribute("thuongHieu", opt.get());
        }else {
            redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi tìm chi tiết  thương hiệu");
            return "redirect:/admin/thuong-hieu";
        }
        return "admin/thuongHieu/infoThuongHieu";
    }


    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model,RedirectAttributes redirect) {
        Optional<ThuongHieu> opt = thuongHieuService.findById(id);
        if(opt.isPresent()) {
            thuongHieuService.delete(opt.get());
            redirect.addFlashAttribute("messageSuccess","Xóa  thương hiệu thành công");
            return "redirect:/admin/thuong-hieu";
        }else {
            redirect.addFlashAttribute("messageDanger","Đã xảy ra lỗi khi xóa  thương hiệu");
            return "redirect:/admin/thuong-hieu";
        }
    }
}
