package com.example.duantotnghiepgiaythethaonova.repository.impl;

import com.example.duantotnghiepgiaythethaonova.QEntityGenarate.*;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.service.SanPhamChiTietSearchRepository;
import com.example.duantotnghiepgiaythethaonova.service.TypeHelperService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Repository
@AllArgsConstructor
public class SanPhamChiTietSearchRepositoryImpl implements SanPhamChiTietSearchRepository {
	private final EntityManager entityManager;
	
	@Autowired
	private TypeHelperService typeHelperService;
	
	@Override
	public Page<ChiTietSanPham> searchProductDetailExist(SPAndSPCTSearchDto data, Pageable pageable){
		SPAndSPCTSearchDto dataSearch = convertSearchNotNull(data);
		
		JPQLQuery<ChiTietSanPham> query = new JPAQuery<>(entityManager);
		Querydsl querydsl = new Querydsl(entityManager, new PathBuilderFactory().create(ChiTietSanPham.class));
		
		BooleanBuilder where = new BooleanBuilder();
		
		QSanPhamChiTiet qSanPhamChiTiet = QSanPhamChiTiet.sanPhamChiTiet;
		QSanPham qSanPham = QSanPham.sanPham;
		QKieuDang qKieuDang = QKieuDang.kieuDang;
		QDeGiay qPhongCach = QDeGiay.deGiay;
		QChatLieu qChatLieu = QChatLieu.chatLieu;
		QMauSac qMauSac = QMauSac.mauSac;
		QKichCo qKichCo = QKichCo.kichCo;
		QThuongHieu qLoaiSanPham = QThuongHieu.thuongHieu;
		
		where.and(qSanPhamChiTiet.daXoa.isFalse());
		where.and(qSanPham.daXoa.isFalse());
		
		
		List<Integer> lstKieuDang = dataSearch.getKieuDangIds();
		List<Integer> lstChatLieu = dataSearch.getChatLieuIds();
		List<Integer> lstThuongHieu = dataSearch.getThuongHieuIds();
		List<Integer> lstDeGiay = dataSearch.getDeGiayIds();
		List<Integer> lstKichCo = dataSearch.getKichCoIds();
		List<Integer> lstMauSac = dataSearch.getMauSacIds();
		
		where.and(qSanPhamChiTiet.coHienThi.isTrue());
		
		if(!lstKieuDang.isEmpty() && !lstKieuDang.get(0).equals(-1L)) {
			where.and(qSanPham.kieuDang.id.in(lstKieuDang));
		}
		if(!lstChatLieu.isEmpty() && !lstChatLieu.get(0).equals(-1L)) {
			where.and(qSanPham.chatLieu.id.in(lstChatLieu));
		}
		if(!lstDeGiay.isEmpty() && !lstDeGiay.get(0).equals(-1L)) {
			where.and(qSanPham.phongCach.id.in(lstDeGiay));
		}
		if(!lstThuongHieu.isEmpty() && !lstThuongHieu.get(0).equals(-1L)) {
			where.and(qSanPham.thuongHieu.id.in(lstThuongHieu));
		}
		if(!lstMauSac.isEmpty() && !lstMauSac.get(0).equals(-1L)) {
			where.and(qSanPhamChiTiet.mauSac.id.in(lstMauSac));
		}
		if(!lstKichCo.isEmpty() && !lstKichCo.get(0).equals(-1L)) {
			where.and(qSanPhamChiTiet.kichCo.id.in(lstKichCo));
		}
		
		if(!dataSearch.getTenSanPham().equalsIgnoreCase("-1")) {
			if(StringUtils.isNotEmpty(dataSearch.getTenSanPham())) {
				where.and(qSanPham.tenSanPham.containsIgnoreCase(dataSearch.getTenSanPham()));
			}
		}
		
		if(lstThuongHieu.size() != 0 && !lstThuongHieu.get(0).equals(-1L)) {
			where.and(qLoaiSanPham.daXoa.isFalse());
			where.and(qSanPham.thuongHieu.id.in(lstThuongHieu));
		}
		
		if(!dataSearch.getNguoiTaoSPCT().equalsIgnoreCase("-1")) {
			if(StringUtils.isNotEmpty(dataSearch.getNguoiTaoSPCT())) {
				where.and(qSanPham.nguoiTao.containsIgnoreCase(dataSearch.getNguoiTaoSPCT()));
			}
		}
		
		if(!dataSearch.getNguoiCapNhatSPCT().equalsIgnoreCase("-1")) {
			if(StringUtils.isNotEmpty(dataSearch.getNguoiCapNhatSPCT())) {
				where.and(qSanPham.nguoiTao.containsIgnoreCase(dataSearch.getNguoiCapNhatSPCT()));
			}
		}
		
		if(dataSearch.getSoLuongMin().intValue() != -1 &&
				dataSearch.getSoLuongMax().intValue() != -1) {
			if(StringUtils.isNotEmpty(dataSearch.getSoLuongMin().toString()) &&
					StringUtils.isNotEmpty(dataSearch.getSoLuongMax().toString())) {
				where.and(qSanPhamChiTiet.soLuong.between(dataSearch.getSoLuongMin(), dataSearch.getSoLuongMax()));
			}
		}
		if(dataSearch.getSoLuongMin().intValue() != -1 &&
				dataSearch.getSoLuongMax().intValue() == -1) {
			where.and(qSanPhamChiTiet.soLuong.goe(dataSearch.getSoLuongMin()));
		}else if(dataSearch.getSoLuongMin().intValue() == -1 &&
				dataSearch.getSoLuongMax().intValue() != -1) {
			where.and(qSanPhamChiTiet.soLuong.loe(dataSearch.getSoLuongMax()));
		}
		
		if(!dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
				!dataSearch.getGiaMax().toString().equalsIgnoreCase("-1") ) {
			if(!dataSearch.getGiaMin().toString().isEmpty() && 
					!dataSearch.getGiaMax().toString().isEmpty()) {
				where.and(qSanPham.gia.between(dataSearch.getGiaMin(), dataSearch.getGiaMax()));
			}
		}
		if(!dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
				dataSearch.getGiaMax().toString().equalsIgnoreCase("-1")) {
			if(!dataSearch.getGiaMin().toString().isEmpty()) {
				where.and(qSanPham.gia.goe(dataSearch.getGiaMin()));
			}
		}else if(dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
				!dataSearch.getGiaMax().toString().equalsIgnoreCase("-1")) {
			if(!dataSearch.getGiaMax().toString().isEmpty()) {
				where.and(qSanPham.gia.loe(dataSearch.getGiaMax()));
			}
		}
		
		//Check validate Date eq epoch
		final Date CheckDate = Date.from(Instant.EPOCH);
//		Date now = Date.from(Instant.now());
		
		if(!dataSearch.getNgayTaoMin().toString().equalsIgnoreCase(""+CheckDate) &&
				!dataSearch.getNgayTaoMax().toString().equalsIgnoreCase(""+CheckDate)) {
			if(!dataSearch.getNgayTaoMin().toString().isEmpty() && 
					!dataSearch.getNgayTaoMax().toString().isEmpty()) {
				where.and(qSanPhamChiTiet.ngayTao.between(dataSearch.getNgayTaoMin(), dataSearch.getNgayTaoMax()));
			}
		}
		//- so sánh ngày
		//- không dùng được -> lí do: kiểu Date có sai số lớn -> Instant is solution
//		if(!dataSearch.getNgayTaoMin().toString().equalsIgnoreCase(""+CheckDate) &&
//				dataSearch.getNgayTaoMax().toString().equalsIgnoreCase(""+CheckDate)) {
//			if(!dataSearch.getNgayTaoMin().toString().isEmpty()) {
//				where.and(qSanPham.ngayTao.between(dataSearch.getNgayTaoMin(), now));
//			}
//		}
		
		
		if(!dataSearch.getNgayCapNhatMin().toString().equalsIgnoreCase(""+CheckDate) &&
				!dataSearch.getNgayCapNhatMax().toString().equalsIgnoreCase(""+CheckDate)) {
			if(!dataSearch.getNgayCapNhatMin().toString().isEmpty() && 
					!dataSearch.getNgayCapNhatMax().toString().isEmpty()) {
				where.and(qSanPhamChiTiet.ngayCapNhat.between(dataSearch.getNgayCapNhatMin(), dataSearch.getNgayCapNhatMax()));
			}
		}
		
//		if(!dataSearch.getNgayCapNhatMin().toString().equalsIgnoreCase(""+CheckDate) &&
//				dataSearch.getNgayCapNhatMax().toString().equalsIgnoreCase(""+CheckDate)) {
//			if(!dataSearch.getNgayCapNhatMin().toString().isEmpty()) {
//				where.and(qSanPham.ngayCapNhat.between(dataSearch.getNgayCapNhatMin(), now));
//			}
//		}
		
		query.select(qSanPhamChiTiet).from(qSanPhamChiTiet)
				.leftJoin(qMauSac).on(qSanPhamChiTiet.mauSac.id.eq(qMauSac.id))
				.innerJoin(qKichCo).on(qSanPhamChiTiet.kichCo.id.eq(qKichCo.id))
				.innerJoin(qSanPham).on(qSanPhamChiTiet.sanPham.id.eq(qSanPham.id))
				.innerJoin(qPhongCach).on(qSanPham.phongCach.id.eq(qPhongCach.id))
				.innerJoin(qKieuDang).on(qSanPham.kieuDang.id.eq(qKieuDang.id))
				.innerJoin(qChatLieu).on(qSanPham.chatLieu.id.eq(qChatLieu.id))
				.innerJoin(qLoaiSanPham).on(qSanPham.thuongHieu.id.eq(qLoaiSanPham.id))
				.where(where)
				.orderBy(qSanPhamChiTiet.sanPham.id.desc())
				.fetch();
		List<ChiTietSanPham> result = querydsl.applyPagination(pageable, query).fetch();
		Long totalElements = query.fetchCount();
		return new PageImpl<ChiTietSanPham>(result, pageable, totalElements);
	}
	
//	convert type of value not null
//	@author cuongdd3
	public SPAndSPCTSearchDto convertSearchNotNull(SPAndSPCTSearchDto dataSearch) {
		List<Integer> lstKieuDangId = typeHelperService.convertObjectTypeListLong(dataSearch.getKieuDangIds());
		List<Integer> lstChatLieuId = typeHelperService.convertObjectTypeListLong(dataSearch.getChatLieuIds());
		List<Integer> lstThuongHieuId = typeHelperService.convertObjectTypeListLong(dataSearch.getThuongHieuIds());
		List<Integer> lstDeGiayId = typeHelperService.convertObjectTypeListLong(dataSearch.getDeGiayIds());
		List<Integer> lstKichCoId = typeHelperService.convertObjectTypeListLong(dataSearch.getKichCoIds());
		List<Integer> lstMauSacId = typeHelperService.convertObjectTypeListLong(dataSearch.getMauSacIds());
		String tenSanPham = typeHelperService.convertObjectTypeString(dataSearch.getTenSanPham());
		BigDecimal giaHienHanhMin = typeHelperService.convertObjectTypeBigDecimal(dataSearch.getGiaMin());
		BigDecimal giaHienHanhMax = typeHelperService.convertObjectTypeBigDecimal(dataSearch.getGiaMax());
		int soLuongMin = typeHelperService.convertObjectTypeListInt(dataSearch.getSoLuongMin());
		int soLuongMax = typeHelperService.convertObjectTypeListInt(dataSearch.getSoLuongMax());
		List<Boolean> coHienThi = typeHelperService.convertObjectTypeListBoolean(dataSearch.getCoHienThi());
		String nguoiTaoSPCT = typeHelperService.convertObjectTypeString(dataSearch.getNguoiTaoSPCT());
		String nguoiCapNhatSPCT = typeHelperService.convertObjectTypeString(dataSearch.getNguoiCapNhatSPCT());
		Date ngayTaoMin = typeHelperService.convertObjectTypeDate(dataSearch.getNgayTaoMin());
		Date ngayTaoMax = typeHelperService.convertObjectTypeDate(dataSearch.getNgayTaoMax());
		Date ngayCapNhatMin = typeHelperService.convertObjectTypeDate(dataSearch.getNgayCapNhatMin());
		Date ngayCapNhatMax = typeHelperService.convertObjectTypeDate(dataSearch.getNgayCapNhatMax());
		String tieuChiGia = typeHelperService.convertObjectTypeString(dataSearch.getTieuChiGia());
		SPAndSPCTSearchDto result = new SPAndSPCTSearchDto(lstKieuDangId, lstChatLieuId, lstThuongHieuId, lstDeGiayId, lstKichCoId, lstMauSacId, tenSanPham, giaHienHanhMin, giaHienHanhMax, soLuongMin, soLuongMax, coHienThi, nguoiTaoSPCT, nguoiCapNhatSPCT, ngayTaoMin, ngayTaoMax, ngayCapNhatMin, ngayCapNhatMax, tieuChiGia, null);
		return result;
	}
}
