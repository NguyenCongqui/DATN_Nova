package com.example.duantotnghiepgiaythethaonova.repository.impl;

import com.example.duantotnghiepgiaythethaonova.QEntityGenarate.*;
import com.example.duantotnghiepgiaythethaonova.constant.OptionContants;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import com.example.duantotnghiepgiaythethaonova.repository.SanPhamSearchRepository;
import com.example.duantotnghiepgiaythethaonova.service.TypeHelperService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class SanPhamSearchRepositoryImpl implements SanPhamSearchRepository {
	private final EntityManager entityManager;
	
	@Autowired
	private TypeHelperService typeHelperService;
	
	@Override
	public Page<SanPham> searchProductExist(SPAndSPCTSearchDto data, Pageable pageable){
		SPAndSPCTSearchDto dataSearch = convertSearchNotNull(data);
		Querydsl querydsl = new Querydsl(entityManager, new PathBuilderFactory().create(SanPham.class));
		JPQLQuery<SanPham> query = new JPAQuery<>(entityManager);
		
		BooleanBuilder where = new BooleanBuilder();
		QSanPhamChiTiet qSanPhamChiTiet = QSanPhamChiTiet.sanPhamChiTiet;
		QSanPham qSanPham = QSanPham.sanPham;
		QKieuDang qKieuDang = QKieuDang.kieuDang;
		QChatLieu qChatLieu = QChatLieu.chatLieu;
		QMauSac qMauSac = QMauSac.mauSac;
		QKichCo qKichCo = QKichCo.kichCo;
		
		where.and(qSanPhamChiTiet.daXoa.isFalse());
		where.and(qSanPham.daXoa.isFalse());
		
		
		List<Integer> lstKieuDang = dataSearch.getKieuDangIds();
		List<Integer> lstChatLieu = dataSearch.getChatLieuIds();
		List<Integer> lstLoaiSanPham = dataSearch.getThuongHieuIds();
		List<Integer> lstKichCo = dataSearch.getKichCoIds();
		List<Integer> lstMauSac = dataSearch.getMauSacIds();
		List<Boolean> lstCoHienThi = dataSearch.getCoHienThi();
		
		if(dataSearch.getGiaOption().equalsIgnoreCase("0")) {
			dataSearch.setGiaMin(BigDecimal.valueOf(0));
			dataSearch.setGiaMax(BigDecimal.valueOf(100000));
		}else if(dataSearch.getGiaOption().equalsIgnoreCase("1")) {
			dataSearch.setGiaMin(BigDecimal.valueOf(100000));
			dataSearch.setGiaMax(BigDecimal.valueOf(200000));
		}else if(dataSearch.getGiaOption().equalsIgnoreCase("2")) {
			dataSearch.setGiaMin(BigDecimal.valueOf(200000));
			dataSearch.setGiaMax(BigDecimal.valueOf(300000));
		}else if(dataSearch.getGiaOption().equalsIgnoreCase("3")) {
			dataSearch.setGiaMin(BigDecimal.valueOf(300000));
			dataSearch.setGiaMax(BigDecimal.valueOf(500000));
		}else if(dataSearch.getGiaOption().equalsIgnoreCase("4")) {
			dataSearch.setGiaMin(BigDecimal.valueOf(500000));
			dataSearch.setGiaMax(BigDecimal.valueOf(1000000));
		}else if(dataSearch.getGiaOption().equalsIgnoreCase("5")) {
			dataSearch.setGiaMin(BigDecimal.valueOf(1000000));
		}
		
		if(lstCoHienThi.size() != 2 && lstCoHienThi.size() != 0) {
			where.and(qSanPhamChiTiet.coHienThi.eq(dataSearch.getCoHienThi().get(0)));
		}
		
//		if(lstKieuDang.size() != 0 && !lstKieuDang.get(0).equals(-1L)) {
//				where.and(qKieuDang.daXoa.isFalse());
//				where.and(qSanPham.kieuDang.id.in(lstKieuDang));
//		}
//		if(lstChatLieu.size() != 0 && !lstChatLieu.get(0).equals(-1L)) {
//			where.and(qChatLieu.daXoa.isFalse());
//			where.and(qSanPham.chatLieu.id.in(lstChatLieu));
//
////		if(lstLoaiSanPham.size() != 0 && !lstLoaiSanPham.get(0).equals(-1L)) {
////			where.and(qThuongH.daXoa.isFalse());
////			where.and(qSanPham.loaiSanPham.id.in(lstLoaiSanPham));
////		}
//		if(lstMauSac.size() != 0 && !lstMauSac.get(0).equals(-1L)) {
//			where.and(qMauSac.daXoa.isFalse());
//			where.and(qSanPhamChiTiet.mauSac.id.in(lstMauSac));
//		}
//		if(lstKichCo.size() != 0 && !lstKichCo.get(0).equals(-1L)) {
//			where.and(qKichCo.daXoa.isFalse());
//			where.and(qSanPhamChiTiet.kichCo.id.in(lstKichCo));
//		}
		
		if(!dataSearch.getTenSanPham().equalsIgnoreCase("-1")) {
			if(StringUtils.isNotEmpty(dataSearch.getTenSanPham())) {
				where.and(qSanPham.tenSanPham.containsIgnoreCase(dataSearch.getTenSanPham()));
			}
		}
		
		if(!dataSearch.getTieuChiGia().equalsIgnoreCase("-1")) {
			if(StringUtils.isNotEmpty(dataSearch.getTieuChiGia())) {
				if(dataSearch.getTieuChiGia().equalsIgnoreCase(OptionContants.caoDenThap)) {
					query.orderBy(qSanPham.gia.desc());
				}else if(dataSearch.getTieuChiGia().equalsIgnoreCase(OptionContants.thapDenCao)) {
					query.orderBy(qSanPham.gia.asc());
				}else {
					query.orderBy(qSanPham.id.desc());
				}
			}else {
				query.orderBy(qSanPham.id.desc());
			}
		}else {
			query.orderBy(qSanPham.id.desc());
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
		
		query.select(qSanPham).from(qSanPham)
				.innerJoin(qKieuDang).on(qSanPham.kieuDang.id.eq(qKieuDang.id))
				.innerJoin(qChatLieu).on(qSanPham.chatLieu.id.eq(qChatLieu.id))
				.innerJoin(qSanPhamChiTiet).on(qSanPhamChiTiet.sanPham.id.eq(qSanPham.id))
				.innerJoin(qMauSac).on(qSanPhamChiTiet.mauSac.id.eq(qMauSac.id))
				.innerJoin(qKichCo).on(qSanPhamChiTiet.kichCo.id.eq(qKichCo.id))
				.where(where).groupBy(qSanPham.id);
		List<SanPham> result = querydsl.applyPagination(pageable, query).fetch();
		Integer totalElements = Math.toIntExact(query.fetchCount());
		return new PageImpl<SanPham>(result, pageable, totalElements);
	}
	
//	convert type of value not null
//	@author cuongdd3
	public SPAndSPCTSearchDto convertSearchNotNull(SPAndSPCTSearchDto dataSearch) {
		List<Integer> lstKieuDangId = typeHelperService.convertObjectTypeListInteger(dataSearch.getKieuDangIds());
		List<Integer> lstChatLieuId = typeHelperService.convertObjectTypeListInteger(dataSearch.getChatLieuIds());
//		List<Integer> lstLoaiSanPhamId = typeHelperService.convertObjectTypeListInteger(dataSearch.getLoaiSanPhamIds());
//		List<Integer> lstPhongCachId = typeHelperService.convertObjectTypeListInteger(dataSearch.getPhongCachIds());
		List<Integer> lstKichCoId = typeHelperService.convertObjectTypeListInteger(dataSearch.getKichCoIds());
		List<Integer> lstMauSacId = typeHelperService.convertObjectTypeListInteger(dataSearch.getMauSacIds());
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
		String giaOption = typeHelperService.convertObjectTypeString(dataSearch.getGiaOption());
//		SPAndSPCTSearchDto result = new SPAndSPCTSearchDto(lstKieuDangId, lstChatLieuId, lstLoaiSanPhamId, lstPhongCachId, lstKichCoId, lstMauSacId, tenSanPham, giaHienHanhMin, giaHienHanhMax, soLuongMin, soLuongMax, coHienThi, nguoiTaoSPCT, nguoiCapNhatSPCT, ngayTaoMin, ngayTaoMax, ngayCapNhatMin, ngayCapNhatMax, tieuChiGia, giaOption);
		return null;
	}
}
