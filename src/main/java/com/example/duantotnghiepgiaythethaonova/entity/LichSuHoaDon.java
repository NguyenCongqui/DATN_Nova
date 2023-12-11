package com.example.duantotnghiepgiaythethaonova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LichSuHoaDon")
@Builder
public class LichSuHoaDon extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLichSuHoaDon")
    private Integer idLichSuHoaDon;

    @Column(name = "NguoiThaoTac")
    private String nguoiThaoTac;

    @Column(name = "ThaoTac")
    private String thaoTac;

    @ManyToOne
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @Column(name = "trangThai_id")
    private Integer trangThaiID;
    
}
