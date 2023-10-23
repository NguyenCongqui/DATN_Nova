package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.AuthenticationProvider;
import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import com.example.duantotnghiepgiaythethaonova.entity.KhachHang;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKhachHang is a Querydsl query type for KhachHang
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKhachHang extends EntityPathBase<KhachHang> {

    private static final long serialVersionUID = -709753496L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKhachHang khachHang = new QKhachHang("khachHang");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<AuthenticationProvider> authProvider = createEnum("authProvider", AuthenticationProvider.class);

    public final QDiaChi diaChi;

    public final StringPath email = createString("email");

    public final StringPath hoTen = createString("hoTen");

    public final NumberPath<Integer> idKhachHang = createNumber("idKhachHang", Integer.class);

    public final ListPath<DiaChi, QDiaChi> listDiaChi = this.<DiaChi, QDiaChi>createList("listDiaChi", DiaChi.class, QDiaChi.class, PathInits.DIRECT2);

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

    public final NumberPath<Integer> soLanMua = createNumber("soLanMua", Integer.class);

    public final NumberPath<Integer> trangThai = createNumber("trangThai", Integer.class);

    public QKhachHang(String variable) {
        this(KhachHang.class, forVariable(variable), INITS);
    }

    public QKhachHang(Path<? extends KhachHang> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QKhachHang(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QKhachHang(PathMetadata metadata, PathInits inits) {
        this(KhachHang.class, metadata, inits);
    }

    public QKhachHang(Class<? extends KhachHang> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diaChi = inits.isInitialized("diaChi") ? new QDiaChi(forProperty("diaChi"), inits.get("diaChi")) : null;
    }

}

