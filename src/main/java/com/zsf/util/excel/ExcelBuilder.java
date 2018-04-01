package com.zsf.util.excel;

import com.alibaba.fastjson.JSONObject;
import com.zsf.util.DateFormatUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;


public class ExcelBuilder {

    /**
     * 行属性
     */
    private List<ColumnMeta> columnMetaList;

    /**
     * 行数据
     */
    private List rowList;

    public ExcelBuilder(List<ColumnMeta> columnMetaList, List rowList) {
        this.columnMetaList = columnMetaList;
        this.rowList = rowList;
        if (CollectionUtils.isEmpty(columnMetaList)) {
            throw new IllegalArgumentException("列参数无效");
        }

        if (CollectionUtils.isEmpty(rowList)) {
            throw new IllegalArgumentException("行参数无效");
        }


    }

    private HSSFWorkbook createSheet() {
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("订单列表");
        HSSFCellStyle cellStyle = book.createCellStyle();
        sheet.setDefaultColumnWidth(20);// 默认列宽
        createTitle(sheet, cellStyle);
        createRow(sheet, cellStyle);
        return book;
    }

    /**
     * 生成标题
     * @param sheet
     */
    private void createTitle(HSSFSheet sheet, CellStyle cellStyle) {
        HSSFRow row = sheet.createRow(0);
        int size = columnMetaList.size();
        for (int i = 0; i < size; i++) {
            ColumnMeta columnMeta = columnMetaList.get(i);
            HSSFCell cell = row.createCell(i);
            sheet.autoSizeColumn(i);
            createCell(cell, columnMeta);
        }
    }

    /**
     * 生成行
     * @param sheet
     */
    private void createRow(HSSFSheet sheet, CellStyle cellStyle) {
        int rowSize = rowList.size();
        int columnSize = columnMetaList.size();
        for (int i = 0; i < rowSize; i++) {
            Object o = rowList.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < columnSize; j++) {
                sheet.autoSizeColumn(j);
                HSSFCell cell = row.createCell(j);
                createCell(cell, columnMetaList.get(j), o, cellStyle);
            }
        }
    }

    /**
     * 填充单元格
     * @param cell
     * @param columnMeta
     */
    private void createCell(HSSFCell cell, ColumnMeta columnMeta) {
        cell.setCellValue(columnMeta.getTitle());
    }

    private void createCell(HSSFCell cell, ColumnMeta columnMeta, Object row, CellStyle cellStyle) {
        JSONObject jsonObject = (JSONObject) row;
        Object value = jsonObject.get(columnMeta.getKey());
        if (value instanceof Date) {
            cell.setCellValue(DateFormatUtil.getString((Date) value));
        } else {
            if (value != null) {
                String convertResult = columnMeta.convert(value.toString());
                if (StringUtils.isNotBlank(convertResult)) {
                    cellStyle.setWrapText(true);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(new HSSFRichTextString(convertResult));
                } else {
                    cell.setCellValue("-");
                }
            } else {
                cell.setCellValue("-");
            }
        }
    }

    /**
     * 生成数据
     * @param outputStream
     * @throws IOException
     */
    public void builder(OutputStream outputStream) throws IOException {
        HSSFWorkbook sheet = createSheet();
        sheet.write(outputStream);
    }

}
