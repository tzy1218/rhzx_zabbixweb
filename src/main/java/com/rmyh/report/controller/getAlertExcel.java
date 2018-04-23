package com.rmyh.report.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import com.alibaba.fastjson.JSONObject;
import com.rmyh.report.bean.TriggerBean;
import com.rmyh.report.bean.AlertBean;
import com.rmyh.report.excel.bean.GjBean;
import com.rmyh.report.excel.bean.XnBean;
import com.rmyh.report.excel.bean.ZbBean;
import com.rmyh.report.excel.style.GjtyExportExcel;
import com.rmyh.report.excel.style.XnExportExcel;
import com.rmyh.report.excel.style.ZbExportExcel;
import com.rmyh.report.service.Dataquery;

@WebServlet(asyncSupported = true, urlPatterns = { "/excel/getAlertExcel" })
public class getAlertExcel extends HttpServlet {
	public getAlertExcel() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 输出流
		PrintWriter out = null;
		// 获得请求文件名
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		SimpleDateFormat dataFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dataFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date startTimeD = null, endTimeD = null;
		try {
			startTimeD = dataFormat.parse(request.getParameter("starttime"));
			endTimeD = dataFormat.parse(request.getParameter("endtime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long startTimeL = startTimeD.getTime();
		long endTimeL = endTimeD.getTime();
		String startTime = dataFormat2.format(startTimeD);
		String endTime = dataFormat2.format(endTimeD);
		String filename = "Alert" + startTime + endTime + ".xls";
		List<AlertBean> AlertBeanList = new ArrayList();
		List<GjBean> gjBeanList = new ArrayList();
			try {
				AlertBeanList = new Dataquery().getAlertDataByWTime(startTimeL, endTimeL);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(AlertBean bean: AlertBeanList) {
				GjBean gjbean = new GjBean();
				gjbean.setGjTime(dataFormat3.format(new Date(Long.parseLong(bean.getClock()+"000"))));
				gjbean.setConfirmState(bean.getConfirmstatus());
				gjbean.setHostName(bean.getHostName());
				gjbean.setGjContent(bean.getAlertText());
				gjbean.setGjFrequency(String.valueOf(bean.getALertTimes()));
				gjbean.setGjLevel(bean.getAlertLevel());
				gjbean.setIpAddress(bean.getHostIp());
				gjbean.setState(bean.getStatus());
				
				gjBeanList.add(gjbean);
			}
//		Collections.sort(AlertBeanList);
		GjtyExportExcel export = new GjtyExportExcel();

		export.Export(startTime, endTime, gjBeanList);

		// 设置文件MIME类型
		response.setContentType(getServletContext().getMimeType(filename));
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		String fullFileName = "/home/hadoop/Documents/eclipse/" + filename;
		// 获得输入流
		out = response.getWriter();
		out.append(fullFileName);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
