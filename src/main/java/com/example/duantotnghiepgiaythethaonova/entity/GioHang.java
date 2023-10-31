package com.example.duantotnghiepgiaythethaonova.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GioHang")
@Builder
@EqualsAndHashCode(callSuper=false)
public class GioHang extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGioHang")
    private Integer idGioHang;

//    @Column(name = "NgayTao")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date NgayTao;
//    @Column(name = "NgayCapNhat")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date NgayCapNhat;
//    @Column(name = "NguoiTao")
//    private String NguoiTao;
//    @Column(name = "NguoiCapNhat")
//    private String NguoiCapNhat;

@Column(name = "NgayTao",columnDefinition = "nvarchar(256)  ")
@CreatedDate
private Date ngayTao;

    @Column(name = "NgayCapNhat",columnDefinition = "nvarchar(256)  ")
    private Date ngayCapNhat;

    @OneToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @Column(name = "TongTien")
    private Integer tongTien;

    @OneToMany(mappedBy = "gioHang")
    private List<GioHangChiTiet> gioHangChiTiets = new ArrayList<GioHangChiTiet>();



    @Override
    public String toString() {
        return "GioHang{" +
                "ngayTao=" + ngayTao +
                ", ngayCapNhat=" + ngayCapNhat +
                ", khachHang=" + khachHang.getIdKhachHang() +
                ", trangThai=" + trangThai +
                ", tongTien=" + tongTien +
                ", gioHangChiTiets=" + gioHangChiTiets.size() +
                '}';
    }

}
