package com.wlgdo.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel简单处理
 * 
 * @author dk
 *
 */
public class ExcelUtils {

	public static String[][] getExcelData(File file, int columnNumber) throws Exception {
		String[][] arr = null;
		Workbook wb = null;

		try {
			wb = new HSSFWorkbook(new FileInputStream(file));// 2003
		} catch (Exception e) {
			wb = new XSSFWorkbook(new FileInputStream(file));// 2007
		}

		int sheetNum = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			Sheet childSheet = wb.getSheetAt(i);
			int rowNum = childSheet.getPhysicalNumberOfRows();
			for (int j = 0; j < rowNum; j++) {
				Row row = childSheet.getRow(j);
				int cellNum = columnNumber + 1;
				if (j == 0) {
					arr = new String[rowNum][cellNum];
				}
				for (int k = 0; k < cellNum; k++) {
					Cell cell = row.getCell(k);
					arr[j][k] = parseExcel(cell);
				}
			}
		}

		wb.close();
		return arr;
	}

	private static DecimalFormat decimalFmt = new DecimalFormat("#.######");

	private static String parseExcel(Cell cell) {
		if (cell == null) {
			return "";
		}
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、 时间格式
				SimpleDateFormat sdf = null;
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else {
				double va = cell.getNumericCellValue();
				// 去掉数值类型后面的".0"
				result = va == (int) va ? String.valueOf((int) va) : decimalFmt.format(va);
			}
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			try {
				result = String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				result = String.valueOf(cell.getRichStringCellValue());
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			result = cell.getRichStringCellValue().toString();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
			break;
		default:
			result = "";
			break;
		}

		return result;
	}

	public static void exportExcels(String fileName, String[][] excles, OutputStream os) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		int rows = 0;
		for (String[] cells : excles) {
			HSSFRow row = sheet.createRow(rows++);
			int cols = 0;
			for (String cellValue : cells) {
				HSSFCell cell = row.createCell(cols++);
				cell.setCellValue(cellValue);
			}
		}
		wb.write(os);
		os.flush();
		os.close();
		wb.close();
	}
	
	public static void exportExcels(ArrayList<ArrayList<String>> excles, OutputStream os) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		int rows = 0;
		for (ArrayList<String> cells : excles) {
			HSSFRow row = sheet.createRow(rows++);
			int cols = 0;
			for (String cellValue : cells) {
				HSSFCell cell = row.createCell(cols++);
				cell.setCellValue(cellValue);
			}
		}
		wb.write(os);
		os.flush();
		os.close();
		wb.close();
	}
}