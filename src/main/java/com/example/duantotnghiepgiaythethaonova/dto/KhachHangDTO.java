package com.example.duantotnghiepgiaythethaonova.dto;

import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDTO extends BaseDTO<KhachHangDTO> {
	
		@Email(message="Email không đúng định dạng !")
		@NotBlank(message="Email không được để trống !")
	    private String email;
//
//		@NotBlank(message = "Mật khẩu không được bỏ trống !")
//		@Size(min=6 , max = 12 , message="Mật khẩu phải lớn hơn 6 và nhỏ hơn 12 ký tự !")
	    private String matKhau;

	    
	    @NotBlank(message="Họ tên không được để trống !")
	    @Size(min=10 , max = 256 , message="Họ tên phải lớn hơn 10 và nhỏ hơn 256 ký tự !")
	    private String hoTen;

	    private Integer soLanMua;

	    private Date ngayTao;
	    
	    
	    @NotBlank(message="Số điện thoại không được để trống !")
	    @Pattern(regexp="^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})",message="Số điện thoại không đúng định dạng !")
	    private String soDienThoai;

	    private Integer trangThai  = 2;
	    
	    private int page ;
	    
	    private int limit ;
	    
	    private int totalPages; 
	    
	    private int totalItems;
	    
	    private String input = "" ;
	    
	    @NotBlank(message="Chưa chọn thành phố !")
	    private String city ;

	    @NotBlank(message="Chưa chọn quận huyện !")
	    private String district ;

	    @NotBlank(message="Chưa chọn xã !")
	    private String ward ;

	    @NotBlank(message="Vui lòng nhập số nhà !")
	    private String soNha ;
	    
	    private String codeSend ;
	    
	    private String matKhauXacNhan ;
	    
	    private List<KhachHangDTO> listKhachHangDTO = new ArrayList<KhachHangDTO>();
	    
		private List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();

		private DiaChi diaChiID;
}
