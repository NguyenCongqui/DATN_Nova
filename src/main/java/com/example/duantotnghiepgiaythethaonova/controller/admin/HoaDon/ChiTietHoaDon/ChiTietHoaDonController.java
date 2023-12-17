package com.example.duantotnghiepgiaythethaonova.controller.admin.HoaDon.ChiTietHoaDon;


import com.example.duantotnghiepgiaythethaonova.dto.TimeLineDTO;
import com.example.duantotnghiepgiaythethaonova.service.ChiTietHoaDonService;
import com.example.duantotnghiepgiaythethaonova.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChiTietHoaDonController {
    @Autowired
    ChiTietHoaDonService chiTietHoaDonService;

    @Autowired
    TimeLineService timeLineService;

    //CHỜ XÁC NHẬN
    @RequestMapping("ChiTietHoaDon/ChoXacNhan/hoa-don-id={id}")
    public String ChoXacNhan(@PathVariable("id") Integer id, Model model) {
        chiTietHoaDonService.choXacNhan(id, model);
//        TimeLineDTO timeLineDTO = timeLineService.getTimeLineChoXacNhan(id);
//        model.addAttribute("timeLine", timeLineDTO);
//        System.out.println(timeLineDTO);
        return "admin/hoadon/ChiTiethoaDon/CTChoXacNhan";
    }

    //CHỜ GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/ChoGiaoHang/hoa-don-id={id}")
    public String ChoGiaoHang(@PathVariable("id") Integer id, Model model) {
        chiTietHoaDonService.choGiaoHang(id, model);
//        TimeLineDTO timeLineDTOCXN = timeLineService.getTimeLineChoXacNhan(id);
//        model.addAttribute("timeLine", timeLineDTOCXN);
//        TimeLineDTO timeLineDTO = timeLineService.getTimeLineChoGiao(id);
//        model.addAttribute("timeLineChoGiao", timeLineDTO);
//        System.out.println(timeLineDTO + "cho giao");
        return "admin/hoadon/ChiTiethoaDon/CTChoGiaohang";
    }

    //ĐANG GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/DangGiaoHang/hoa-don-id={id}")
    public String DangGiaoHang(@PathVariable("id") Integer id, Model model) {
        chiTietHoaDonService.dangGiaoHang(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTDangGiaoHang";
    }

    //ĐÃ GIAO HÀNG
    @RequestMapping("ChiTietHoaDon/DaGiaoHang/hoa-don-id={id}")
    public String DaGiaoHang(@PathVariable("id") Integer id, Model model) {
        chiTietHoaDonService.daGiaoHang(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTDaGiao";
    }

    //ĐÃ HỦY
    @RequestMapping("ChiTietHoaDon/DaHuy/hoa-don-id={id}")
    public String DaHuy(@PathVariable("id") Integer id, Model model) {
        chiTietHoaDonService.daHuy(id, model);
        return "admin/hoadon/ChiTiethoaDon/CTDaHuy";
    }
}
