package com.ourway.base.zk.utils;

import com.ourway.base.utils.*;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.zkoss.zul.Filedownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * Created by Administrator on 2017/8/16.
 */
public class ExcelUtils {
    private List<Map<String, Object>> datas;
    private List<String[]> auxHeader;// 多表头信息
    private List<PageControlVO> cols = new ArrayList<PageControlVO>();
    private static Map<String, Short> CELL_COLOR = new HashMap<String, Short>();

    static {
        CELL_COLOR.put("red", new HSSFColor.RED().getIndex());
        CELL_COLOR.put("blue", new HSSFColor.BLUE().getIndex());
        CELL_COLOR.put("yellow", new HSSFColor.YELLOW().getIndex());
        CELL_COLOR.put("green", new HSSFColor.GREEN().getIndex());
        CELL_COLOR.put("black", new HSSFColor.BLACK().getIndex());
    }

    //生成excel
    public XSSFWorkbook doExportXLS(String title) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        //根据数据量来生成excel成不同的sheet
        //单个sheet是65535条记录，取整，放65000条
        XSSFSheet sheet = wb.createSheet("sheet1");
        int rowId = createXLSHeader(sheet, wb,title);
        createXLSEntity(wb, sheet, rowId, datas);
        // sheet.setDefaultColumnWidth((short)40);
        // wb.setRepeatingRowsAndColumns(0, -1, -1, 0, 2);// 设置顶端
        //sheet.setPrintGridlines(true); // 打印网格线
        // sheet.setDefaultRowHeight((short) 30);//默认显示几行
        return wb;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }

    public List<String[]> getAuxHeader() {
        return auxHeader;
    }

    public void setAuxHeader(List<String[]> auxHeader) {
        this.auxHeader = auxHeader;
    }

    public List<PageControlVO> getCols() {
        return cols;
    }

    public void setCols(List<PageControlVO> cols) {
        this.cols = cols;
    }

    //到处excel
    public static void exportXls(List<PageControlVO> cols, List<Map<String, Object>> datas, String title, String fileLabel) {
        ExcelUtils xls = new ExcelUtils();
        xls.setCols(cols);
        xls.setDatas(datas);
        String[] tl = new String[]{title};
        List<String[]> auxheader = new ArrayList<String[]>(1);
        auxheader.add(tl);
        xls.setAuxHeader(auxheader);
        FileOutputStream out = null;
        try {
            XSSFWorkbook wb = xls.doExportXLS(title);
            String folder = ZKConstants.SYSTEM_FILE_PATH + "export" + File.separator;
            File _folder = new File(folder);
            if(!_folder.exists())
                _folder.mkdirs();
            String fileName = System.currentTimeMillis() + ".xlsx";
            File file = new File(folder + fileName);
            out = new FileOutputStream(file);
            wb.write(out);
            out.close();
            FileInputStream inputStream = new FileInputStream(file);
            Filedownload.save(inputStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", fileLabel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createHSSFWorkBooks() {
        try {
//            Workbook wb = new HSSFWorkbook(); //这里新建了一个exccel 2003的文件，所以
            Workbook wb = new XSSFWorkbook();  //这里是一个excel2007的文件，相应的输出流后缀应该是xlsx
            XSSFSheet sheet1 = (XSSFSheet) wb.createSheet("sheet1");
            FileOutputStream fos = new FileOutputStream("workbook.xls");
            wb.write(fos);
            fos.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public int createXLSHeader(XSSFSheet sheet, XSSFWorkbook wb,String title){
        int rowId = 0; // 起始行信息
        int _startRow = 0;
        int _endRow = 0;
        int _startCol = 0;
        int _endCol = cols.size()-1;
        XSSFRow row = sheet.createRow((short) rowId); // 创建这一行信息
        XSSFCell cell = row.createCell((short) _startCol);
        XSSFRichTextString str = new XSSFRichTextString(title);
        cell.setCellValue(str);
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
        mergeRowCell(sheet, _startRow, (short) _startCol,
                _endRow, (short) _endCol);
        rowId++;
        for (int i = 0; i < cols.size(); i++) {
            cell = getMyCell(sheet, (short) rowId, (short) i);
            PageControlVO vo = cols.get(i);
            setStringValue(cell, I18nUtil.getLabelContent(vo.getKjLabelid()));
        }
        return  rowId + 1;
    }

    //设置表头和第一行数据
    public int createXLSHeader(XSSFSheet sheet, XSSFWorkbook wb) {
        // 设置表头信息，包括多重表头信息。
        int rowId = 0; // 起始行信息

        if (null != this.auxHeader && this.auxHeader.size() > 0) {
            Integer[][] _row = new Integer[this.auxHeader.size()][cols.size()];
            rowId = this.auxHeader.size();
            for (int i = 0; i < this.auxHeader.size(); i++) {
                String[] auxs = this.auxHeader.get(i);
                XSSFRow row = sheet.createRow((short) i); // 创建这一行信息
                int _startRow = 0;
                int _endRow = 0;
                int _startCol = 0;
                int _endCol = 0;

                for (int j = 0; j < auxs.length; j++) {// 多表头设置信息 label#row#col
                    int _bgCol = 0; // 默认从第0列开始，存在多行合并，可能默认起始的列不定
                    // 计算从第几列开始
                    for (int k = 0; k < cols.size(); k++) {
                        if (null != _row[i][k] && _row[i][k] == 1)
                            _bgCol++;
                        if (k + 1 < cols.size()
                                && (null == _row[i][k + 1] || _row[i][k + 1] == 0))
                            break;
                    }
                    String[] _auxs = auxs[j].split("#");
                    _startCol = _bgCol + 0;
                    _startRow = i; // 起始行
                    _endRow = i + new Integer(_auxs[1]) - 1;
                    _endCol = _startCol + new Integer(_auxs[2]) - 1;
                    XSSFCell cell = row.createCell((short) _startCol);
                    XSSFRichTextString str = new XSSFRichTextString(_auxs[0]);
                    cell.setCellValue(str);
                    mergeRowCell(sheet, _row, _startRow, (short) _startCol,
                            _endRow, (short) _endCol);
                }
            }
        }
        for (int i = 0; i < cols.size(); i++) {
            XSSFCell cell = getMyCell(sheet, (short) rowId, (short) i);
            PageControlVO vo = cols.get(i);
            setStringValue(cell, I18nUtil.getLabelContent(vo.getKjLabelid()));
        }
        return rowId + 1;
    }

    //创建单元格
    private XSSFCell getMyCell(XSSFSheet sheet, int rowNum, int colNum) {
        XSSFRow row = sheet.getRow(rowNum);
        if (null == row) {
            row = sheet.createRow(rowNum);
        }
        XSSFCell cell = row.getCell(colNum);
        if (null == cell) {
            cell = row.createCell(colNum);
        }
        return cell;
    }


    //合并单元格
    private void mergeRowCell(XSSFSheet sheet, Integer[][] _row, int _startRow,
                              short _startCol, int _endRow, short _endCol) {
        // 合并
        sheet.addMergedRegion(new CellRangeAddress(_startRow, _startCol, _endRow,
                _endCol));
        for (int i = _startRow; i <= _endRow; i++) {
            for (int j = _startCol; j <= _endCol; j++) {
                _row[i][j] = 1;
            }
        }
    } //合并单元格
    private void mergeRowCell(XSSFSheet sheet, int _startRow,
                              short _startCol, int _endRow, short _endCol) {
        // 合并
        sheet.addMergedRegion(new CellRangeAddress(_startRow, _startCol, _endRow,
                _endCol));

    }

    public void createXLSEntity(XSSFWorkbook wb, XSSFSheet sheet, int rowId, List<Map<String, Object>> _datas) {
        String labelKey = "";
        Object _obj = null;
        int j = 0;
        for (int i = 0; i < _datas.size(); i++) {
            Map<String, Object> bean = _datas.get(i);
            j = 0;
            for (PageControlVO vo : cols) {
                if (TextUtils.isEmpty(vo.getKjAttributeDisplay()))
                    labelKey = vo.getKjAttribute();
                else
                    labelKey = vo.getKjAttributeDisplay();
                _obj = doGetObjByLabel(labelKey, bean);
                this.doSetCell(wb, sheet, (rowId + i),
                        (short) j, _obj, "");
                j++;
            }
        }
    }

    public void doSetCell(XSSFWorkbook wb, XSSFSheet sheet, int rowNum,
                          short colNum, Object value, String color) {
        XSSFCell cell = getMyCell(sheet, rowNum, colNum);
        if (!TextUtils.isEmpty(color)) {
//            XSSFCellStyle cellStyle = wb.createCellStyle();
//            cellStyle.setFillForegroundColor();
//            cellStyle.setFillBackgroundColor(CELL_COLOR.get(color));
//            cellStyle.setFillPattern(CellStyle.SPARSE_DOTS);
//            cell.setCellStyle(cellStyle);
        }
        if (value != null) {
            if (value instanceof Number) {
                setDoubleValue(cell, Double.valueOf(value.toString()));
            } else if (value instanceof String) {
                setStringValue(cell, value.toString());
            } else if (value instanceof Date) {
                XSSFCellStyle cellStyle = wb.createCellStyle();
                setDateValue(cell, cellStyle, (Date) value);
            }
        }
    }

    private Object doGetObjByLabel(String labelKey, Map<String, Object> data) {
        Object _obj = null;
        if (labelKey.contains(".")) {
            Map<String, Object> tmpData = data;
            String[] _labelKeys = labelKey.split("\\.");
            for (int index = 0; index < _labelKeys.length; index++) {
                if (index != _labelKeys.length - 1) {
                    tmpData = (Map<String, Object>) tmpData.get(_labelKeys[index]);
                    if (TextUtils.isEmpty(tmpData))
                        return null;
                } else
                    _obj = tmpData.get(_labelKeys[index]);
            }
        } else
            _obj = data.get(labelKey);
        return _obj;

    }

    public void setDoubleValue(XSSFCell cell, Double value) {
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
    }

    public void setDateValue(XSSFCell cell, XSSFCellStyle cellStyle, Date value) {
        // 指定日期显示格式
//        cellStyle.setDataFormat(XSSFDataFormat.);
        // 设定单元格日期显示格式
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }

    //设置值
    public void setStringValue(XSSFCell cell, String value) {
        // 单元格汉字编码转换
        cell.setCellType(Cell.CELL_TYPE_STRING);
        XSSFRichTextString str = new XSSFRichTextString(value);
        cell.setCellValue(str);
    }
//    public static void insertImg(WritableSheet dataSheet, int col, int row, int width,
//                                 int height, File imgFile){
//        WritableImage img = new WritableImage(col, row, width, height, imgFile);
//        dataSheet.addImage(img);
//    }
}
