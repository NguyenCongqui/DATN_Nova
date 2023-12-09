package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChiTietSanPham is a Querydsl query type for ChiTietSanPham
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChiTietSanPham extends EntityPathBase<ChiTietSanPham> {

    private static final long serialVersionUID = 1978175279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChiTietSanPham chiTietSanPham = new QChiTietSanPham("chiTietSanPham");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath coHienThi = createBoolean("coHienThi");

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final NumberPath<java.math.BigDecimal> gia = createNumber("gia", java.math.BigDecimal.class);

    public final QDayGiay dayGiay;

    public final QDeGiay deGiay;

    public final ListPath<GioHangChiTiet, QGioHangChiTiet> gioHangChiTiets = this.<GioHangChiTiet, QGioHangChiTiet>createList("gioHangChiTiets", GioHangChiTiet.class, QGioHangChiTiet.class, PathInits.DIRECT2);

    public final ListPath<HoaDonChiTiet, QHoaDonChiTiet> hoaDonChiTiets = this.<HoaDonChiTiet, QHoaDonChiTiet>createList("hoaDonChiTiets", HoaDonChiTiet.class, QHoaDonChiTiet.class, PathInits.DIRECT2);

    public final NumberPath<Integer> idCTSP = createNumber("idCTSP", Integer.class);

    public final QKichCo kichCo;

    public final QLotGiay lotGiay;

    public final StringPath maCTSP = createString("maCTSP");

    public final QMauSac mauSac;

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final QSanPham sanPham;

    public final NumberPath<Integer> soLuong = createNumber("soLuong", Integer.class);

    public QChiTietSanPham(String variable) {
        this(ChiTietSanPham.class, forVariable(variable), INITS);
    }

    public QChiTietSanPham(Path<? extends ChiTietSanPham> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChiTietSanPham(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChiTietSanPham(PathMetadata metadata, PathInits inits) {
        this(ChiTietSanPham.class, metadata, inits);
    }

    public QChiTietSanPham(Class<? extends ChiTietSanPham> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dayGiay = inits.isInitialized("dayGiay") ? new QDayGiay(forProperty("dayGiay")) : null;
        this.deGiay = inits.isInitialized("deGiay") ? new QDeGiay(forProperty("deGiay")) : null;
        this.kichCo = inits.isInitialized("kichCo") ? new QKichCo(forProperty("kichCo")) : null;
        this.lotGiay = inits.isInitialized("lotGiay") ? new QLotGiay(forProperty("lotGiay")) : null;
        this.mauSac = inits.isInitialized("mauSac") ? new QMauSac(forProperty("mauSac")) : null;
        this.sanPham = inits.isInitialized("sanPham") ? new QSanPham(forProperty("sanPham"), inits.get("sanPham")) : null;
    }

}

