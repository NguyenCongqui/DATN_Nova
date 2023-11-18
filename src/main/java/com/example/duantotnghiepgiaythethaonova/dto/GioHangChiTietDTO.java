package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class GioHangChiTietDTO extends BaseDTO<GioHangChiTietDTO> {

    private Integer sanPhamChiTietId;

    private Integer gioHangId;

    private int soLuong;

    private BigDecimal donGia;

    private BigDecimal thanhTien;

    private int trangThai;

    private Boolean daXoa;

    private SanPhamChiTietDTO sanPhamChiTietDTO;

    private boolean isChecked; // Thêm trạng thái isChecked

    // Thêm getter và setter cho isChecked (lựa chọn tùy chọn)
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
