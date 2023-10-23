package com.example.duantotnghiepgiaythethaonova.dto.search;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SPAndSPCTSearchDto {
    private List<Integer> kieuDangIds;

    private List<Integer> chatLieuIds;

    private List<Integer>  thuongHieuIds;

    private List<Integer> kichCoIds;

    private List<Integer> mauSacIds;
//
//    private List<Integer> dayGiayIds;
//
//    private List<Integer> deGiayIds;
//
//    private List<Integer> lotGiayIds;

    private String tenSanPham;

    @DecimalMin(value = "0", message = "Giá nhỏ nhất không được nhỏ hơn 0")
    private BigDecimal giaMin;

    @DecimalMin(value = "0", message = "Giá lớn nhất không được nhỏ hơn 0")
    private BigDecimal giaMax;

    @Min(value = 0, message = "Số lượng nhỏ nhất không được nhỏ hơn 0")
    private Integer soLuongMin;

    @Min(value = 0, message = "Số lượng lớn nhất không được nhỏ hơn 0")
    private Integer soLuongMax;

    private List<Boolean> coHienThi;

    private String nguoiTaoSPCT;

    private String nguoiCapNhatSPCT;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTaoMin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTaoMax;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhatMin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayCapNhatMax;

    // customer search theo tieu chi
    private String tieuChiGia;

    // customer search theo gia mac dinh
    private String giaOption;
}
