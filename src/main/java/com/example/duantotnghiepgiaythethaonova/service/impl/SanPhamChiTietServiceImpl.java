package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamProductManageDTO;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.repository.SanPhamChiTietRepository;
import com.example.duantotnghiepgiaythethaonova.service.SanPhamChiTietSearchRepository;
import com.example.duantotnghiepgiaythethaonova.service.SanPhamChiTietService;
import com.example.duantotnghiepgiaythethaonova.service.StorageService;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final SanPhamChiTietSearchRepository sanPhamChiTietSearchRepository;

    @Autowired
    private StorageService storageService;

    @Override
    public List<ChiTietSanPham> getLstChiTietSanPhamExist() {
        return sanPhamChiTietRepository.getLstChiTietSanPhamExist();
    }

    @Override
    public Optional<ChiTietSanPham> findById(Integer id) {
        return sanPhamChiTietRepository.findById(id);
    }

    @Override
    public List<ChiTietSanPham> getLstChiTietSanPhamBySanPhamId(Integer id) {
        return sanPhamChiTietRepository.getLstChiTietSanPhamBySanPhamId(id);
    }

    @Override
    public Page<ChiTietSanPham> searchProductDetailExist(SPAndSPCTSearchDto data, Pageable pageable) {
        return sanPhamChiTietSearchRepository.searchProductDetailExist(data, pageable);
    }

    @Override
    public <S extends ChiTietSanPham> S save(S entity) {
        entity.setDaXoa(false);
       String maSPCT = UUID.randomUUID().toString();
        entity.setMaCTSP(maSPCT);
        return sanPhamChiTietRepository.save(entity);
    }


    @Override
    public void delete(ChiTietSanPham entity) {
        entity.setDaXoa(true);
        sanPhamChiTietRepository.save(entity);
    }

    @Override
    public List<ChiTietSanPham> getLstChiTietSanPhamAddImg(Integer id) {
        return sanPhamChiTietRepository.getLstChiTietSanPhamAddImg(id);
    }

    @Override
    public List<Integer> getLstMauSacBySanPhamId(Integer sanPhamId) {
        return sanPhamChiTietRepository.getLstMauSacBySanPhamId(sanPhamId);
    }

    @Override
    public Optional<ChiTietSanPham> getChiTietSanPhamByMauSacSizeSanPhamId(Integer sanPhamId, Integer mauSacId,
                                                                           Integer kichCoId) {
        return sanPhamChiTietRepository.getChiTietSanPhamByMauSacSizeSanPhamId(sanPhamId, mauSacId, kichCoId);
    }

    @Override
    public int selectCountChiTietSanPhamDuplicate(Integer mauSacId, Integer kichCoId, Integer sanPhamId) {
        return sanPhamChiTietRepository.selectCountChiTietSanPhamDuplicate(mauSacId, kichCoId, sanPhamId);
    }

    @Override
    public int getCountChiTietSanPhamExistBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId) {
        return sanPhamChiTietRepository.getCountChiTietSanPhamExistBySanPhamIdAndMauSacId(sanPhamId, mauSacId);
    }

    @Override
    public int getCountChiTietSanPhamExistBySanPhamId(Integer sanPhamId) {
        return sanPhamChiTietRepository.getCountChiTietSanPhamExistBySanPhamId(sanPhamId);
    }

    @Override
    public int getSumSoLuongBySanPhamId(Integer id) {
        return sanPhamChiTietRepository.getSumSoLuongBySanPhamId(id);
    }

    @Override
    public BigDecimal getTienBan(Integer id) {

            return sanPhamChiTietRepository.getTienBan(id);
    }

    @Override
    public Optional<ChiTietSanPham> selectChiTietSanPhamDuplicate(Integer mauSacId, Integer kichCoId, Integer sanPhamId) {
        return sanPhamChiTietRepository.selectChiTietSanPhamDuplicate(mauSacId, kichCoId, sanPhamId);
    }

    @Override
    public void WritingToExcelProduct(List<SanPhamProductManageDTO> lstDto) throws IOException {
        if (!lstDto.isEmpty()) {
            int row = 0;
            int indexItemOfSheet = 1;
            Workbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Sản phẩm");

            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 5000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(3, 8000);
            sheet.setColumnWidth(4, 8000);
//            sheet.setColumnWidth(5, 8000);

            sheet.setDefaultRowHeightInPoints(sheet.getColumnWidthInPixels(1));

            // header
            Row header = sheet.createRow(0);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.MEDIUM);
            headerStyle.setBorderTop(BorderStyle.MEDIUM);
            headerStyle.setBorderLeft(BorderStyle.MEDIUM);
            headerStyle.setBorderRight(BorderStyle.MEDIUM);

            XSSFFont headerFont = ((XSSFWorkbook) workbook).createFont();
            headerFont.setFontName("Arial");
            headerFont.setFontHeightInPoints((short) 16);
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("#");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(1);
            headerCell.setCellValue("Ảnh chính");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(2);
            headerCell.setCellValue("Tên sản phẩm");
            headerCell.setCellStyle(headerStyle);


            headerCell = header.createCell(3);
            headerCell.setCellValue("Mã sản phẩm");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(4);
            headerCell.setCellValue("Tổng số lượng");
            headerCell.setCellStyle(headerStyle);

//            headerCell = header.createCell(5);
//            headerCell.setCellValue("id");
//            headerCell.setCellStyle(headerStyle);
            row++;
            for (SanPhamProductManageDTO dto : lstDto) {
                // body
                int countRowIn1Product = 0;
                Row body = sheet.createRow(row);

                CellStyle bodyStyle = workbook.createCellStyle();

                XSSFFont bodyFont = ((XSSFWorkbook) workbook).createFont();
                bodyFont.setFontName("Arial");
                bodyFont.setFontHeightInPoints((short) 12);
                bodyStyle.setFont(bodyFont);

                Cell bodyCell = body.createCell(0);
                bodyCell.setCellValue(indexItemOfSheet);
                bodyCell.setCellStyle(bodyStyle);


                for (String haStr : dto.getAnhChinhs()) {
                    Resource file = storageService.loadAsResource(haStr);
                    FileInputStream is = new FileInputStream(file.getFile());
                    byte[] bytes = IOUtils.toByteArray(is);
                    int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
                    is.close();
                    XSSFCreationHelper helper = (XSSFCreationHelper) workbook.getCreationHelper();
                    // Create the drawing patriarch. This is the top level container for all shapes.
                    Drawing drawing = sheet.createDrawingPatriarch();
                    // add a picture shape
                    XSSFClientAnchor anchor = helper.createClientAnchor();
                    // set top-left corner of the picture,
                    // subsequent call of Picture#resize() will operate relative to it
                    anchor.setCol1(1);
                    anchor.setCol2(2);
                    anchor.setRow1(row);
                    anchor.setRow2(row + 1);
                    Picture pict = drawing.createPicture(anchor, pictureIdx);
                    // auto-size picture relative to its top-left corner
                    sheet.autoSizeColumn(row);
                    countRowIn1Product++;
                    row++;
                }

                bodyCell = body.createCell(2);
                bodyCell.setCellValue(dto.getSanPham().getTenSanPham());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = body.createCell(3);
                bodyCell.setCellValue(dto.getSanPham().getMaSanPham());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = body.createCell(4);
                bodyCell.setCellValue(dto.getTongSoLuong());
                bodyCell.setCellStyle(bodyStyle);

//                bodyCell = body.createCell(5);
//                bodyCell.setCellValue(dto.getChiTietSanPham().getIdCTSP());
//                bodyCell.setCellStyle(bodyStyle);
                indexItemOfSheet++;
            }
            // finaly
            File currDir = new File("D:\\Excel\\ImportExcel/qlsp.xlsx");

            FileOutputStream outputStream = new FileOutputStream(currDir);
            workbook.write(outputStream);
            workbook.close();
        }
    }
}
