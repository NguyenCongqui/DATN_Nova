package com.example.duantotnghiepgiaythethaonova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ThuongHieu")
public class ThuongHieu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdThuongHieu")
    private Integer IdThuongHieu;
    @Column(name = "TenThuongHieu")
    private String TenThuongHieu;
    @Column(name = "MoTa")
    private String MoTa;
    @Column(name = "NgayTao")
    private Date NgayTao;
    @Column(name = "NgaySua")
    private Date NgaySua;
    @Column(name = "NguoiTao")
    private String NguoiTao;
    @Column(name = "NguoiSua")
    private String NguoiSua;
    @Column(name = "TrangThai")
    private Integer TrangThai;
//    @JsonIgnore
//    @OneToMany(mappedBy = "thuonghieu")
//    private Set<CTSP> ctsps = new HashSet<CTSP>();

}
