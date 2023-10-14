package com.example.duantotnghiepgiaythethaonova.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	
	@Column(name="NgayTao",updatable=false)
	@CreatedDate
	private Date NgayTao;
	
	@Column(name="NguoiTao",updatable=false)
	@CreatedBy
	private String NguoiTao;
	
	@Column(name="NgayCapNhat",updatable=true)
	@LastModifiedDate
	private Date NgayCapNhat;
	
	@Column(name="NguoiCapNhat",updatable=true)
	@LastModifiedBy
	private String NguoiCapNhat;
}
