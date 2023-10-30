package com.example.duantotnghiepgiaythethaonova.api.admin;

import com.example.duantotnghiepgiaythethaonova.dto.KhachHangDTO;
import com.example.duantotnghiepgiaythethaonova.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "khachHangAPIOfAdmin")
public class KhachHangAPI {

    @Autowired
    private KhachHangService khachHangService;

    @PostMapping("/admin/api/cap-nhat-trang-thai")
    public ResponseEntity<String> updateStatus(@RequestParam("userId") Integer id, @RequestParam("status") int trangThai) {
        try {
            khachHangService.updateUserStatus(id, trangThai);
            return ResponseEntity.ok("Cập nhật trạng thái thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Khách hàng không tồn tại");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật trạng thái");
        }
    }

    @GetMapping("/admin/api/khach-hang")
    public List<KhachHangDTO> layDanhSachKhachHang() {
        return khachHangService.findAll();
    }

    @PutMapping("/admin/api/khach-hang/trang-thai-dang-hoat-dong")
    public void deleteKhachHangByTrangThaiDangHoatDong(@RequestBody Integer[] ids) {
        khachHangService.capNhatTrangThaiThanhDangHoatDongTheoMa(ids);
    }

    @PutMapping("/admin/api/khach-hang/trang-thai-khong-hoat-dong")
    public void deleteKhachHangByTrangThaiKhongHoatDong(@RequestBody Integer[] ids) {
        khachHangService.capNhatTrangThaiThanhKhongHoatDongTheoMa(ids);
    }

    @PostMapping("/admin/api/khach-hang")
    public KhachHangDTO themMoiKhachHang(@RequestBody KhachHangDTO khachHangDTO) {
        return khachHangService.save(khachHangDTO);
    }

    @PutMapping("/admin/api/khach-hang")
    public KhachHangDTO capNhatKhachHang(@RequestBody KhachHangDTO khachHangDTO) {
        return khachHangService.save(khachHangDTO);
    }
//	@Autowired
//	private KhachHangService KhachHangService ;
//	
//	
//	@GetMapping("/admin/api/khach-hang")
//	public List<KhachHangDTO> layDanhSachKhachHang(){
//		return KhachHangService.findAll();
//	}
//	
//	@DeleteMapping("/admin/api/khach-hang/trang-thai-dang-hoat-dong")
//	public void deleteKhachHangByTrangThaiDangHoatDong(@RequestBody long[] ids) {
//			KhachHangService.capNhatTrangThaiThanhDangHoatDongTheoMa(ids);
//	}
//	
//	
//	@DeleteMapping("/admin/api/khach-hang/trang-thai-khong-hoat-dong")
//	public void deleteKhachHangByTrangThaiKhongHoatDong(@RequestBody long[] ids) {
//			KhachHangService.capNhatTrangThaiThanhKhongHoatDongTheoMa(ids);
//	}
//	
//	@PostMapping("/admin/api/khach-hang")
//	public KhachHangDTO themMoiKhachHang(@RequestBody KhachHangDTO khachHangDTO) {
//		return KhachHangService.save(khachHangDTO);
//	}
//	@PutMapping("/admin/api/khach-hang")
//	public KhachHangDTO capNhatKhachHang(@RequestBody KhachHangDTO khachHangDTO) {
//		return KhachHangService.save(khachHangDTO);
//	}
}
