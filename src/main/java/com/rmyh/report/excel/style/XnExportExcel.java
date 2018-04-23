package com.rmyh.report.excel.style;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.*;
import java.util.Properties;

import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import com.rmyh.report.excel.bean.XnBean;

public class XnExportExcel {
	
public static String excelNamePre = "Perf"; 	
	
	public Properties initProp() throws IOException {
		Properties prop = new Properties();
		String property = System.getProperty("user.dir");
		File file = new File(property+"/excel.properties");
		InputStream input = new FileInputStream(file);
		//    InputStreamReader inputR = new InputStreamReader(input,"utf-8");
		prop.load(input);
		return prop;
	}
	
	public void Export (String startTime, String endTime, List<List <XnBean>> list) throws IOException {
		
//		Properties prop = initProp();
		
		HSSFWorkbook wb = new HSSFWorkbook();
		
		for (int i=0;i<list.size();i++) {
			makeSheet(wb,list.get(i),i);
		}
		
		try {
			// 默认导出到盘下
			String excelName = "" + startTime + endTime;
			String property = System.getProperty("user.dir");
			FileOutputStream out = new FileOutputStream(
					property+"/" +excelNamePre+ excelName + ".xls");
			// 保存excel报表
			wb.write(out);
			// 关闭文件流
			out.close();
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void makeSheet (HSSFWorkbook wb, List<XnBean> list,int sheetId) {
		
		String sheetName = list.get(0).getGroupName();
	
		// 声明一个单子并命名
		HSSFSheet sheet = wb.createSheet(sheetName);
		makeFixedPart(wb,sheet,sheetId);
		makeDataPart(wb,sheet,sheetId,list);
		mergeColumn(wb,sheetId,list,sheet);
	}
	
	public void mergeColumn(HSSFWorkbook wb, int sheetId, List<XnBean> list,HSSFSheet sheet) {
		
		switch (sheetId) {
		case 4:
			//合并第一列相同的数据
			int rowgap4 = 3;
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap4;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getIpAddress().equals(list.get(endRow - rowgap4).getIpAddress())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column1RangeAddress = new CellRangeAddress(startRow, endRow-1, 0, 0);
							sheet.addMergedRegion(column1RangeAddress);
							i = endRow - rowgap4;
							break;
						} else {
							i = endRow - rowgap4;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap4) {
							CellRangeAddress column1RangeAddress = new CellRangeAddress(startRow, endRow, 0, 0);
							sheet.addMergedRegion(column1RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			//合并第二列相同的数据
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap4;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getJkInstance().equals(list.get(endRow - rowgap4).getJkInstance())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column2RangeAddress = new CellRangeAddress(startRow, endRow-1, 1, 1);
							sheet.addMergedRegion(column2RangeAddress);
							i = endRow - rowgap4;
							break;
						} else {
							i = endRow - rowgap4;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap4) {
							CellRangeAddress column2RangeAddress = new CellRangeAddress(startRow, endRow, 1, 1);
							sheet.addMergedRegion(column2RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			//合并第三列相同的数据
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap4;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getJkObject().equals(list.get(endRow - rowgap4).getJkObject())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow-1, 2, 2);
							sheet.addMergedRegion(column3RangeAddress);
							i = endRow - rowgap4;
							break;
						} else {
							i = endRow - rowgap4;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap4) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow, 2, 2);
							sheet.addMergedRegion(column3RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			break;
		case 5:
			break;
		case 6:
			//合并第一列相同的数据
		 	int rowgap6 = 3;
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap6;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getIpAddress().equals(list.get(endRow - rowgap6).getIpAddress())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column1RangeAddress = new CellRangeAddress(startRow, endRow-1, 0, 0);
							sheet.addMergedRegion(column1RangeAddress);
							i = endRow - rowgap6;
							break;
						} else {
							i = endRow - rowgap6;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap6) {
							CellRangeAddress column1RangeAddress = new CellRangeAddress(startRow, endRow, 0, 0);
							sheet.addMergedRegion(column1RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			//合并第二列相同的数据
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap6;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getDOMAIN().equals(list.get(endRow - rowgap6).getDOMAIN())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column2RangeAddress = new CellRangeAddress(startRow, endRow-1, 1, 1);
							sheet.addMergedRegion(column2RangeAddress);
							i = endRow - rowgap6;
							break;
						} else {
							i = endRow - rowgap6;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap6) {
							CellRangeAddress column2RangeAddress = new CellRangeAddress(startRow, endRow, 1, 1);
							sheet.addMergedRegion(column2RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			//合并第三列相同的数据
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap6;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getSERVER().equals(list.get(endRow - rowgap6).getSERVER())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow-1, 2, 2);
							sheet.addMergedRegion(column3RangeAddress);
							i = endRow - rowgap6;
							break;
						} else {
							i = endRow - rowgap6;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap6) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow, 2, 2);
							sheet.addMergedRegion(column3RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			break;
		default:
			//合并第一列相同的数据
			int rowgap = 3;
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getSbName().equals(list.get(endRow - rowgap).getSbName())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column1RangeAddress = new CellRangeAddress(startRow, endRow-1, 0, 0);
							sheet.addMergedRegion(column1RangeAddress);
							i = endRow - rowgap;
							break;
						} else {
							i = endRow - rowgap;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap) {
							CellRangeAddress column1RangeAddress = new CellRangeAddress(startRow, endRow, 0, 0);
							sheet.addMergedRegion(column1RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			//合并第二列相同的数据
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getIpAddress().equals(list.get(endRow - rowgap).getIpAddress())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column2RangeAddress = new CellRangeAddress(startRow, endRow-1, 1, 1);
							sheet.addMergedRegion(column2RangeAddress);
							i = endRow - rowgap;
							break;
						} else {
							i = endRow - rowgap;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap) {
							CellRangeAddress column2RangeAddress = new CellRangeAddress(startRow, endRow, 1, 1);
							sheet.addMergedRegion(column2RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			//合并第三列相同的数据
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getJkObject().equals(list.get(endRow - rowgap).getJkObject())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow-1, 2, 2);
							sheet.addMergedRegion(column3RangeAddress);
							i = endRow - rowgap;
							break;
						} else {
							i = endRow - rowgap;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow, 2, 2);
							sheet.addMergedRegion(column3RangeAddress);
							i = list.size();
							break;
						} else {
						endRow++;}
					}
				}
			}
			break;
		}
		
	}

	@SuppressWarnings("deprecation")
	public void makeFixedPart (HSSFWorkbook wb,HSSFSheet sheet,int sheetId) {
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((short) 33);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		// 创建第一行（也可以称为表头）
		HSSFRow row = sheet.createRow(0);
		// 创建第一行第一列
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("性能数据统一报表");
		// 合并第一行的列
		switch (sheetId) {
		case 5:
			CellRangeAddress cellRangeAddress5 = new CellRangeAddress(0, 0, 0, 6);
			sheet.addMergedRegion(cellRangeAddress5);
		case 6:
			CellRangeAddress cellRangeAddress6 = new CellRangeAddress(0, 0, 0, 8);
			sheet.addMergedRegion(cellRangeAddress6);
		default:
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 9);
			sheet.addMergedRegion(cellRangeAddress);
		}
		
		
		// 第一行居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 第一行字体格式(宋体,20,加粗)
		HSSFFont headFont = wb.createFont();
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 30);
		headFont.setTypeOffset(FontFormatting.SS_SUPER);
		headFont.setBold(true);
		style.setFont(headFont);
		// 第一行背景颜色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
		cell.setCellStyle(style);

		// 创建第二行
		HSSFRow row1 = sheet.createRow(1);
		// 创建第二行第一列
		HSSFCell cell10 = row1.createCell(0);
		HSSFCellStyle style10 = wb.createCellStyle();
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tableTime = "报表类型:日报 时间:" + df.format(day.getTime());
		cell10.setCellValue(tableTime);
		sheet.setColumnWidth(0, cell10.getStringCellValue().toString().length() * 1024);
		HSSFFont headFont10 = wb.createFont();
		headFont10.setFontName("宋体");
		headFont10.setFontHeightInPoints((short) 11);
		style10.setFont(headFont10);
		cell10.setCellStyle(style10);
	}
	public void makeDataPart (HSSFWorkbook wb,HSSFSheet sheet,int sheetId,List<XnBean> list) {
		// 创建第三行
		HSSFRow row2 = sheet.createRow(2);
		// 创建第三行样式
		HSSFCellStyle style20 = wb.createCellStyle();
		HSSFFont headFont20 = wb.createFont();
		headFont20.setFontName("宋体");
		headFont20.setFontHeightInPoints((short) 11);
		style20.setFont(headFont20);
		style20.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style20.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
		
		switch (sheetId) {
		case 4:
			// 创建第三行第一列
			HSSFCell cell420 = row2.createCell(0);
			cell420.setCellValue("IP地址");
			sheet.setColumnWidth(0, cell420.getStringCellValue().toString().length() * 1024);
			cell420.setCellStyle(style20);
			// 创建第三行第二列
			HSSFCell cell421 = row2.createCell(1);
			cell421.setCellValue("监控实例");
			sheet.setColumnWidth(1, cell421.getStringCellValue().toString().length() * 1024);
			cell421.setCellStyle(style20);
			// 创建第三行第三列
			HSSFCell cell422 = row2.createCell(2);
			cell422.setCellValue("监控对象");
			sheet.setColumnWidth(2, cell422.getStringCellValue().toString().length() * 1024);
			cell422.setCellStyle(style20);
			// 创建第三行第四列
			HSSFCell cell423 = row2.createCell(3);
			cell423.setCellValue("监控项名");
			sheet.setColumnWidth(3, cell423.getStringCellValue().toString().length() * 1024);
			cell423.setCellStyle(style20);
			// 创建第三行第五列
			HSSFCell cell424 = row2.createCell(4);
			cell424.setCellValue("最大值");
			sheet.setColumnWidth(4, cell424.getStringCellValue().toString().length() * 1024);
			cell424.setCellStyle(style20);
			// 创建第三行第六列
			HSSFCell cell425 = row2.createCell(5);
			cell425.setCellValue("平均值");
			sheet.setColumnWidth(5, cell425.getStringCellValue().toString().length() * 1024);
			cell425.setCellStyle(style20);
			// 创建第三行第七列
			HSSFCell cell426 = row2.createCell(6);
			cell426.setCellValue("最小值");
			sheet.setColumnWidth(6, cell426.getStringCellValue().toString().length() * 1024);
			cell426.setCellStyle(style20);
			// 创建第三行第八列
			HSSFCell cell427 = row2.createCell(7);
			cell427.setCellValue("严重告警次数");
			sheet.setColumnWidth(7, cell427.getStringCellValue().toString().length() * 1024);
			cell427.setCellStyle(style20);
			// 创建第三行第九列
			HSSFCell cell428 = row2.createCell(8);
			cell428.setCellValue("主要告警次数");
			sheet.setColumnWidth(8, cell428.getStringCellValue().toString().length() * 1024);
			cell428.setCellStyle(style20);
			// 创建第三行第十列
			HSSFCell cell429 = row2.createCell(9);
			cell429.setCellValue("次要告警次数");
			sheet.setColumnWidth(9, cell429.getStringCellValue().toString().length() * 1024);
			cell429.setCellStyle(style20);
			
			HSSFCellStyle styleData4 = wb.createCellStyle();
//			styleData.setAlignment(HorizontalAlignment.CENTER);
			styleData4.setWrapText(true);
			// 创建数据记录
			for (int i = 0; i < list.size(); i++) {

				HSSFRow rowStart = sheet.createRow(i + 3);

				dataLine4(wb, sheet, rowStart, styleData4, list.get(i).getIpAddress(), 
						list.get(i).getJkInstance(), list.get(i).getJkObject(),list.get(i).getJkxName(), 
						list.get(i).getMax(), list.get(i).getAvg(),
						list.get(i).getMin(),list.get(i).getYzjg(),list.get(i).getZyjg(),
						list.get(i).getCyjg());
		
			}
			
			break;
		case 5:
			// 创建第三行第一列
			HSSFCell cell520 = row2.createCell(0);
			cell520.setCellValue("设备名称");
			sheet.setColumnWidth(0, cell520.getStringCellValue().toString().length() * 1024);
			cell520.setCellStyle(style20);
			// 创建第三行第二列
			HSSFCell cell521 = row2.createCell(1);
			cell521.setCellValue("IP地址");
			sheet.setColumnWidth(1, cell521.getStringCellValue().toString().length() * 1024);
			cell521.setCellStyle(style20);
			// 创建第三行第三列
			HSSFCell cell522 = row2.createCell(2);
			cell522.setCellValue("监控对象");
			sheet.setColumnWidth(2, cell522.getStringCellValue().toString().length() * 1024);
			cell522.setCellStyle(style20);
			// 创建第三行第四列
			HSSFCell cell523 = row2.createCell(3);
			cell523.setCellValue("监控项名");
			sheet.setColumnWidth(3, cell523.getStringCellValue().toString().length() * 1024);
			cell523.setCellStyle(style20);
			// 创建第三行第五列
			HSSFCell cell524 = row2.createCell(4);
			cell524.setCellValue("严重告警次数");
			sheet.setColumnWidth(4, cell524.getStringCellValue().toString().length() * 1024);
			cell524.setCellStyle(style20);
			// 创建第三行第六列
			HSSFCell cell525 = row2.createCell(5);
			cell525.setCellValue("主要告警次数");
			sheet.setColumnWidth(5, cell525.getStringCellValue().toString().length() * 1024);
			cell525.setCellStyle(style20);
			// 创建第三行第七列
			HSSFCell cell526 = row2.createCell(6);
			cell526.setCellValue("次要告警次数");
			sheet.setColumnWidth(6, cell526.getStringCellValue().toString().length() * 1024);
			cell526.setCellStyle(style20);
			
			HSSFCellStyle styleData5 = wb.createCellStyle();
			styleData5.setWrapText(true);

			// 创建数据记录
			for (int i = 0; i < list.size(); i++) {

				HSSFRow rowStart = sheet.createRow(i + 3);

				dataLine5(wb, sheet, rowStart, styleData5, list.get(i).getSbName(), 
						list.get(i).getIpAddress(), list.get(i).getJkObject(),list.get(i).getJkxName(), 
						list.get(i).getYzjg(),list.get(i).getZyjg(),list.get(i).getCyjg());
		
			}
			
			break;
		case 6:
			// 创建第三行第一列
			HSSFCell cell620 = row2.createCell(0);
			cell620.setCellValue("IP地址");
			sheet.setColumnWidth(0, cell620.getStringCellValue().toString().length() * 1024);
			cell620.setCellStyle(style20);
			// 创建第三行第二列
			HSSFCell cell621 = row2.createCell(1);
			cell621.setCellValue("DOMAIN");
			sheet.setColumnWidth(1, cell621.getStringCellValue().toString().length() * 1024);
			cell621.setCellStyle(style20);
			// 创建第三行第三列
			HSSFCell cell622 = row2.createCell(2);
			cell622.setCellValue("SERVER");
			sheet.setColumnWidth(2, cell622.getStringCellValue().toString().length() * 1024);
			cell622.setCellStyle(style20);
			// 创建第三行第四列
			HSSFCell cell623 = row2.createCell(3);
			cell623.setCellValue("监控对象");
			sheet.setColumnWidth(3, cell623.getStringCellValue().toString().length() * 1024);
			cell623.setCellStyle(style20);
			// 创建第三行第五列
			HSSFCell cell624 = row2.createCell(4);
			cell624.setCellValue("最大值");
			sheet.setColumnWidth(4, cell624.getStringCellValue().toString().length() * 1024);
			cell624.setCellStyle(style20);
			// 创建第三行第六列
			HSSFCell cell625 = row2.createCell(5);
			cell625.setCellValue("平均值");
			sheet.setColumnWidth(5, cell625.getStringCellValue().toString().length() * 1024);
			cell625.setCellStyle(style20);
			// 创建第三行第七列
			HSSFCell cell626 = row2.createCell(6);
			cell626.setCellValue("最小值");
			sheet.setColumnWidth(6, cell626.getStringCellValue().toString().length() * 1024);
			cell626.setCellStyle(style20);
			// 创建第三行第八列
			HSSFCell cell627 = row2.createCell(7);
			cell627.setCellValue("严重告警次数");
			sheet.setColumnWidth(7, cell627.getStringCellValue().toString().length() * 1024);
			cell627.setCellStyle(style20);
			// 创建第三行第九列
			HSSFCell cell628 = row2.createCell(8);
			cell628.setCellValue("主要告警次数");
			sheet.setColumnWidth(8, cell628.getStringCellValue().toString().length() * 1024);
			cell628.setCellStyle(style20);
			// 创建第三行第十列
			HSSFCell cell629 = row2.createCell(9);
			cell629.setCellValue("次要告警次数");
			sheet.setColumnWidth(9, cell629.getStringCellValue().toString().length() * 1024);
			cell629.setCellStyle(style20);
			
			HSSFCellStyle styleData6 = wb.createCellStyle();
			styleData6.setWrapText(true);
			// 创建数据记录
			for (int i = 0; i < list.size(); i++) {

				HSSFRow rowStart = sheet.createRow(i + 3);

				dataLine6(wb, sheet, rowStart, styleData6, list.get(i).getIpAddress(), list.get(i).getDOMAIN(),
						list.get(i).getSERVER(), list.get(i).getJkObject(), list.get(i).getMax(), list.get(i).getAvg(),
						list.get(i).getMin(), list.get(i).getYzjg(), list.get(i).getZyjg(), list.get(i).getCyjg());

			}
			
			break;
		default:
			// 创建第三行第一列
			HSSFCell cell20 = row2.createCell(0);
			cell20.setCellValue("设备名称");
			sheet.setColumnWidth(0, cell20.getStringCellValue().toString().length() * 1024);
			cell20.setCellStyle(style20);
			// 创建第三行第二列
			HSSFCell cell21 = row2.createCell(1);
			cell21.setCellValue("IP地址");
			sheet.setColumnWidth(1, cell21.getStringCellValue().toString().length() * 1024);
			cell21.setCellStyle(style20);
			// 创建第三行第三列
			HSSFCell cell22 = row2.createCell(2);
			cell22.setCellValue("监控对象");
			sheet.setColumnWidth(2, cell22.getStringCellValue().toString().length() * 1024);
			cell22.setCellStyle(style20);
			// 创建第三行第四列
			HSSFCell cell23 = row2.createCell(3);
			cell23.setCellValue("监控项名");
			sheet.setColumnWidth(3, cell23.getStringCellValue().toString().length() * 1024);
			cell23.setCellStyle(style20);
			// 创建第三行第五列
			HSSFCell cell24 = row2.createCell(4);
			cell24.setCellValue("最大值");
			sheet.setColumnWidth(4, cell24.getStringCellValue().toString().length() * 1024);
			cell24.setCellStyle(style20);
			// 创建第三行第六列
			HSSFCell cell25 = row2.createCell(5);
			cell25.setCellValue("平均值");
			sheet.setColumnWidth(5, cell25.getStringCellValue().toString().length() * 1024);
			cell25.setCellStyle(style20);
			// 创建第三行第七列
			HSSFCell cell26 = row2.createCell(6);
			cell26.setCellValue("最小值");
			sheet.setColumnWidth(6, cell26.getStringCellValue().toString().length() * 1024);
			cell26.setCellStyle(style20);
			// 创建第三行第八列
			HSSFCell cell27 = row2.createCell(7);
			cell27.setCellValue("严重告警次数");
			sheet.setColumnWidth(7, cell27.getStringCellValue().toString().length() * 1024);
			cell27.setCellStyle(style20);
			// 创建第三行第九列
			HSSFCell cell28 = row2.createCell(8);
			cell28.setCellValue("主要告警次数");
			sheet.setColumnWidth(8, cell28.getStringCellValue().toString().length() * 1024);
			cell28.setCellStyle(style20);
			// 创建第三行第十列
			HSSFCell cell29 = row2.createCell(9);
			cell29.setCellValue("次要告警次数");
			sheet.setColumnWidth(9, cell29.getStringCellValue().toString().length() * 1024);
			cell29.setCellStyle(style20);
	
			HSSFCellStyle styleData = wb.createCellStyle();
			// styleData.setAlignment(HorizontalAlignment.CENTER);
			styleData.setWrapText(true);
			// 创建数据记录
			for (int i = 0; i < list.size(); i++) {

				HSSFRow rowStart = sheet.createRow(i + 3);

				dataLine(wb, sheet, rowStart, styleData, list.get(i).getSbName(), list.get(i).getIpAddress(),
						list.get(i).getJkObject(), list.get(i).getJkxName(), list.get(i).getMax(), list.get(i).getAvg(),
						list.get(i).getMin(), list.get(i).getYzjg(), list.get(i).getZyjg(), list.get(i).getCyjg());

			}
			
			break;
		}

	}
	
	private void dataLine(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow rowStart, HSSFCellStyle styleData, String sbName,
			String ipAddress, String jkObject, String jkxName, String max, String avg, String min, String yzjg,
			String zyjg, String cyjg) {

		// 创建第四行第一列"设备名称"
		HSSFCell cellSbName = rowStart.createCell(0);
		if (sbName != null && !sbName.equals("")) {
			cellSbName.setCellValue(sbName);
			cellSbName.setCellStyle(styleData);
		} else {
			cellSbName.setCellValue("");
			cellSbName.setCellStyle(styleData);
		}

		// 创建第四行第二列"IP地址"
		HSSFCell cellIpAddress = rowStart.createCell(1);
		if (ipAddress != null && !ipAddress.equals("")) {
			cellIpAddress.setCellValue(ipAddress);
			cellIpAddress.setCellStyle(styleData);
		} else {
			cellIpAddress.setCellValue("");
			cellIpAddress.setCellStyle(styleData);
		}

		// 创建第四行第三列"监控对象"
		HSSFCell cellJkObject = rowStart.createCell(2);
		if (jkObject != null && !jkObject.equals("")) {
			cellJkObject.setCellValue(jkObject);
			cellJkObject.setCellStyle(styleData);
		} else {
			cellJkObject.setCellValue("");
			cellJkObject.setCellStyle(styleData);
		}

		// 创建第四行第四列"监控项名"
		HSSFCell cellJkxName = rowStart.createCell(3);
		if (jkxName != null && !jkxName.equals("")) {
			cellJkxName.setCellValue(jkxName);
			cellJkxName.setCellStyle(styleData);
		} else {
			cellJkxName.setCellValue("");
			cellJkxName.setCellStyle(styleData);
		}

		// 创建第四行第五列"最大值"
		HSSFCell cellMax = rowStart.createCell(4);
		if (max != null && !max.equals("")) {
			cellMax.setCellValue(max);
			cellMax.setCellStyle(styleData);
		} else {
			cellMax.setCellValue("");
			cellMax.setCellStyle(styleData);
		}

		// 创建第四行第六列"平均值"
		HSSFCell cellAvg = rowStart.createCell(5);
		if (avg != null && !avg.equals("")) {
			cellAvg.setCellValue(avg);
			cellAvg.setCellStyle(styleData);
		} else {
			cellAvg.setCellValue("");
			cellAvg.setCellStyle(styleData);
		}

		// 创建第四行第七列"最小值"
		HSSFCell cellMin = rowStart.createCell(6);
		if (min != null && !min.equals("")) {
			cellMin.setCellValue(min);
			cellMin.setCellStyle(styleData);
		} else {
			cellMin.setCellValue("");
			cellMin.setCellStyle(styleData);
		}

		// 创建第四行第八列"严重告警次数"
		HSSFCell cellYzgj = rowStart.createCell(7);
		if (yzjg != null && !yzjg.equals("")) {
			cellYzgj.setCellValue(yzjg);
			cellYzgj.setCellStyle(styleData);
		} else {
			cellYzgj.setCellValue("");
			cellYzgj.setCellStyle(styleData);
		}

		// 创建第四行第九列"主要告警次数"
		HSSFCell cellZygj = rowStart.createCell(8);
		if (zyjg != null && !zyjg.equals("")) {
			cellZygj.setCellValue(zyjg);
			cellZygj.setCellStyle(styleData);
		} else {
			cellZygj.setCellValue("");
			cellZygj.setCellStyle(styleData);
		}
		// 创建第四行第十列"次要告警次数"
		HSSFCell cellCygj = rowStart.createCell(9);
		if (cyjg != null && !cyjg.equals("")) {
			cellCygj.setCellValue(cyjg);
			cellCygj.setCellStyle(styleData);
		} else {
			cellCygj.setCellValue("");
			cellCygj.setCellStyle(styleData);
		}
	}
	
	private void dataLine4(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow rowStart,
			HSSFCellStyle styleData,String ipAddress, String jkInstance,
			String jkObject, String jkxName, String max,
			String avg,String min,String yzjg,String zyjg,String cyjg) {
		
		// 创建第四行第一列"IP地址"
		HSSFCell cellIpAddress = rowStart.createCell(0);
		if (ipAddress!=null&&!ipAddress.equals("")) {
			cellIpAddress.setCellValue(ipAddress);
			cellIpAddress.setCellStyle(styleData);
		}else{
			cellIpAddress.setCellValue("");	
			cellIpAddress.setCellStyle(styleData);
		}

		// 创建第四行第二列"监控实例"
		HSSFCell cellJkInstance = rowStart.createCell(1);
		if (jkInstance!=null&&!jkInstance.equals("")) {
			cellJkInstance.setCellValue(jkInstance);
			cellJkInstance.setCellStyle(styleData);
		}else{
			cellJkInstance.setCellValue("");
			cellJkInstance.setCellStyle(styleData);
		}

		// 创建第四行第三列"监控对象"
		HSSFCell cellJkObject = rowStart.createCell(2);
		if (jkObject!=null&&!jkObject.equals("")) {
			cellJkObject.setCellValue(jkObject);
			cellJkObject.setCellStyle(styleData);
		}else{
			cellJkObject.setCellValue("");
			cellJkObject.setCellStyle(styleData);
		}

		// 创建第四行第四列"监控项名"
		HSSFCell cellJkxName = rowStart.createCell(3);
		if (jkxName!=null&&!jkxName.equals("")) {
			cellJkxName.setCellValue(jkxName);
			cellJkxName.setCellStyle(styleData);
		}else{
			cellJkxName.setCellValue("");
			cellJkxName.setCellStyle(styleData);
		}
		
		// 创建第四行第五列"最大值"
		HSSFCell cellMax = rowStart.createCell(4);
		if (max!=null&&!max.equals("")) {
			cellMax.setCellValue(max);
			cellMax.setCellStyle(styleData);
		}else{
			cellMax.setCellValue("");
			cellMax.setCellStyle(styleData);
		}
		
		// 创建第四行第六列"平均值"
		HSSFCell cellAvg = rowStart.createCell(5);
		if (min!=null&&!min.equals("")) {
			cellAvg.setCellValue(min);
			cellAvg.setCellStyle(styleData);
		}else{
			cellAvg.setCellValue("");
			cellAvg.setCellStyle(styleData);
		}

		// 创建第四行第七列"最小值"
		HSSFCell cellMin = rowStart.createCell(6);
		if (min!=null&&!min.equals("")) {
			cellMin.setCellValue(min);
			cellMin.setCellStyle(styleData);
		}else{
			cellMin.setCellValue("");
			cellMin.setCellStyle(styleData);
		}
		
		// 创建第四行第八列"严重告警次数"
		HSSFCell cellYzgj = rowStart.createCell(7);
		if (yzjg!=null&&!yzjg.equals("")) {
			cellYzgj.setCellValue(yzjg);
			cellYzgj.setCellStyle(styleData);
		}else{
			cellYzgj.setCellValue("");
			cellYzgj.setCellStyle(styleData);
		}
		
		// 创建第四行第九列"主要告警次数"
		HSSFCell cellZygj = rowStart.createCell(8);
		if (zyjg!=null&&!zyjg.equals("")) {
			cellZygj.setCellValue(zyjg);
			cellZygj.setCellStyle(styleData);
		}else{
			cellZygj.setCellValue("");
			cellZygj.setCellStyle(styleData);
		}
		// 创建第四行第十列"次要告警次数"
		HSSFCell cellCygj = rowStart.createCell(9);
		if (cyjg!=null&&!cyjg.equals("")) {
			cellCygj.setCellValue(cyjg);
			cellCygj.setCellStyle(styleData);
		}else{
			cellCygj.setCellValue("");
			cellCygj.setCellStyle(styleData);
		}
	}
	
	private void dataLine5(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow rowStart,
			HSSFCellStyle styleData,String sbName, String ipAddress,
			String jkObject, String jkxName, String yzjg,String zyjg,String cyjg) {
		
		// 创建第四行第一列"设备名称"
		HSSFCell cellSbName = rowStart.createCell(0);
		if (sbName!=null&&!sbName.equals("")) {
			cellSbName.setCellValue(sbName);
			cellSbName.setCellStyle(styleData);
		}else{
			cellSbName.setCellValue("");	
			cellSbName.setCellStyle(styleData);
		}

		// 创建第四行第二列"IP地址"
		HSSFCell cellIpAddress = rowStart.createCell(1);
		if (ipAddress!=null&&!ipAddress.equals("")) {
			cellIpAddress.setCellValue(ipAddress);
			cellIpAddress.setCellStyle(styleData);
		}else{
			cellIpAddress.setCellValue("");
			cellIpAddress.setCellStyle(styleData);
		}

		// 创建第四行第三列"监控对象"
		HSSFCell cellJkObject = rowStart.createCell(2);
		if (jkObject!=null&&!jkObject.equals("")) {
			cellJkObject.setCellValue(jkObject);
			cellJkObject.setCellStyle(styleData);
		}else{
			cellJkObject.setCellValue("");
			cellJkObject.setCellStyle(styleData);
		}

		// 创建第四行第四列"监控项名"
		HSSFCell cellJkxName = rowStart.createCell(3);
		if (jkxName!=null&&!jkxName.equals("")) {
			cellJkxName.setCellValue(jkxName);
			cellJkxName.setCellStyle(styleData);
		}else{
			cellJkxName.setCellValue("");
			cellJkxName.setCellStyle(styleData);
		}
		
		// 创建第四行第五列"严重告警次数"
		HSSFCell cellYzgj = rowStart.createCell(4);
		if (yzjg!=null&&!yzjg.equals("")) {
			cellYzgj.setCellValue(yzjg);
			cellYzgj.setCellStyle(styleData);
		}else{
			cellYzgj.setCellValue("");
			cellYzgj.setCellStyle(styleData);
		}
		
		// 创建第四行第六列"主要告警次数"
		HSSFCell cellZygj = rowStart.createCell(5);
		if (zyjg!=null&&!zyjg.equals("")) {
			cellZygj.setCellValue(zyjg);
			cellZygj.setCellStyle(styleData);
		}else{
			cellZygj.setCellValue("");
			cellZygj.setCellStyle(styleData);
		}
		// 创建第四行第七列"次要告警次数"
		HSSFCell cellCygj = rowStart.createCell(6);
		if (cyjg!=null&&!cyjg.equals("")) {
			cellCygj.setCellValue(cyjg);
			cellCygj.setCellStyle(styleData);
		}else{
			cellCygj.setCellValue("");
			cellCygj.setCellStyle(styleData);
		}
	}
	 
	private void dataLine6(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow rowStart, HSSFCellStyle styleData, String IpAddress,
			String DOMAIN, String SERVER, String JkObject, String max, String avg, String min, String yzjg,
			String zyjg, String cyjg) {

		// 创建第四行第一列"IP地址"
		HSSFCell cellIpAddress = rowStart.createCell(0);
		if (IpAddress != null && !IpAddress.equals("")) {
			cellIpAddress.setCellValue(IpAddress);
			cellIpAddress.setCellStyle(styleData);
		} else {
			cellIpAddress.setCellValue("");
			cellIpAddress.setCellStyle(styleData);
		}

		// 创建第四行第二列"DOMAIN"
		HSSFCell cellDOMAIN = rowStart.createCell(1);
		if (DOMAIN != null && !DOMAIN.equals("")) {
			cellDOMAIN.setCellValue(DOMAIN);
			cellDOMAIN.setCellStyle(styleData);
		} else {
			cellDOMAIN.setCellValue("");
			cellDOMAIN.setCellStyle(styleData);
		}

		// 创建第四行第三列"SERVER"
		HSSFCell cellJkSERVER = rowStart.createCell(2);
		if (SERVER != null && !SERVER.equals("")) {
			cellJkSERVER.setCellValue(SERVER);
			cellJkSERVER.setCellStyle(styleData);
		} else {
			cellJkSERVER.setCellValue("");
			cellJkSERVER.setCellStyle(styleData);
		}

		// 创建第四行第四列"监控项名"
		HSSFCell cellJkObject = rowStart.createCell(3);
		if (JkObject != null && !JkObject.equals("")) {
			cellJkObject.setCellValue(JkObject);
			cellJkObject.setCellStyle(styleData);
		} else {
			cellJkObject.setCellValue("");
			cellJkObject.setCellStyle(styleData);
		}

		// 创建第四行第五列"最大值"
		HSSFCell cellMax = rowStart.createCell(4);
		if (max != null && !max.equals("")) {
			cellMax.setCellValue(max);
			cellMax.setCellStyle(styleData);
		} else {
			cellMax.setCellValue("");
			cellMax.setCellStyle(styleData);
		}

		// 创建第四行第六列"平均值"
		HSSFCell cellAvg = rowStart.createCell(5);
		if (min != null && !min.equals("")) {
			cellAvg.setCellValue(min);
			cellAvg.setCellStyle(styleData);
		} else {
			cellAvg.setCellValue("");
			cellAvg.setCellStyle(styleData);
		}

		// 创建第四行第七列"最小值"
		HSSFCell cellMin = rowStart.createCell(6);
		if (min != null && !min.equals("")) {
			cellMin.setCellValue(min);
			cellMin.setCellStyle(styleData);
		} else {
			cellMin.setCellValue("");
			cellMin.setCellStyle(styleData);
		}

		// 创建第四行第八列"严重告警次数"
		HSSFCell cellYzgj = rowStart.createCell(7);
		if (yzjg != null && !yzjg.equals("")) {
			cellYzgj.setCellValue(yzjg);
			cellYzgj.setCellStyle(styleData);
		} else {
			cellYzgj.setCellValue("");
			cellYzgj.setCellStyle(styleData);
		}

		// 创建第四行第九列"主要告警次数"
		HSSFCell cellZygj = rowStart.createCell(8);
		if (zyjg != null && !zyjg.equals("")) {
			cellZygj.setCellValue(zyjg);
			cellZygj.setCellStyle(styleData);
		} else {
			cellZygj.setCellValue("");
			cellZygj.setCellStyle(styleData);
		}
		// 创建第四行第十列"次要告警次数"
		HSSFCell cellCygj = rowStart.createCell(9);
		if (cyjg != null && !cyjg.equals("")) {
			cellCygj.setCellValue(cyjg);
			cellCygj.setCellStyle(styleData);
		} else {
			cellCygj.setCellValue("");
			cellCygj.setCellStyle(styleData);
		}
	}		
	
}
