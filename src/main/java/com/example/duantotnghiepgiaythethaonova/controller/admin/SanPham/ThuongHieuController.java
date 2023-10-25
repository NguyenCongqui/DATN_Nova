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

    @Autowired
    private SanPhamService sanPhamService;
//
//    @GetMapping("")
//    public String chatLieu(Model model, HttpServletRequest request, @RequestParam("page") Optional<Integer> page,
//                           @RequestParam("size") Optional<Integer> size, @RequestParam("messageSuccess") Optional<String> messageSuccess,
//                           @RequestParam("messageDanger") Optional<String> messageDanger) {
//        String[] tenChatLieuSearchs = request.getParameterValues("tenChatLieuSearch");
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(10);
//        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
//        Page<ThuongHieu> resultPage = null;
//        if (tenChatLieuSearchs == null) {
//            List<ThuongHieuDTO> dtos = new ArrayList<>();
//            resultPage = thuongHieuService.selectAllThuongHieuExist(pageable);
//            for (ThuongHieu thuongHieu : resultPage.getContent()) {
//                ChatLieuDTO dto = new ChatLieuDTO();
//                int soSanPhamCungChatLieu = sanPhamService.selectCountSanPhamByChatLieuId(thuongHieu.getIdThuongHieu());
//                dto.setIdChatLieu(thuongHieu.getIdThuongHieu());
//                dto.setTenChatLieu(thuongHieu.getTenChatLieu());
//                dto.setSoSanPhamCungChatLieu(soSanPhamCungChatLieu);
//                dtos.add(dto);
//            }
//            model.addAttribute("chatLieus", dtos);
//        } else {
//            if (!tenChatLieuSearchs[0].isEmpty()) {
//                List<ChatLieuDTO> dtos = new ArrayList<>();
//                resultPage = thuongHieuService.getChatLieuExistByName(tenChatLieuSearchs[0], pageable);
//                for (ChatLieu chatLieu : resultPage.getContent()) {
//                    ChatLieuDTO dto = new ChatLieuDTO();
//                    int soSanPhamCungChatLieu = sanPhamService.selectCountSanPhamByChatLieuId(chatLieu.getIdChatLieu());
//                    dto.setIdChatLieu(chatLieu.getIdChatLieu());
//                    dto.setTenChatLieu(chatLieu.getTenChatLieu());
//                    dto.setSoSanPhamCungChatLieu(soSanPhamCungChatLieu);
//                    dtos.add(dto);
//                }
//                model.addAttribute("chatLieus", dtos);
//            } else {
//                List<ChatLieuDTO> dtos = new ArrayList<>();
//                resultPage = thuongHieuService.selectAllChatLieuExist(pageable);
//                for (ChatLieu chatLieu : resultPage.getContent()) {
//                    ChatLieuDTO dto = new ChatLieuDTO();
//                    int soSanPhamCungChatLieu = sanPhamService.selectCountSanPhamByChatLieuId(chatLieu.getIdChatLieu());
//                    dto.setIdChatLieu(chatLieu.getIdChatLieu());
//                    dto.setTenChatLieu(chatLieu.getTenChatLieu());
//                    dto.setSoSanPhamCungChatLieu(soSanPhamCungChatLieu);
//                    dtos.add(dto);
//                }
//                model.addAttribute("chatLieus", dtos);
//            }
//        }
//        int totalPages = resultPage.getTotalPages();
//        if (totalPages > 0) {
//            int start = Math.max(1, currentPage - 2);
//            int end = Math.min(currentPage + 2, totalPages);
//            if (totalPages > 5) {
//                if (end == totalPages) {
//                    start = end - 5;
//                } else if (start == 1) {
//                    end = start + 5;
//                }
//            }
//            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//        if (messageSuccess.isPresent()) {
//            model.addAttribute("messageSuccess", messageSuccess.get());
//        }
//        if (messageSuccess.isPresent()) {
//            model.addAttribute("messageDanger", messageDanger.get());
//        }
//        model.addAttribute("resultPage", resultPage);
//        return "admin/chatLieu/chatLieuManage";
//    }
//
//    @PostMapping("createOrUpdate")
//    public String createOrUpdate(RedirectAttributes redirect,
//                                 @RequestParam("tenChatLieuCreateOrUpdate") String tenChatLieu,
//                                 @RequestParam("chatLieuIdCreateOrUpdate") String chatLieuId) {
//        final String redirectUrl = "redirect:/admin/chat-lieu";
//        if (tenChatLieu != null && chatLieuId != null) {
//            if (tenChatLieu.isEmpty()) {
//                redirect.addFlashAttribute("messageDanger", "Tên chất liệu không được để trống");
//                return redirectUrl;
//            }
//            Optional<ChatLieu> opt = thuongHieuService.findById(Integer.parseInt(chatLieuId));
//            if (opt.isPresent()) {
//                opt.get().setTenChatLieu(tenChatLieu);
//                redirect.addFlashAttribute("messageSuccess", "Cập nhật chất liệu thành công");
//                thuongHieuService.save(opt.get());
//                return redirectUrl;
//            } else {
//                ChatLieu cl = new ChatLieu();
//                cl.setTenChatLieu(tenChatLieu);
//                redirect.addFlashAttribute("messageSuccess", "Thêm mới chất liệu thành công");
//                thuongHieuService.save(cl);
//                return redirectUrl;
//            }
//        } else {
//            redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi cập nhật chất liệu");
//            return redirectUrl;
//        }
//    }
//
//    @GetMapping("info/{id}")
//    public String info(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
//        Optional<ChatLieu> opt = thuongHieuService.findById(id);
//        if (opt.isPresent()) {
//            model.addAttribute("chatLieu", opt.get());
//            return "admin/chatLieu/infoChatLieu";
//        } else {
//            redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi tìm chi tiết chất liệu");
//            return "redirect:/admin/chat-lieu";
//        }
//
//    }
//
//
//    @GetMapping("delete/{id}")
//    public String delete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirect) {
//        Optional<ChatLieu> opt = thuongHieuService.findById(id);
//        if (opt.isPresent()) {
//            thuongHieuService.delete(opt.get());
//            redirect.addFlashAttribute("messageSuccess", "Xóa chất liệu thành công");
//            return "redirect:/admin/chat-lieu";
//        } else {
//            redirect.addFlashAttribute("messageDanger", "Đã xảy ra lỗi khi xóa chất liệu");
//            return "redirect:/admin/chat-lieu";
//        }
//    }
}
