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
    public Page<SanPham> searchProductExist(SPAndSPCTSearchDto data, Pageable pageable) {
        SPAndSPCTSearchDto dataSearch = convertSearchNotNull(data);
        Querydsl querydsl = new Querydsl(entityManager, new PathBuilderFactory().create(SanPham.class));
        JPQLQuery<SanPham> query = new JPAQuery<>(entityManager);

        BooleanBuilder where = new BooleanBuilder();
        QChiTietSanPham qChiTietSanPham = QChiTietSanPham.chiTietSanPham;
        QSanPham qSanPham = QSanPham.sanPham;
        QKieuDang qKieuDang = QKieuDang.kieuDang;
        QChatLieu qChatLieu = QChatLieu.chatLieu;
        QThuongHieu qThuongHieu = QThuongHieu.thuongHieu;
        QMauSac qMauSac = QMauSac.mauSac;
        QKichCo qKichCo = QKichCo.kichCo;
        QDayGiay qDayGiay = QDayGiay.dayGiay;
        QDeGiay qDeGiay = QDeGiay.deGiay;
        QLotGiay qLotGiay = QLotGiay.lotGiay;

        where.and(qChiTietSanPham.daXoa.isFalse());
        where.and(qSanPham.daXoa.isFalse());


        List<Integer> lstKieuDang = dataSearch.getKieuDangIds();
        List<Integer> lstChatLieu = dataSearch.getChatLieuIds();
        List<Integer> lstThuongHieu = dataSearch.getThuongHieuIds();
        List<Integer> lstKichCo = dataSearch.getKichCoIds();
        List<Integer> lstMauSac = dataSearch.getMauSacIds();
        List<Integer> lstDayGiay = dataSearch.getDayGiayIds();
        List<Integer> lstDeGiay = dataSearch.getDeGiayIds();
        List<Integer> lstLotGiay = dataSearch.getLotGiayIds();
        List<Boolean> lstCoHienThi = dataSearch.getCoHienThi();

        if (dataSearch.getGiaOption().equalsIgnoreCase("0")) {
            dataSearch.setGiaMin(BigDecimal.valueOf(0));
            dataSearch.setGiaMax(BigDecimal.valueOf(100000));
        } else if (dataSearch.getGiaOption().equalsIgnoreCase("1")) {
            dataSearch.setGiaMin(BigDecimal.valueOf(100000));
            dataSearch.setGiaMax(BigDecimal.valueOf(200000));
        } else if (dataSearch.getGiaOption().equalsIgnoreCase("2")) {
            dataSearch.setGiaMin(BigDecimal.valueOf(200000));
            dataSearch.setGiaMax(BigDecimal.valueOf(300000));
        } else if (dataSearch.getGiaOption().equalsIgnoreCase("3")) {
            dataSearch.setGiaMin(BigDecimal.valueOf(300000));
            dataSearch.setGiaMax(BigDecimal.valueOf(500000));
        } else if (dataSearch.getGiaOption().equalsIgnoreCase("4")) {
            dataSearch.setGiaMin(BigDecimal.valueOf(500000));
            dataSearch.setGiaMax(BigDecimal.valueOf(1000000));
        } else if (dataSearch.getGiaOption().equalsIgnoreCase("5")) {
            dataSearch.setGiaMin(BigDecimal.valueOf(1000000));
        }

        if (lstCoHienThi.size() != 2 && lstCoHienThi.size() != 0) {
            where.and(qChiTietSanPham.coHienThi.eq(dataSearch.getCoHienThi().get(0)));
        }

        if (lstKieuDang.size() != 0 && !lstKieuDang.get(0).equals(-1)) {
            where.and(qKieuDang.daXoa.isFalse());
            where.and(qSanPham.kieuDang.idKieuDang.in(lstKieuDang));
        }
        if (lstChatLieu.size() != 0 && !lstChatLieu.get(0).equals(-1)) {
            where.and(qChatLieu.daXoa.isFalse());
            where.and(qSanPham.chatLieu.idChatLieu.in(lstChatLieu));
        }
        if (lstThuongHieu.size() != 0 && !lstThuongHieu.get(0).equals(-1)) {
            where.and(qThuongHieu.daXoa.isFalse());
            where.and(qSanPham.thuongHieu.idThuongHieu.in(lstThuongHieu));
        }
        if (lstMauSac.size() != 0 && !lstMauSac.get(0).equals(-1)) {
            where.and(qMauSac.daXoa.isFalse());
            where.and(qChiTietSanPham.mauSac.idMauSac.in(lstMauSac));
        }
        if (lstKichCo.size() != 0 && !lstKichCo.get(0).equals(-1)) {
            where.and(qKichCo.daXoa.isFalse());
            where.and(qChiTietSanPham.kichCo.idKichCo.in(lstKichCo));
        }
        if (lstDayGiay.size() != 0 && !lstDayGiay.get(0).equals(-1)) {
            where.and(qDayGiay.daXoa.isFalse());
            where.and(qChiTietSanPham.dayGiay.idDayGiay.in(lstDayGiay));
        }
        if (lstDeGiay.size() != 0 && !lstDeGiay.get(0).equals(-1)) {
            where.and(qDeGiay.daXoa.isFalse());
            where.and(qChiTietSanPham.deGiay.idDeGiay.in(lstDeGiay));
        }
        if (lstLotGiay.size() != 0 && !lstLotGiay.get(0).equals(-1)) {
            where.and(qLotGiay.daXoa.isFalse());
            where.and(qChiTietSanPham.lotGiay.idLotGiay.in(lstLotGiay));
        }
        if (!dataSearch.getTenSanPham().equalsIgnoreCase("-1")) {
            if (StringUtils.isNotEmpty(dataSearch.getTenSanPham())) {
                where.and(qSanPham.tenSanPham.containsIgnoreCase(dataSearch.getTenSanPham()));
            }
        }
//        if (!dataSearch.getMaSanPham().equalsIgnoreCase("-1")) {
//            if (StringUtils.isNotEmpty(dataSearch.getMaSanPham())) {
//                where.and(qSanPham.maSanPham.containsIgnoreCase(dataSearch.getMaSanPham()));
//            }
//        }
        if (!dataSearch.getTieuChiGia().equalsIgnoreCase("-1")) {
            if (StringUtils.isNotEmpty(dataSearch.getTieuChiGia())) {
                if (dataSearch.getTieuChiGia().equalsIgnoreCase(OptionContants.caoDenThap)) {
                    query.orderBy(qChiTietSanPham.gia.desc());
                } else if (dataSearch.getTieuChiGia().equalsIgnoreCase(OptionContants.thapDenCao)) {
                    query.orderBy(qChiTietSanPham.gia.asc());
                } else {
                    query.orderBy(qSanPham.idSanPham.desc());
                }
            } else {
                query.orderBy(qSanPham.idSanPham.desc());
            }
        } else {
            query.orderBy(qSanPham.idSanPham.desc());
        }

        if (!dataSearch.getNguoiTaoSPCT().equalsIgnoreCase("-1")) {
            if (StringUtils.isNotEmpty(dataSearch.getNguoiTaoSPCT())) {
                where.and(qSanPham.nguoiTao.containsIgnoreCase(dataSearch.getNguoiTaoSPCT()));
            }
        }

        if (!dataSearch.getNguoiCapNhatSPCT().equalsIgnoreCase("-1")) {
            if (StringUtils.isNotEmpty(dataSearch.getNguoiCapNhatSPCT())) {
                where.and(qSanPham.nguoiTao.containsIgnoreCase(dataSearch.getNguoiCapNhatSPCT()));
            }
        }

        if (dataSearch.getSoLuongMin().intValue() != -1 &&
                dataSearch.getSoLuongMax().intValue() != -1) {
            if (StringUtils.isNotEmpty(dataSearch.getSoLuongMin().toString()) &&
                    StringUtils.isNotEmpty(dataSearch.getSoLuongMax().toString())) {
                where.and(qChiTietSanPham.soLuong.between(dataSearch.getSoLuongMin(), dataSearch.getSoLuongMax()));
            }
        }
        if (dataSearch.getSoLuongMin().intValue() != -1 &&
                dataSearch.getSoLuongMax().intValue() == -1) {
            where.and(qChiTietSanPham.soLuong.goe(dataSearch.getSoLuongMin()));
        } else if (dataSearch.getSoLuongMin().intValue() == -1 &&
                dataSearch.getSoLuongMax().intValue() != -1) {
            where.and(qChiTietSanPham.soLuong.loe(dataSearch.getSoLuongMax()));
        }

        if (!dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
                !dataSearch.getGiaMax().toString().equalsIgnoreCase("-1")) {
            if (!dataSearch.getGiaMin().toString().isEmpty() &&
                    !dataSearch.getGiaMax().toString().isEmpty()) {
                where.and(qChiTietSanPham.gia.between(dataSearch.getGiaMin(), dataSearch.getGiaMax()));
            }
        }
        if (!dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
                dataSearch.getGiaMax().toString().equalsIgnoreCase("-1")) {
            if (!dataSearch.getGiaMin().toString().isEmpty()) {
                where.and(qChiTietSanPham.gia.goe(dataSearch.getGiaMin()));
            }
        } else if (dataSearch.getGiaMin().toString().equalsIgnoreCase("-1") &&
                !dataSearch.getGiaMax().toString().equalsIgnoreCase("-1")) {
            if (!dataSearch.getGiaMax().toString().isEmpty()) {
                where.and(qChiTietSanPham.gia.loe(dataSearch.getGiaMax()));
            }
        }

        //Check validate Date eq epoch
        final Date CheckDate = Date.from(Instant.EPOCH);
//		Date now = Date.from(Instant.now());

        if (!dataSearch.getNgayTaoMin().toString().equalsIgnoreCase("" + CheckDate) &&
                !dataSearch.getNgayTaoMax().toString().equalsIgnoreCase("" + CheckDate)) {
            if (!dataSearch.getNgayTaoMin().toString().isEmpty() &&
                    !dataSearch.getNgayTaoMax().toString().isEmpty()) {
                where.and(qChiTietSanPham.ngayTao.between(dataSearch.getNgayTaoMin(), dataSearch.getNgayTaoMax()));
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


        if (!dataSearch.getNgayCapNhatMin().toString().equalsIgnoreCase("" + CheckDate) &&
                !dataSearch.getNgayCapNhatMax().toString().equalsIgnoreCase("" + CheckDate)) {
            if (!dataSearch.getNgayCapNhatMin().toString().isEmpty() &&
                    !dataSearch.getNgayCapNhatMax().toString().isEmpty()) {
                where.and(qChiTietSanPham.ngayCapNhat.between(dataSearch.getNgayCapNhatMin(), dataSearch.getNgayCapNhatMax()));
            }
        }

//		if(!dataSearch.getNgayCapNhatMin().toString().equalsIgnoreCase(""+CheckDate) &&
//				dataSearch.getNgayCapNhatMax().toString().equalsIgnoreCase(""+CheckDate)) {
//			if(!dataSearch.getNgayCapNhatMin().toString().isEmpty()) {
//				where.and(qSanPham.ngayCapNhat.between(dataSearch.getNgayCapNhatMin(), now));
//			}
//		}

        query.select(qSanPham).from(qSanPham)
                .innerJoin(qKieuDang).on(qSanPham.kieuDang.idKieuDang.eq(qKieuDang.idKieuDang))
                .innerJoin(qChatLieu).on(qSanPham.chatLieu.idChatLieu.eq(qChatLieu.idChatLieu))
                .innerJoin(qThuongHieu).on(qSanPham.thuongHieu.idThuongHieu.eq(qThuongHieu.idThuongHieu))
                .innerJoin(qChiTietSanPham).on(qChiTietSanPham.sanPham.idSanPham.eq(qSanPham.idSanPham))

                .innerJoin(qMauSac).on(qChiTietSanPham.mauSac.idMauSac.eq(qMauSac.idMauSac))
                .innerJoin(qKichCo).on(qChiTietSanPham.kichCo.idKichCo.eq(qKichCo.idKichCo))
                .innerJoin(qDayGiay).on(qChiTietSanPham.dayGiay.idDayGiay.eq(qDayGiay.idDayGiay))
                .innerJoin(qDeGiay).on(qChiTietSanPham.deGiay.idDeGiay.eq(qDeGiay.idDeGiay))
                .innerJoin(qLotGiay).on(qChiTietSanPham.lotGiay.idLotGiay.eq(qLotGiay.idLotGiay))
                .where(where).groupBy(qSanPham.idSanPham,
                qSanPham.ngayCapNhat,
                qSanPham.ngayTao,
                qSanPham.nguoiCapNhat,
                qSanPham.nguoiTao,
                qSanPham.chatLieu.idChatLieu,
                qSanPham.kieuDang.idKieuDang,
                qSanPham.thuongHieu.idThuongHieu,
                qSanPham.daXoa,
                qSanPham.gia,
                qSanPham.moTa,
                qSanPham.tenSanPham,qSanPham.maSanPham);
        List<SanPham> result = querydsl.applyPagination(pageable, query).fetch();
        Integer totalElements = Math.toIntExact(query.fetchCount());
        return new PageImpl<SanPham>(result, pageable, totalElements);
    }

    //	convert type of value not null
//	@author
    public SPAndSPCTSearchDto convertSearchNotNull(SPAndSPCTSearchDto dataSearch) {
        List<Integer> lstKieuDangId = typeHelperService.convertObjectTypeListInteger(dataSearch.getKieuDangIds());
        List<Integer> lstChatLieuId = typeHelperService.convertObjectTypeListInteger(dataSearch.getChatLieuIds());
        List<Integer> lstThuongHieuId = typeHelperService.convertObjectTypeListInteger(dataSearch.getThuongHieuIds());
        List<Integer> lstKichCoId = typeHelperService.convertObjectTypeListInteger(dataSearch.getKichCoIds());
        List<Integer> lstMauSacId = typeHelperService.convertObjectTypeListInteger(dataSearch.getMauSacIds());
        List<Integer> lstDayGiayId = typeHelperService.convertObjectTypeListInteger(dataSearch.getDayGiayIds());
        List<Integer> lstDeGiayId = typeHelperService.convertObjectTypeListInteger(dataSearch.getDeGiayIds());
        List<Integer> lstLotGiayId = typeHelperService.convertObjectTypeListInteger(dataSearch.getLotGiayIds());
        String tenSanPham = typeHelperService.convertObjectTypeString(dataSearch.getTenSanPham());
        String maSanPham = typeHelperService.convertObjectTypeString(dataSearch.getMaSanPham());
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
//        SPAndSPCTSearchDto result = new SPAndSPCTSearchDto(lstKieuDangId, lstChatLieuId, lstThuongHieuId, lstKichCoId, lstMauSacId, tenSanPham giaHienHanhMin, giaHienHanhMax, soLuongMin, soLuongMax, coHienThi, nguoiTaoSPCT, nguoiCapNhatSPCT, ngayTaoMin, ngayTaoMax, ngayCapNhatMin, ngayCapNhatMax, tieuChiGia, giaOption);

        SPAndSPCTSearchDto result = new SPAndSPCTSearchDto(lstKieuDangId, lstChatLieuId, lstThuongHieuId, lstKichCoId, lstMauSacId,lstDayGiayId,lstDeGiayId,lstLotGiayId ,tenSanPham,maSanPham, giaHienHanhMin, giaHienHanhMax, soLuongMin, soLuongMax, coHienThi, nguoiTaoSPCT, nguoiCapNhatSPCT, ngayTaoMin, ngayTaoMax, ngayCapNhatMin, ngayCapNhatMax, tieuChiGia, giaOption);
        return result;
    }
}
