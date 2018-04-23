//package com.rmyh.report.zabbix;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//import com.rmyh.report.excel.bean.XnA01Bean;
//import com.rmyh.report.bean.XNDataBean;
//import com.rmyh.report.excel.bean.GjBean;
////import com.rmyh.report.excel.style.XnA01ExportExcelStyle;
////import com.rmyh.report.excel.style.XnA02ExportExcelStyle;
////import com.rmyh.report.excel.style.XnA03ExportExcelStyle;
////import com.rmyh.report.excel.style.XnA04ExportExcelStyle;
////import com.rmyh.report.excel.style.XnA05ExportExcelStyle;
////import com.rmyh.report.excel.style.XnA06ExportExcelStyle;
////import com.rmyh.report.excel.style.XnA07ExportExcelStyle;
//import com.rmyh.report.service.hive.HiveGetData;
//import com.rmyh.report.excel.style.GjtyExportExcelStyle;;
//
//
//public class excel4test {
//	public static void main(String[] args) throws Exception {
////		XnA01ExportExcelStyle A01= new XnA01ExportExcelStyle();
////		XnA02ExportExcelStyle A02= new XnA02ExportExcelStyle();
////		XnA03ExportExcelStyle A03= new XnA03ExportExcelStyle();
////		XnA04ExportExcelStyle A04= new XnA04ExportExcelStyle();
////		XnA05ExportExcelStyle A05= new XnA05ExportExcelStyle();
////		XnA06ExportExcelStyle A06= new XnA06ExportExcelStyle();
////		XnA07ExportExcelStyle A07= new XnA07ExportExcelStyle();
//		GjtyExportExcelStyle Gj= new GjtyExportExcelStyle();
//		HSSFWorkbook xna01 = new HSSFWorkbook();
//		HSSFWorkbook gj = new HSSFWorkbook();
//		
//		List<XnA01Bean> beanList = new ArrayList();
//		List<GjBean> gjBeanList = new ArrayList();	
//		//List<GJDataBean> gjbeanList = new HiveGetData().getGJData();
//		//for GJDataBean.iteator 
//		GjBean gjBean = new GjBean();
//		gjBeanList.add(gjBean);
//		
//		
//		List<XNDataBean> xnbeanList = new HiveGetData().getXNData();
//		for(int i=0;i<xnbeanList.size();i++) {
//			XnA01Bean xnA01Bean = new XnA01Bean();
//			XNDataBean xnBean = xnbeanList.get(i);
//			xnA01Bean.setSbName(xnBean.getHostName());
//			xnA01Bean.setJkObject(xnBean.getApplicationName());
//			xnA01Bean.setIpAddress(xnBean.getHostName());
//			xnA01Bean.setMax(xnBean.getValue_max());
//			xnA01Bean.setMin(xnBean.getValue_min());
//			xnA01Bean.setAvg(xnBean.getValue_avg());
//			xnA01Bean.setJkxName(xnBean.getItemName());
//			beanList.add(xnA01Bean);
//			}
//		
////		Gj.Export(gj, "2012", "2019", gjBeanList);
////		A01.Export(xna01,"2012", "2018", "设备名称(5)","监控项名(2047)","严重告警次数(2)","主要告警次数(0)","次要告警次数(1)",beanList);
////		A02.Export(xn,"2012", "2018", "设备名称(5)","监控项名(2047)","严重告警次数(2)","主要告警次数(0)","次要告警次数(1)",beanList);
////		A03.Export(xn,"2012", "2018", "设备名称(5)","监控项名(2047)","严重告警次数(2)","主要告警次数(0)","次要告警次数(1)",beanList);
////		A04.Export(xn,"2012", "2018", "设备名称(5)","监控项名(2047)","严重告警次数(2)","主要告警次数(0)","次要告警次数(1)",beanList);
////		A05.Export(xn,"2012", "2018", "IP地址(7)","监控项名(182)","严重告警次数(2)","主要告警次数(0)","次要告警次数(1)",beanList);
////		A06.Export(xn,"2012", "2018", "设备名称(5)","监控项名(2047)","严重告警次数(2)","主要告警次数(0)","次要告警次数(1)",beanList);
////		A07.Export(xn,"2012", "2018", "SERVER(20)","监控对象(118)","严重告警次数(2)","主要告警次数(0)","次要告警次数(1)",beanList);
//	}
//
//}
