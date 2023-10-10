package com.example.duantotnghiepgiaythethaonova.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TrangThai")
@Builder
public class TrangThai implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdTrangThai;
    private String Name;
}
