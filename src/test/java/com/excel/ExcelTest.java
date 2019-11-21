package com.excel;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelTest {

    /**
     * 解析excel成json数据
     */
    @Test
    public void test1() {
        long l = System.currentTimeMillis();
        File file = new File("E:\\usr\\excel\\test.xlsx");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> rows = sheet.rowIterator();
            //行数据记录
            List rowList = new LinkedList<>();
            while (rows.hasNext()) {
                //列数据记录
                List<Map> columnList = new LinkedList<>();
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    //单元格值
                    Map<String, Object> cellMap = new HashMap<>();
                    Cell cell = cells.next();
                    int rowIndex = cell.getRowIndex();
                    int columnIndex = cell.getColumnIndex();
                    //判断是否合并单元格。对于合并单元格，其通过poi解析后只有合并单元格的第一个单元格有数据
                    boolean mergedRegion = isMergedRegion(sheet, rowIndex, columnIndex);
                    int mergeRowNum = 1;
                    int mergeColumNum = 1;
                    if (mergedRegion) {
                        mergeRowNum = getMergeRowNum(cell, sheet);
                        mergeColumNum = getMergeColumNum(cell, sheet);
                    }
                    //设置值
                    Object value = getValue(cell);
                    if (Objects.nonNull(value)) {
                        //设置单元格的值
                        cellMap.put("value", value);
                        //设置单元格的起始行，从0开始
                        cellMap.put("row", rowIndex);
                        //设置单元格的起始列，从0开始
                        cellMap.put("col", columnIndex);
                        //设置单元格的合并行，默认从1开始，1-表示没有合并行。
                        cellMap.put("rowSpan", mergeRowNum);
                        //设置单元格的合并列，默认从1开始，1-表示没有合并列。
                        cellMap.put("colSpan", mergeColumNum);
                        columnList.add(cellMap);
                    }
                }
                rowList.add(columnList);
            }
            long l1 = (System.currentTimeMillis() - l) / 1000;
            System.out.println("时间：" + l1 + " 秒");
//            System.out.println(JSON.toJSON(rowList));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到单元格中的值
     *
     * @param cell
     * @return
     */
    private Object getValue(Cell cell) {
        CellType cellTypeEnum = cell.getCellTypeEnum();
        Object value = null;
        if (Objects.equals(cellTypeEnum, CellType.STRING)) {
            value = cell.getStringCellValue();
        } else if (Objects.equals(cellTypeEnum, CellType.NUMERIC)) {
            value = cell.getNumericCellValue();
        } else if (Objects.equals(cellTypeEnum, CellType.BOOLEAN)) {
            value = cell.getBooleanCellValue();
        } else if (Objects.equals(cellTypeEnum, CellType.FORMULA)) {
            value = cell.getBooleanCellValue();
        }
        return value;
    }

    /**
     * @param cell  当前cell
     * @param sheet 当前sheet
     * @return 获取合并的行数
     */
    private int getMergeRowNum(Cell cell, Sheet sheet) {
        int mergeSize = 1;
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress cellRangeAddress : mergedRegions) {
            if (cellRangeAddress.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
                //获取合并的行数
                mergeSize = cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow() + 1;
                break;
            }
        }
        return mergeSize;
    }


    /**
     * @param cell  当前cell
     * @param sheet 当前sheet
     * @return 获取合并的列数
     */
    private int getMergeColumNum(Cell cell, Sheet sheet) {
        int mergeSize = 1;
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress cellRangeAddress : mergedRegions) {
            if (cellRangeAddress.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
                //获取合并的列数
                mergeSize = cellRangeAddress.getLastColumn() - cellRangeAddress.getFirstColumn() + 1;
                break;
            }
        }
        return mergeSize;
    }

    /**
     * 判断当前单元格是否合并单元格
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    private boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

}
