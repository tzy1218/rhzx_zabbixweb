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
import com.rmyh.report.bean.XNDataBean;
import com.rmyh.report.excel.bean.XnBean;
import com.rmyh.report.excel.bean.ZbBean;
import com.rmyh.report.excel.style.XnExportExcel;
import com.rmyh.report.excel.style.ZbExportExcel;
import com.rmyh.report.service.Dataquery;

@WebServlet(asyncSupported = true, urlPatterns = { "/excel/getPerfExcel" })
public class getPerfExcel extends HttpServlet {
	public getPerfExcel() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 输出流
		PrintWriter out = null;
		// 获得请求文件名
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		SimpleDateFormat dataFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");

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
		String filename = "Perf" + startTime + endTime + ".xls";
		List<XNDataBean> XNDataBeanList = null;
			try {
				XNDataBeanList = new Dataquery().getXNDataByWTime(startTimeL, endTimeL);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Collections.sort(XNDataBeanList);
		List<List<XnBean>> resBean = new ArrayList();
		XnExportExcel export = new XnExportExcel();
		List<XnBean> tmpBean = new ArrayList();

		for (int i = 0, groupIdPre = 0; i < XNDataBeanList.size(); i++) {
			XNDataBean bean = XNDataBeanList.get(i);
			if (bean.getGroupId() != groupIdPre) {
				if (i != 0) {
					resBean.add(tmpBean);
				}
				tmpBean = new ArrayList();
				XnBean xnBean = new XnBean();
				xnBean.setSbName(bean.getHostName());
//				xnBean.setIpAddress(bean.getHostIp());
				xnBean.setJkObject(bean.getApplicationName());
				xnBean.setJkxName(bean.getItemName());
				xnBean.setIpAddress(bean.getHostIp());
				xnBean.setAvg(bean.getValue_avg());
				xnBean.setMax(bean.getValue_max());
				xnBean.setMin(bean.getValue_min());
//				xnBean.setGjfzbds(bean.getTriggerText());
//				xnBean.setGroupName(bean.getGroupName());
//				xnBean.setState(bean.getStatus());
//				xnBean.setGjdj(bean.getPriority());
				xnBean.setGroupName(bean.getGroupName());
				tmpBean.add(xnBean);

				groupIdPre = bean.getGroupId();

			} else {

				XnBean xnBean = new XnBean();
				xnBean.setSbName(bean.getHostName());
//				xnBean.setIpAddress(bean.getHostIp());
				xnBean.setJkObject(bean.getApplicationName());
				xnBean.setJkxName(bean.getItemName());
				xnBean.setIpAddress(bean.getHostIp());
				xnBean.setAvg(bean.getValue_avg());
				xnBean.setMax(bean.getValue_max());
				xnBean.setMin(bean.getValue_min());
				xnBean.setGroupName(bean.getGroupName());
				
//				xnBean.setGjfzbds(bean.getTriggerText());
//				xnBean.setGroupName(bean.getGroupName());
//				xnBean.setState(bean.getStatus());
//				xnBean.setGjdj(bean.getPriority());
				tmpBean.add(xnBean);
				if (i == XNDataBeanList.size()-1) {
					resBean.add(tmpBean);

				}

			}
		}
		System.out.println("resBean:" + resBean);
		export.Export(startTime, endTime, resBean);

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
