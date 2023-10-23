package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.HinhAnh;
import com.example.duantotnghiepgiaythethaonova.entity.QMauSac;
import com.example.duantotnghiepgiaythethaonova.entity.QSanPham;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHinhAnh is a Querydsl query type for HinhAnh
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHinhAnh extends EntityPathBase<HinhAnh> {

    private static final long serialVersionUID = -946526227L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHinhAnh hinhAnh = new QHinhAnh("hinhAnh");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath coHienThi = createBoolean("coHienThi");

    public final NumberPath<Integer> idHinhAnh = createNumber("idHinhAnh", Integer.class);

    public final BooleanPath laAnhChinh = createBoolean("laAnhChinh");

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

    public final StringPath tenAnh = createString("tenAnh");

    public QHinhAnh(String variable) {
        this(HinhAnh.class, forVariable(variable), INITS);
    }

    public QHinhAnh(Path<? extends HinhAnh> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHinhAnh(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHinhAnh(PathMetadata metadata, PathInits inits) {
        this(HinhAnh.class, metadata, inits);
    }

    public QHinhAnh(Class<? extends HinhAnh> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mauSac = inits.isInitialized("mauSac") ? new QMauSac(forProperty("mauSac")) : null;
        this.sanPham = inits.isInitialized("sanPham") ? new QSanPham(forProperty("sanPham"), inits.get("sanPham")) : null;
    }

}

