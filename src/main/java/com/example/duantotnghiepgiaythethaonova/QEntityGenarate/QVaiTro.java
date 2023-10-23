package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
import com.example.duantotnghiepgiaythethaonova.entity.VaiTro;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVaiTro is a Querydsl query type for VaiTro
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVaiTro extends EntityPathBase<VaiTro> {

    private static final long serialVersionUID = -1299847802L;

    public static final QVaiTro vaiTro = new QVaiTro("vaiTro");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath code = createString("code");

    public final NumberPath<Integer> idVaiTro = createNumber("idVaiTro", Integer.class);

    public final ListPath<NguoiDung_VaiTro, QNguoiDung_VaiTro> listNguoiDungVaiTro = this.<NguoiDung_VaiTro, QNguoiDung_VaiTro>createList("listNguoiDungVaiTro", NguoiDung_VaiTro.class, QNguoiDung_VaiTro.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath tenVaiTro = createString("tenVaiTro");

    public QVaiTro(String variable) {
        super(VaiTro.class, forVariable(variable));
    }

    public QVaiTro(Path<? extends VaiTro> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVaiTro(PathMetadata metadata) {
        super(VaiTro.class, metadata);
    }

}

