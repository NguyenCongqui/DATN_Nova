package com.example.duantotnghiepgiaythethaonova.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiaChiDTO extends BaseDTO<DiaChiDTO> {


    private String diaChi;

   // @NotBlank(message = "Chưa chọn thành phố !")
    private String tenThanhPho;

   // @NotBlank(message = "Chưa chọn quận huyện !")
    private String tenQuanHuyen;

   // @NotBlank(message = "Chưa chọn xã !")
    private String teXaPhuong;

   // @NotBlank(message = "Vui lòng nhập số nhà !")
    private String soNha;

   // @NotBlank(message = "Vui lòng nhập họ tên !")
    private String hoTen;

   // @NotBlank(message = "Vui lòng nhập số điện thoại !")
    private String soDienThoai;

    private int idThanhPho;
    private int idHuyen;
    private int xa;

    private int page;

    private int limit;

    private int totalPages;

    private int totalItems;

    private List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();

    private Integer khachHangId;

    private boolean laDiaChiMacDinh;
}