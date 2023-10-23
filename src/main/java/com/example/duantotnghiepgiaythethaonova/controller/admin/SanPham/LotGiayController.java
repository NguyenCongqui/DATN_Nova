package com.example.duantotnghiepgiaythethaonova.controller.admin.SanPham;

import com.example.duantotnghiepgiaythethaonova.dto.LotGiayDTO;
import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.service.LotGiayService;
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
@RequestMapping("admin/lot-giay")
public class LotGiayController {

        @Autowired
        private HttpSession session;

        @Autowired
        private LotGiayService kichCoService;

        @GetMapping("")
        public String chatLieu(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam("messageSuccess") Optional<String> messageSuccess,
                               @RequestParam("messageDanger") Optional<String> messageDanger) {
            String[] tenKichCoSearch = request.getParameterValues("tenLotGiaySearch");
            int currentPage = page.orElse(1);
            int pageSize = size.orElse(10);
            Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
            Page<LotGiay> resultPage = null;
            NguoiDung nguoiDung = (NguoiDung) session.getAttribute("NguoiDung");
            if (tenKichCoSearch == null) {
                List<LotGiayDTO> dtos = new ArrayList<>();
                resultPage = kichCoService.selectAllKichCoExist(pageable);
                for (LotGiay kichCo : resultPage.getContent()) {
                    LotGiayDTO dto = new LotGiayDTO();
                    dto.setIdLotGiay(kichCo.getIdLotGiay());
                    dto.setTenLotGiay(kichCo.getTenLotGiay());
                    dtos.add(dto);
                }
                model.addAttribute("lotGiays", dtos);
            } else {
                if (!tenKichCoSearch[0].isEmpty()) {
                    List<LotGiayDTO> dtos = new ArrayList<>();
                    resultPage = kichCoService.getKichCoExistByName(tenKichCoSearch[0], pageable);
                    for (LotGiay kichCo : resultPage.getContent()) {
                        LotGiayDTO dto = new LotGiayDTO();
                        dto.setIdLotGiay(kichCo.getIdLotGiay());
                        dto.setTenLotGiay(kichCo.getTenLotGiay());
                        dtos.add(dto);
                    }
                    model.addAttribute("lotGiays", dtos);
                } else {
                    List<LotGiayDTO> dtos = new ArrayList<>();
                    resultPage = kichCoService.selectAllKichCoExist(pageable);
                    for (LotGiay kichCo : resultPage.getContent()) {
                        LotGiayDTO dto = new LotGiayDTO();
                        dto.setIdLotGiay(kichCo.getIdLotGiay());
                        dto.setTenLotGiay(kichCo.getTenLotGiay());
                        dtos.add(dto);
                    }
                    model.addAttribute("lotGiays", dtos);
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
            return "admin/lotGiay/lotGiayManage";
        }


        @PostMapping("createOrUpdate")
        public String createOrUpdate(RedirectAttributes redirect,
                                     @RequestParam("tenLotGiayCreateOrUpdate") String tenKichCo,
                                     @RequestParam("lotGiayIdCreateOrUpdate") String kichCoId) {
            final String redirectUrl = "redirect:/admin/lot-giay";
            if (tenKichCo != null && !tenKichCo.isEmpty() && kichCoId != null && !kichCoId.isEmpty()) {
                Optional<LotGiay> opt = kichCoService.findById(Integer.parseInt(kichCoId));
                if (opt.isPresent()) {
                    LotGiay cl = opt.get();
                    cl.setTenLotGiay(tenKichCo);
                    cl.setNgayCapNhat(new Date());
                    redirect.addFlashAttribute("messageSuccess", "Cập nhật lót giày thành công");
                    kichCoService.save(cl);
                } else {
                    LotGiay cl = new LotGiay();
                    cl.setTenLotGiay(tenKichCo);
                    cl.setNgayTao(new Date());
                    redirect.addFlashAttribute("messageSuccess", "Thêm mới lót giày thành công");
                    kichCoService.save(cl);
                }
            } else {
                redirect.addFlashAttribute("messageDanger", "Tên lót giày không được để trống");
            }
            return redirectUrl;
        }

        @GetMapping("info/{id}")
        public String info(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
            Optional<LotGiay> opt = kichCoService.findById(id);
            if (opt.isPresent()) {
                model.addAttribute("lotGiay", opt.get());
                return "admin/layGiay/infoLotGiay";
            } else {
                redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi tìm chi tiết lót giày");
                return "redirect:/admin/lot-giay";
            }
        }


        @GetMapping("delete/{id}")
        public String delete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
            Optional<LotGiay> opt = kichCoService.findById(id);
            if (opt.isPresent()) {
                kichCoService.delete(opt.get());
                redirect.addFlashAttribute("messageSuccess", "Xóa lót giày thành công");
                return "redirect:/admin/lot-giay";
            } else {
                redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi xóa lót giày");
                return "redirect:/admin/lot-giay";
            }
        }
    }
