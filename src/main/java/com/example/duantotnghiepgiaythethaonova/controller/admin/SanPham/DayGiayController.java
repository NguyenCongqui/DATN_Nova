package com.example.duantotnghiepgiaythethaonova.controller.admin.SanPham;


import com.example.duantotnghiepgiaythethaonova.dto.DayGiayDTO;
import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.service.DayGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class DayGiayController {

    @Controller
    @RequestMapping("admin/day-giay")
    public class KichCoController {
        @Autowired
        private HttpSession session;

        @Autowired
        private DayGiayService kichCoService;

        @GetMapping("")
        public String chatLieu(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam("messageSuccess") Optional<String> messageSuccess,
                               @RequestParam("messageDanger") Optional<String> messageDanger) {
            String[] tenKichCoSearch = request.getParameterValues("tenDayGiaySearch");
            int currentPage = page.orElse(1);
            int pageSize = size.orElse(10);
            Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
            Page<DayGiay> resultPage = null;
            NguoiDung nguoiDung = (NguoiDung) session.getAttribute("NguoiDung");
            if (tenKichCoSearch == null) {
                List<DayGiayDTO> dtos = new ArrayList<>();
                resultPage = kichCoService.selectAllKichCoExist(pageable);
                for (DayGiay kichCo : resultPage.getContent()) {
                    DayGiayDTO dto = new DayGiayDTO();
                    dto.setIdDayGiay(kichCo.getIdDayGiay());
                    dto.setTenDayGiay(kichCo.getTenDayGiay());
                    dtos.add(dto);
                }
                model.addAttribute("dayGiays", dtos);
            } else {
                if (!tenKichCoSearch[0].isEmpty()) {
                    List<DayGiayDTO> dtos = new ArrayList<>();
                    resultPage = kichCoService.getKichCoExistByName(tenKichCoSearch[0], pageable);
                    for (DayGiay kichCo : resultPage.getContent()) {
                        DayGiayDTO dto = new DayGiayDTO();
                        dto.setIdDayGiay(kichCo.getIdDayGiay());
                        dto.setTenDayGiay(kichCo.getTenDayGiay());
                        dtos.add(dto);
                    }
                    model.addAttribute("dayGiays", dtos);
                } else {
                    List<DayGiayDTO> dtos = new ArrayList<>();
                    resultPage = kichCoService.selectAllKichCoExist(pageable);
                    for (DayGiay kichCo : resultPage.getContent()) {
                        DayGiayDTO dto = new DayGiayDTO();
                        dto.setIdDayGiay(kichCo.getIdDayGiay());
                        dto.setTenDayGiay(kichCo.getTenDayGiay());
                        dtos.add(dto);
                    }
                    model.addAttribute("dayGiays", dtos);
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
            return "admin/dayGiay/dayGiayManage";
        }


        @PostMapping("createOrUpdate")
        public String createOrUpdate(RedirectAttributes redirect,
                                     @RequestParam("tenDayGiayCreateOrUpdate") String tenKichCo,
                                     @RequestParam("dayGiayIdCreateOrUpdate") String kichCoId) {
            final String redirectUrl = "redirect:/admin/day-giay";
            if (tenKichCo != null && !tenKichCo.isEmpty() && kichCoId != null && !kichCoId.isEmpty()) {
                Optional<DayGiay> opt = kichCoService.findById(Integer.parseInt(kichCoId));
                if (opt.isPresent()) {
                    DayGiay cl = opt.get();
                    cl.setTenDayGiay(tenKichCo);
                    cl.setNgayCapNhat(new Date());
                    redirect.addFlashAttribute("messageSuccess", "Cập nhật dây giày thành công");
                    kichCoService.save(cl);
                } else {
                    DayGiay cl = new DayGiay();
                    cl.setTenDayGiay(tenKichCo);
                    cl.setNgayTao(new Date());
                    redirect.addFlashAttribute("messageSuccess", "Thêm mới dây giày thành công");
                    kichCoService.save(cl);
                }
            } else {
                redirect.addFlashAttribute("messageDanger", "Tên dây giày không được để trống");
            }
            return redirectUrl;
        }

        @GetMapping("info/{id}")
        public String info(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
            Optional<DayGiay> opt = kichCoService.findById(id);
            if (opt.isPresent()) {
                model.addAttribute("dayGiay", opt.get());
                return "admin/dayGiay/infoDayGiay";
            } else {
                redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi tìm chi tiết dây giày");
                return "redirect:/admin/day-giay";
            }
        }


        @GetMapping("delete/{id}")
        public String delete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
            Optional<DayGiay> opt = kichCoService.findById(id);
            if (opt.isPresent()) {
                kichCoService.delete(opt.get());
                redirect.addFlashAttribute("messageSuccess", "Xóa dây giày thành công");
                return "redirect:/admin/day-giay";
            } else {
                redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi xóa dây giày");
                return "redirect:/admin/day-giay";
            }
        }
    }
}
