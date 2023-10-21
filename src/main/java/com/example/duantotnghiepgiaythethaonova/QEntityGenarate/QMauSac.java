package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.HinhAnh;
import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QMauSac is a Querydsl query type for MauSac
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMauSac extends EntityPathBase<MauSac> {

    private static final Integer serialVersionUID = -1990321258;

    public static final QMauSac mauSac = new QMauSac("mauSac");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final ListPath<HinhAnh, QHinhAnh> hinhAnhs = this.<HinhAnh, QHinhAnh>createList("hinhAnhs", HinhAnh.class, QHinhAnh.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath maMauSac = createString("maMauSac");

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final ListPath<ChiTietSanPham, QSanPhamChiTiet> sanPhamChiTiets = this.<ChiTietSanPham, QSanPhamChiTiet>createList("sanPhamChiTiets", ChiTietSanPham.class, QSanPhamChiTiet.class, PathInits.DIRECT2);

    public final StringPath tenMauSac = createString("tenMauSac");

    public QMauSac(String variable) {
        super(MauSac.class, forVariable(variable));
    }

    public QMauSac(Path<? extends MauSac> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMauSac(PathMetadata metadata) {
        super(MauSac.class, metadata);
    }

}

