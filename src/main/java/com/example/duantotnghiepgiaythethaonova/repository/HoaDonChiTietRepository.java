package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.GioHangChiTiet;
import com.example.duantotnghiepgiaythethaonova.entity.HoaDonChiTiet;
import com.example.duantotnghiepgiaythethaonova.response.ThongKe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {

    @Query(value = "SELECT count(*) FROM HoaDonCT WHERE idHoaDon = :id AND daXoa= 0", nativeQuery = true)
    Integer countByHoaDonId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM HoaDonCT WHERE idHoaDon = :id AND daXoa= 0", nativeQuery = true)
    Page<HoaDonChiTiet> findAllByHoaDonId(@Param("id") Integer id, Pageable pageable);

    @Query(value = "update HoaDonCT set daXoa = 1 where idHoaDon = ? and idHoaDonCT = ?", nativeQuery = true)
    void xoaHoaDonCT(Integer HoaDonChiTietID, Integer hoaDonId);

    @Query(value = "select * from HoaDonCT where idSanPhamCT = ? and idHoaDon = ? and daXoa = 0", nativeQuery = true)
    Optional<HoaDonChiTiet> findBySanPhamChiTietAndHoaDon(Integer sanPhamCTId, Integer hoaDonId);

    @Query(value = "select * from HoaDonCT where idHoaDon = ? and daXoa = 0", nativeQuery = true)
    List<HoaDonChiTiet> findByHoaDonIdAndDaXoa(Integer id);

    @Query(value = "SELECT * FROM dbo.HoaDonCT WHERE IdHoaDonCT = ? AND DaXoa = 0", nativeQuery = true)
    List<HoaDonChiTiet> findbyHoaDonIdAndDaXoa(Integer id);

    @Query(value = "select * from HoaDonCT where IdSanPhamCT = ?", nativeQuery = true)
    List<HoaDonChiTiet> test(Integer IdSanPhamCT);

    @Query(value = "select top 5 sanpham.IdSanPham, sanpham.MaSanPham,sanpham.TenSanPham,SUM(hdct.SoLuong) as 'SoLuong', SUM(hdct.SoLuong * hdct.DonGia) as 'TongDoanhThu'\n" +
            "from SanPhamCT sp\n" +
            "join SanPham sanpham on sp.IdSanPham = sanpham.IdSanPham\n" +
            "join HinhAnh ha on ha.IdSanPham = sanpham.IdSanPham\n" +
            "join HoaDonCT hdct on sp.IdSanPhamCT = hdct.IdSanPhamCT\n" +
            "join HoaDon hd on hd.IdHoaDon = hdct.IdHoaDon\n" +
            "join TrangThai tt on tt.IdTrangThai = hd.IdTrangThai\n" +
            "where tt.IdTrangThai = 4 or tt.IdTrangThai = 7\n" +
            "Group by sanpham.TenSanPham,sanpham.MaSanPham, sanpham.IdSanPham\n" +
            "order by SUM(hdct.SoLuong) desc",nativeQuery = true)
    List<ThongKe> thongKe();

}
