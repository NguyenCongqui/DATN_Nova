package com.example.duantotnghiepgiaythethaonova.controller.admin.SanPham;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import com.example.duantotnghiepgiaythethaonova.dto.DeGiayDTO;
import com.example.duantotnghiepgiaythethaonova.entity.DeGiay;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.service.DeGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("admin/de-giay")
public class DeGiayController {

    @Autowired
    private HttpSession session;

    @Autowired
    private DeGiayService kichCoService;

    @GetMapping("")
    public String chatLieu(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("messageSuccess") Optional<String> messageSuccess,
                           @RequestParam("messageDanger") Optional<String> messageDanger) {
        String[] tenKichCoSearch = request.getParameterValues("tenDeGiaySearch");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<DeGiay> resultPage = null;
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("NguoiDung");
        if (tenKichCoSearch == null) {
            List<DeGiayDTO> dtos = new ArrayList<>();
            resultPage = kichCoService.selectAllKichCoExist(pageable);
            for (DeGiay kichCo : resultPage.getContent()) {
                DeGiayDTO dto = new DeGiayDTO();
                dto.setIdDeGiay(kichCo.getIdDeGiay());
                dto.setTenDeGiay(kichCo.getTenDeGiay());
                dtos.add(dto);
            }
            model.addAttribute("deGiays", dtos);
        } else {
            if (!tenKichCoSearch[0].isEmpty()) {
                List<DeGiayDTO> dtos = new ArrayList<>();
                resultPage = kichCoService.getKichCoExistByName(tenKichCoSearch[0], pageable);
                for (DeGiay kichCo : resultPage.getContent()) {
                    DeGiayDTO dto = new DeGiayDTO();
                    dto.setIdDeGiay(kichCo.getIdDeGiay());
                    dto.setTenDeGiay(kichCo.getTenDeGiay());
                    dtos.add(dto);
                }
                model.addAttribute("deGiays", dtos);
            } else {
                List<DeGiayDTO> dtos = new ArrayList<>();
                resultPage = kichCoService.selectAllKichCoExist(pageable);
                for (DeGiay kichCo : resultPage.getContent()) {
                    DeGiayDTO dto = new DeGiayDTO();
                    dto.setIdDeGiay(kichCo.getIdDeGiay());
                    dto.setTenDeGiay(kichCo.getTenDeGiay());
                    dtos.add(dto);
                }
                model.addAttribute("deGiays", dtos);
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
        return "admin/deGiay/deGiayManage";
    }


    @PostMapping("createOrUpdate")
    public String createOrUpdate(RedirectAttributes redirect,
                                 @RequestParam("tenDeGiayCreateOrUpdate") String tenKichCo,
                                 @RequestParam("DeGiayIdCreateOrUpdate") String kichCoId) {
        final String redirectUrl = "redirect:/admin/de-giay";
        if (tenKichCo != null && !tenKichCo.isEmpty() && kichCoId != null && !kichCoId.isEmpty()) {
            Optional<DeGiay> opt = kichCoService.findById(Integer.parseInt(kichCoId));
            if (opt.isPresent()) {
                DeGiay cl = opt.get();
                cl.setTenDeGiay(tenKichCo);
                cl.setNgayCapNhat(new Date());
                redirect.addFlashAttribute("messageSuccess", "Cập nhật đế giày thành công");
                kichCoService.save(cl);
            } else {
                DeGiay cl = new DeGiay();
                cl.setTenDeGiay(tenKichCo);
                cl.setNgayTao(new Date());
                redirect.addFlashAttribute("messageSuccess", "Thêm mới đế giày thành công");
                kichCoService.save(cl);
            }
        } else {
            redirect.addFlashAttribute("messageDanger", "Tên đế giày không được để trống");
        }
        return redirectUrl;
    }

    @GetMapping("info/{id}")
    public String info(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
        Optional<DeGiay> opt = kichCoService.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("DeGiay", opt.get());
            return "admin/DeGiay/infoDeGiay";
        } else {
            redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi tìm chi tiết đế giày");
            return "redirect:/admin/de-giay";
        }
    }


    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
        Optional<DeGiay> opt = kichCoService.findById(id);
        if (opt.isPresent()) {
            kichCoService.delete(opt.get());
            redirect.addFlashAttribute("messageSuccess", "Xóa đế giày thành công");
            return "redirect:/admin/de-giay";
        } else {
            redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi xóa đế giày");
            return "redirect:/admin/de-giay";
        }
    }
}
