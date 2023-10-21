package com.example.duantotnghiepgiaythethaonova.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	
	@Column(name="NgayTao",updatable=false)
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayTao;
	
	@Column(name="NguoiTao",updatable=false)
	@CreatedBy
	private String nguoiTao;
	
	@Column(name="NgayCapNhat",updatable=true)
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayCapNhat;
	
	@Column(name="NguoiCapNhat",updatable=true)
	@LastModifiedBy
	private String nguoiCapNhat;
}
