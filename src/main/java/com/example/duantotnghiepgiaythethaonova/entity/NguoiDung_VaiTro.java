package com.example.duantotnghiepgiaythethaonova.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NguoiDung_VaiTro")
@Builder
public class NguoiDung_VaiTro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdNguoiDung_VaiTro")
    private Integer IdNguoiDung_VaiTro;

    @ManyToOne
    @JoinColumn(name = "IdNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "IdVaiTro")
    private VaiTro vaiTro;


}
