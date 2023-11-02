package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GioHangDTO extends BaseDTO<GioHangDTO> {

    private Integer khachHangId;

    private int trangThai;

    private int tongTien;

    private String maGiamGia;

    private Integer soTienGiamGia;

    private int thanhTien;

    private List<GioHangChiTietDTO> listGioHangChiTiets = new ArrayList<GioHangChiTietDTO>();

    public List<Integer> getSelectedCartItemIds() {
        List<Integer> selectedCartItemIds = new ArrayList<>();
        for (GioHangChiTietDTO gioHangChiTietDTO : listGioHangChiTiets) {
            if (gioHangChiTietDTO.isChecked()) {
                selectedCartItemIds.add(gioHangChiTietDTO.getGioHangId());
            }
        }
        return selectedCartItemIds;
    }

}
