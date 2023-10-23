package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import com.example.duantotnghiepgiaythethaonova.entity.ThuongHieu;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QThuongHieu is a Querydsl query type for ThuongHieu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThuongHieu extends EntityPathBase<ThuongHieu> {

    private static final long serialVersionUID = 460089003L;

    public static final QThuongHieu thuongHieu = new QThuongHieu("thuongHieu");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final NumberPath<Integer> idThuongHieu = createNumber("idThuongHieu", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final ListPath<SanPham, QSanPham> sanPhams = this.<SanPham, QSanPham>createList("sanPhams", SanPham.class, QSanPham.class, PathInits.DIRECT2);

    public final StringPath tenThuongHieu = createString("tenThuongHieu");

    public QThuongHieu(String variable) {
        super(ThuongHieu.class, forVariable(variable));
    }

    public QThuongHieu(Path<? extends ThuongHieu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThuongHieu(PathMetadata metadata) {
        super(ThuongHieu.class, metadata);
    }

}

