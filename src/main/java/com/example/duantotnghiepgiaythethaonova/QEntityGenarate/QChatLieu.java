package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import com.example.duantotnghiepgiaythethaonova.entity.QSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatLieu is a Querydsl query type for ChatLieu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatLieu extends EntityPathBase<ChatLieu> {

    private static final long serialVersionUID = -649936520L;

    public static final QChatLieu chatLieu = new QChatLieu("chatLieu");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final NumberPath<Integer> idChatLieu = createNumber("idChatLieu", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final ListPath<SanPham, QSanPham> sanPhams = this.<SanPham, QSanPham>createList("sanPhams", SanPham.class, QSanPham.class, PathInits.DIRECT2);

    public final StringPath tenChatLieu = createString("tenChatLieu");

    public QChatLieu(String variable) {
        super(ChatLieu.class, forVariable(variable));
    }

    public QChatLieu(Path<? extends ChatLieu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatLieu(PathMetadata metadata) {
        super(ChatLieu.class, metadata);
    }

}

