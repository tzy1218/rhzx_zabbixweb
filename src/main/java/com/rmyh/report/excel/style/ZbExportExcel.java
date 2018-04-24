package com.rmyh.report.excel.style;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.*;
//import java.util.Properties;

import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import com.rmyh.report.excel.bean.ZbBean;

public class ZbExportExcel {
	
	public static String excelNamePre = "Monit"; 
	
//	public Properties initProp() throws IOException {
//		Properties prop = new Properties();
//		String property = System.getProperty("user.dir");
//		File file = new File(property+"/excel.properties");
//		InputStream input = new FileInputStream(file);
//		//    InputStreamReader inputR = new InputStreamReader(input,"utf-8");
//		prop.load(input);
//		return prop;
//	}
	
	public void Export (String startTime, String endTime, List<List <ZbBean>> list) throws IOException {
		
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
					property+"/" +excelNamePre + excelName + ".xls");
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
	
	public void makeSheet (HSSFWorkbook wb, List<ZbBean> list,int sheetId) {
		
//		String sheetName = "";
		String sheetName = list.get(0).getGroupName();
//		switch(sheetId){
//		case 0:
//			sheetName = "A01Linux操作系统服务器_监控指标报表";
//		    break;
//		case 1:
//			sheetName = "A02IBM_AIX操作系统服务器_监控指标报表";
//		    break;
//		case 2:
//			sheetName = "A03HP_UNIX操作系统服务器_监控指标报表";
//			break;
//		case 3:
//			sheetName = "A04windows操作系统服务器_监控指标报表";
//			break;
//		case 4:
//			sheetName = "A05ORACLE数据库_监控指标报表";
//			break;
//		case 5:
//			sheetName = "A06SNMP存储设备_监控指标报表";
//			break;
//		case 6:
//			sheetName = "A07中间件WEBLOGIC_监控指标报表";
//			break;
//		}
		// 声明一个单子并命名
		HSSFSheet sheet = wb.createSheet(sheetName);
		makeFixedPart(wb,sheet,sheetId);
		makeDataPart(wb,sheet,sheetId,list);
		mergeColumn(wb,sheetId,list,sheet);
	}
	
	public void mergeColumn(HSSFWorkbook wb, int sheetId, List<ZbBean> list,HSSFSheet sheet) {
		
		switch (sheetId) {
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
			//合并第四列相同的数据
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap6;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getJkx().equals(list.get(endRow - rowgap6).getJkx())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow-1, 3, 3);
							sheet.addMergedRegion(column3RangeAddress);
							i = endRow - rowgap6;
							break;
						} else {
							i = endRow - rowgap6;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap6) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow, 3, 3);
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
			//合并第四列相同的数据
			for (int i = 0; i < list.size() - 1;) {
				int startRow = i + rowgap;
				for (int j = 1;; j++) {
					int endRow = startRow + j;
					if (!list.get(i).getJkxName().equals(list.get(endRow - rowgap).getJkxName())) {
						if (startRow != endRow - 1) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow-1, 3, 3);
							sheet.addMergedRegion(column3RangeAddress);
							i = endRow - rowgap;
							break;
						} else {
							i = endRow - rowgap;
							break;
						}
					} else {
						if(endRow == list.size()-1+rowgap) {
							CellRangeAddress column3RangeAddress = new CellRangeAddress(startRow, endRow, 3, 3);
							sheet.addMergedRegion(column3RangeAddress);
							i = list.size();
							break;
						} else {
							endRow++;}
					}
				}
			break;
			}
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
		cell.setCellValue("监控指标统一报表");
		// 合并第一行的列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 7);
		sheet.addMergedRegion(cellRangeAddress);	
		
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
	public void makeDataPart (HSSFWorkbook wb,HSSFSheet sheet,int sheetId,List<ZbBean> list) {
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
			cell623.setCellValue("监控项");
			sheet.setColumnWidth(3, cell623.getStringCellValue().toString().length() * 1024);
			cell623.setCellStyle(style20);
			// 创建第三行第五列
			HSSFCell cell624 = row2.createCell(4);
			cell624.setCellValue("告警阀值");
			sheet.setColumnWidth(4, cell624.getStringCellValue().toString().length() * 1024);
			cell624.setCellStyle(style20);
			// 创建第三行第六列
			HSSFCell cell625 = row2.createCell(5);
			cell625.setCellValue("告警级别");
			sheet.setColumnWidth(5, cell625.getStringCellValue().toString().length() * 1024);
			cell625.setCellStyle(style20);
			// 创建第三行第七列
			HSSFCell cell626 = row2.createCell(6);
			cell626.setCellValue("压制次数");
			sheet.setColumnWidth(6, cell626.getStringCellValue().toString().length() * 1024);
			cell626.setCellStyle(style20);
			// 创建第三行第八列
			HSSFCell cell627 = row2.createCell(7);
			cell627.setCellValue("状态");
			sheet.setColumnWidth(7, cell627.getStringCellValue().toString().length() * 1024);
			cell627.setCellStyle(style20);
			
			HSSFCellStyle styleData6 = wb.createCellStyle();
			styleData6.setWrapText(true);
			// 创建数据记录
			for (int i = 0; i < list.size(); i++) {

				HSSFRow rowStart = sheet.createRow(i + 3);

				dataLine6(wb, sheet, rowStart, styleData6, list.get(i).getIpAddress(), list.get(i).getDOMAIN(),
						list.get(i).getSERVER(), list.get(i).getJkx(), list.get(i).getGjfz(), list.get(i).getGjjb(),
						list.get(i).getYzcs(), list.get(i).getState());

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
			cell24.setCellValue("告警等级");
			sheet.setColumnWidth(4, cell24.getStringCellValue().toString().length() * 1024);
			cell24.setCellStyle(style20);
			// 创建第三行第六列
			HSSFCell cell25 = row2.createCell(5);
			cell25.setCellValue("压制次数");
			sheet.setColumnWidth(5, cell25.getStringCellValue().toString().length() * 1024);
			cell25.setCellStyle(style20);
			// 创建第三行第七列
			HSSFCell cell26 = row2.createCell(6);
			cell26.setCellValue("告警表阈值达式");
			sheet.setColumnWidth(6, cell26.getStringCellValue().toString().length() * 1024);
			cell26.setCellStyle(style20);
			// 创建第三行第八列
			HSSFCell cell27 = row2.createCell(7);
			cell27.setCellValue("状态");
			sheet.setColumnWidth(7, cell27.getStringCellValue().toString().length() * 1024);
			cell27.setCellStyle(style20);
	
			HSSFCellStyle styleData = wb.createCellStyle();
			styleData.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleData.setWrapText(true);
			// 创建数据记录
			for (int i = 0; i < list.size(); i++) {

				HSSFRow rowStart = sheet.createRow(i + 3);

				dataLine(wb, sheet, rowStart, styleData, list.get(i).getSbName(), list.get(i).getIpAddress(),
						list.get(i).getJkObject(), list.get(i).getJkxName(), list.get(i).getGjdj(), list.get(i).getYzcs(),
						list.get(i).getGjfzbds(), list.get(i).getState());

			}
			
			break;
		}

	}
	
	private void dataLine(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow rowStart, HSSFCellStyle styleData, String sbName,
			String ipAddress, String jkObject, String jkxName, String gjdj, String yzcs, String gjfzbds, String state) {

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

		// 创建第四行第五列"告警等级"
		HSSFCell cellMax = rowStart.createCell(4);
		if (gjdj != null && !gjdj.equals("")) {
			cellMax.setCellValue(gjdj);
			cellMax.setCellStyle(styleData);
		} else {
			cellMax.setCellValue("");
			cellMax.setCellStyle(styleData);
		}

		// 创建第四行第六列"压制次数"
		HSSFCell cellAvg = rowStart.createCell(5);
		if (yzcs != null && !yzcs.equals("")) {
			cellAvg.setCellValue(yzcs);
			cellAvg.setCellStyle(styleData);
		} else {
			cellAvg.setCellValue("");
			cellAvg.setCellStyle(styleData);
		}

		// 创建第四行第七列"告警表阈值达式"
		HSSFCell cellMin = rowStart.createCell(6);
		if (gjfzbds != null && !gjfzbds.equals("")) {
			cellMin.setCellValue(gjfzbds);
			cellMin.setCellStyle(styleData);
		} else {
			cellMin.setCellValue("");
			cellMin.setCellStyle(styleData);
		}

		// 创建第四行第八列"状态"
		HSSFCell cellYzgj = rowStart.createCell(7);
		if (state != null && !state.equals("")) {
			cellYzgj.setCellValue(state);
			cellYzgj.setCellStyle(styleData);
		} else {
			cellYzgj.setCellValue("");
			cellYzgj.setCellStyle(styleData);
		}
	}
	
	private void dataLine6(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow rowStart, HSSFCellStyle styleData, String IpAddress,
			String DOMAIN, String SERVER, String Jkx, String Gjfz, String Gjjb, String Yzcs, String State) {

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

		// 创建第四行第四列"监控项"
		HSSFCell cellJkObject = rowStart.createCell(3);
		if (Jkx != null && !Jkx.equals("")) {
			cellJkObject.setCellValue(Jkx);
			cellJkObject.setCellStyle(styleData);
		} else {
			cellJkObject.setCellValue("");
			cellJkObject.setCellStyle(styleData);
		}

		// 创建第四行第五列"告警阀值"
		HSSFCell cellMax = rowStart.createCell(4);
		if (Gjfz != null && !Gjfz.equals("")) {
			cellMax.setCellValue(Gjfz);
			cellMax.setCellStyle(styleData);
		} else {
			cellMax.setCellValue("");
			cellMax.setCellStyle(styleData);
		}

		// 创建第四行第六列"告警级别"
		HSSFCell cellAvg = rowStart.createCell(5);
		if (Gjjb != null && !Gjjb.equals("")) {
			cellAvg.setCellValue(Gjjb);
			cellAvg.setCellStyle(styleData);
		} else {
			cellAvg.setCellValue("");
			cellAvg.setCellStyle(styleData);
		}

		// 创建第四行第七列"压制次数"
		HSSFCell cellMin = rowStart.createCell(6);
		if (Yzcs != null && !Yzcs.equals("")) {
			cellMin.setCellValue(Yzcs);
			cellMin.setCellStyle(styleData);
		} else {
			cellMin.setCellValue("");
			cellMin.setCellStyle(styleData);
		}

		// 创建第四行第八列"状态"
		HSSFCell cellYzgj = rowStart.createCell(7);
		if (State != null && !State.equals("")) {
			cellYzgj.setCellValue(State);
			cellYzgj.setCellStyle(styleData);
		} else {
			cellYzgj.setCellValue("");
			cellYzgj.setCellStyle(styleData);
		}

	}		
	
}
