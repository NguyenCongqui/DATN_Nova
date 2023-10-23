package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNguoiDung is a Querydsl query type for NguoiDung
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNguoiDung extends EntityPathBase<NguoiDung> {

    private static final long serialVersionUID = 1629585197L;

    public static final QNguoiDung nguoiDung = new QNguoiDung("nguoiDung");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath anhNhanVien = createString("anhNhanVien");

    public final EnumPath<AuthenticationProvider> authProvider = createEnum("authProvider", AuthenticationProvider.class);

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final StringPath diaChi = createString("diaChi");

    public final StringPath email = createString("email");

    public final ListPath<GiaoDich, QGiaoDich> giaoDichs = this.<GiaoDich, QGiaoDich>createList("giaoDichs", GiaoDich.class, QGiaoDich.class, PathInits.DIRECT2);

    public final NumberPath<Integer> idNguoiDung = createNumber("idNguoiDung", Integer.class);

    public final ListPath<NguoiDung_VaiTro, QNguoiDung_VaiTro> listNguoiDungVaiTro = this.<NguoiDung_VaiTro, QNguoiDung_VaiTro>createList("listNguoiDungVaiTro", NguoiDung_VaiTro.class, QNguoiDung_VaiTro.class, PathInits.DIRECT2);

    public final StringPath maNguoiDung = createString("maNguoiDung");

    public final StringPath matKhau = createString("matKhau");

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath soDienThoai = createString("soDienThoai");

    public final StringPath tenNguoiDung = createString("tenNguoiDung");

    public final NumberPath<Integer> trangThai = createNumber("trangThai", Integer.class);

    public QNguoiDung(String variable) {
        super(NguoiDung.class, forVariable(variable));
    }

    public QNguoiDung(Path<? extends NguoiDung> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNguoiDung(PathMetadata metadata) {
        super(NguoiDung.class, metadata);
    }

}

