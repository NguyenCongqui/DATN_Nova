package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo,Integer> {
    @Query(value = "SELECT * FROM KichCo c WHERE c.DaXoa = 0 ORDER BY c.IdKichCo DESC", nativeQuery = true)
    List<KichCo> selectAllKichCoExist();

    @Query(value = "SELECT * FROM KichCo c WHERE c.DaXoa = 0 ORDER BY c.IdKichCo DESC", nativeQuery = true)
    Page<KichCo> selectAllKichCoExist(Pageable pageable);

    @Query(value = "SELECT * FROM KichCo c WHERE c.DaXoa = 0 AND c.TenKichCo like %:TenKichCo% ORDER BY c.IdKichCo DESC", nativeQuery = true)
    Page<KichCo> getKichCoExistByName(@Param("TenKichCo") String tenKichCo, Pageable pageable);

    @Query(value = "SELECT  c.* FROM KichCo c join SanPhamCT s1 on s1.IdKichCo = c.IdKichCo WHERE c.DaXoa = 0 and s1.IdSanPham = :IdSanPham group by c.IdKichCo , c.MaKichCo , c.TenKichCo, c.NgayCapNhat,c.NguoiCapNhat,c.NgayTao,c.NguoiTao,c.DaXoa", nativeQuery = true)
    List<KichCo> selectAllKichCoBySanPhamId(@Param("IdSanPham") Integer sanPhamId);

    @Query(value = "SELECT pc.TenKichCo AS ten_kich_co FROM SanPhamCT pd JOIN KichCo pc" +
            " ON pd.IdKichCo = pc.IdKichCo WHERE pd.IdSanPham = ?" +
            " GROUP BY pc.TenKichCo, pd.IdKichCo",nativeQuery = true)
    List<Integer> getKichCoBySanPhamId(Integer idSanPham);

    @Query(value = "select * from KichCo where TenKichCo = ?", nativeQuery = true)
    Optional<KichCo> findByTenKichCo(String TenKichCo);
}
