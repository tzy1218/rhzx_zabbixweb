package com.rmyh.report.excel.style;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//import java.util.Properties;
import java.io.*;
// import java.util.Properties;

import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import com.rmyh.report.excel.bean.GjBean;

public class GjtyExportExcel {
    /**
	 * 告警统一报表
	 * 
	 * @param wb
	 * @param startTime
	 * @param endTime
	 * @param list
	 */
	public static String excelNamePre = "Alert"; 
	//获取字体
	public static String getFontName = "\u5B8B\u4F53" ; 

//    public Properties initProp() throws IOException {
//    		Properties prop = new Properties();
//    		String property = System.getProperty("user.dir");
//    		File file = new File(property+"/excel.properties");
//        InputStream input = new FileInputStream(file);
////        InputStreamReader inputR = new InputStreamReader(input,"utf-8");
//        prop.load(input);
//        return prop;
//    }

    

	@SuppressWarnings("deprecation")
	public void Export(String startTime,String endTime,List<GjBean> list) throws IOException {
		
//		Properties prop = initProp();
		// 声明一个单子并命名
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("\u544A\u8B66\u7EDF\u4E00\u62A5\u8868");
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((short) 33);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		// 创建第一行（也可以称为表头）
		HSSFRow row = sheet.createRow(0);
		// 创建第一行第一列
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("\u6C47\u603B\u544A\u8B66\u62A5\u8868");
		// 合并第一行的列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 7);
		sheet.addMergedRegion(cellRangeAddress);
		// 第一行居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 第一行字体格式(宋体,20,加粗)
		HSSFFont headFont = wb.createFont();
		headFont.setFontName(getFontName);
		headFont.setFontHeightInPoints((short) 30);
		headFont.setTypeOffset(FontFormatting.SS_SUPER);
		headFont.setBold(true);
		style.setFont(headFont);
		cell.setCellStyle(style);
		// 第一行背景颜色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());

		// 创建第二行
		HSSFRow row1 = sheet.createRow(1);
		// 创建第二行第一列
		HSSFCell cell10 = row1.createCell(0);
		HSSFCellStyle style10 = wb.createCellStyle();
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tableTime = "报表类型:日报 时间:"+df.format(day.getTime());
		cell10.setCellValue(tableTime);
		sheet.setColumnWidth(0,
				cell10.getStringCellValue().toString().length() * 1024);
		HSSFFont headFont10 = wb.createFont();
		headFont10.setFontName(getFontName);
		headFont10.setFontHeightInPoints((short) 11);
		style10.setFont(headFont10);
		cell10.setCellStyle(style10);

		// 创建第三行
		HSSFRow row2 = sheet.createRow(2);
		// 创建第三行第一列
		HSSFCell cell20 = row2.createCell(0);
		HSSFCellStyle style20 = wb.createCellStyle();
		cell20.setCellValue("\u65F6\u95F4");
		sheet.setColumnWidth(0,
				cell20.getStringCellValue().toString().length() * 1024);
		HSSFFont headFont20 = wb.createFont();
		headFont20.setFontName(getFontName);
		headFont20.setFontHeightInPoints((short) 11);
		style20.setFont(headFont20);
		// 第三行背景颜色
		style20.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style20.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style20.setBorderBottom(	HSSFCellStyle.BORDER_THICK);
		style20.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style20.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style20.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cell20.setCellStyle(style20);

		// 创建第三行第二列
		HSSFCell cell21 = row2.createCell(1);
		cell21.setCellValue("\u544A\u8B66\u7EA7\u522B");
		sheet.setColumnWidth(1,
				cell21.getStringCellValue().toString().length() * 1024);
		cell21.setCellStyle(style20);
		
	    // 创建第三行第三列
		HSSFCell cell22 = row2.createCell(2);
		cell22.setCellValue("\u4E3B\u673A\u540D");
		sheet.setColumnWidth(2,
				cell22.getStringCellValue().toString().length() * 1024);
		cell22.setCellStyle(style20);
		
		// 创建第三行四列
		HSSFCell cell23 = row2.createCell(3);
		cell23.setCellValue("IP\u5730\u5740");
		sheet.setColumnWidth(3,
				cell23.getStringCellValue().toString().length() * 1024);
		cell23.setCellStyle(style20);

		// 创建第三行第五列
		HSSFCell cell24 = row2.createCell(4);
		cell24.setCellValue("\u544A\u8B66\u6B21\u6570");
		sheet.setColumnWidth(4,
				cell24.getStringCellValue().toString().length() * 1024);
		cell24.setCellStyle(style20);

		// 创建第三行第六列
		HSSFCell cell25 = row2.createCell(5);
		cell25.setCellValue("\u5177\u4F53\u544A\u8B66\u5185\u5BB9");
		sheet.setColumnWidth(5,
				cell25.getStringCellValue().toString().length() * 2048);
		cell25.setCellStyle(style20);

		// 创建第三行第七列
		HSSFCell cell26 = row2.createCell(6);
		cell26.setCellValue("\u72B6\u6001");
		sheet.setColumnWidth(6,
				cell26.getStringCellValue().toString().length() * 1024);
		cell26.setCellStyle(style20);

		// 创建第三行第八列
		HSSFCell cell27 = row2.createCell(7);
		cell27.setCellValue("\u786E\u8BA4\u72B6\u6001");
		sheet.setColumnWidth(7,
				cell27.getStringCellValue().toString().length() * 1024);
		cell27.setCellStyle(style20);


		HSSFCellStyle styleData = wb.createCellStyle();
//		styleData.setAlignment(HorizontalAlignment.CENTER);
		styleData.setWrapText(true);
		// 第四行字体格式(宋体)
		HSSFFont headFontStart = wb.createFont();
		headFontStart.setFontName(getFontName);
		headFontStart.setFontHeightInPoints((short) 11);
		styleData.setFont(headFontStart);

		for (int i = 0; i < list.size(); i++) {

			HSSFRow rowStart = sheet.createRow(i + 3);

			dataLine(wb, sheet, rowStart, styleData, list.get(i).getGjTime(), 
					list.get(i).getGjLevel(), list.get(i).getHostName(),list.get(i).getIpAddress(), 
					list.get(i).getGjFrequency(), list.get(i).getGjContent(),
					list.get(i).getState(),list.get(i).getConfirmState());
		}

		try {
			// 默认导出到盘下
			String excelName = ""+startTime+endTime; 
			String property = System.getProperty("user.dir");
//			FileOutputStream out = new FileOutputStream("/../"+property+"/"+excelName+".xls");
			FileOutputStream out = new FileOutputStream(property+"/" +excelNamePre + excelName + ".xls");
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



/**
	 * 各列数据插入的方法
	 * 
	 * @param wb
	 * @param sheet
	 * @param rowStart
	 * @param styleData
	 * @param gjTime
	 * @param gjLevel
	 * @param hostName
	 * @param ipAddress
	 * @param gjFrequency
	 * @param gjContent
	 * @param state
	 * @param confirmState
	 */
	private void dataLine(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow rowStart,
			HSSFCellStyle styleData,String gjTime, String gjLevel,
			String hostName, String ipAddress, String gjFrequency,
			String gjContent,String state,String confirmState) {
		
		// 创建第四行第一列"时间"
		HSSFCell cellGjTime = rowStart.createCell(0);
		if (gjTime!=null&&!gjTime.equals("")) {
			cellGjTime.setCellValue(gjTime);
			cellGjTime.setCellStyle(styleData);
		}else{
			cellGjTime.setCellValue("");
			cellGjTime.setCellStyle(styleData);
		}

		// 创建第四行第二列"告警级别"
		HSSFCell cellGjLevel = rowStart.createCell(1);
		if (gjLevel!=null&&!gjLevel.equals("")) {
			cellGjLevel.setCellValue(gjLevel);
			cellGjLevel.setCellStyle(styleData);
		}else{
			cellGjLevel.setCellValue("");
			cellGjLevel.setCellStyle(styleData);
		}

		// 创建第四行第三列"主机名"
		HSSFCell cellHostName = rowStart.createCell(2);
		if (hostName!=null&&!hostName.equals("")) {
			cellHostName.setCellValue(hostName);
			cellHostName.setCellStyle(styleData);
		}else{
			cellHostName.setCellValue("");
			cellHostName.setCellStyle(styleData);
		}

		// 创建第四行第四列"IP地址"
		HSSFCell cellIpAddress = rowStart.createCell(3);
		if (ipAddress!=null&&!ipAddress.equals("")) {
			cellIpAddress.setCellValue(ipAddress);
			cellIpAddress.setCellStyle(styleData);
		}else{
			cellIpAddress.setCellValue("");
			cellIpAddress.setCellStyle(styleData);
		}
		
		// 创建第四行第五列"告警次数"
		HSSFCell cellGjFrequency = rowStart.createCell(4);
		if (gjFrequency!=null&&!gjFrequency.equals("")) {
			cellGjFrequency.setCellValue(gjFrequency);
			cellGjFrequency.setCellStyle(styleData);
		}else{
			cellGjFrequency.setCellValue("");
			cellGjFrequency.setCellStyle(styleData);
		}
		
		// 创建第四行第六列"具体告警内容"
		HSSFCell cellGjContent = rowStart.createCell(5);
		if (gjContent!=null&&!gjContent.equals("")) {
			cellGjContent.setCellValue(gjContent);
			cellGjContent.setCellStyle(styleData);
		}else{
			cellGjContent.setCellValue("");
			cellGjContent.setCellStyle(styleData);
		}

		// 创建第四行第七列"状态"
		HSSFCell cellState = rowStart.createCell(6);
		if (state!=null&&!state.equals("")) {
			cellState.setCellValue(state);
			cellState.setCellStyle(styleData);
		}else{
			cellState.setCellValue("");
			cellState.setCellStyle(styleData);
		}
		
		// 创建第四行第八列"确认状态"
		HSSFCell cellConfirmState = rowStart.createCell(7);
		if (confirmState!=null&&!confirmState.equals("")) {
			cellConfirmState.setCellValue(confirmState);
			cellConfirmState.setCellStyle(styleData);
		}else{
			cellConfirmState.setCellValue("");
			cellConfirmState.setCellStyle(styleData);
		}
	}
}