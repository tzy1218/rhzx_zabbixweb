package com.rmyh.report.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import com.rmyh.report.bean.TriggerBean;
import com.rmyh.report.excel.bean.ZbBean;
import com.rmyh.report.excel.style.ZbExportExcel;
import com.rmyh.report.service.Dataquery;

@WebServlet(asyncSupported = true, urlPatterns = { "/excel/getMonitExcel" })
public class getMonitExcel extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public getMonitExcel() {
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
		String filename = "Monit" + startTime + endTime + ".xls";

		List<TriggerBean> triggerBean = null;
		try {
			triggerBean = new Dataquery().getAllTriggers_App(startTimeL, endTimeL);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(triggerBean);
		List<List<ZbBean>> resBean = new ArrayList<List<ZbBean>>();
		ZbExportExcel export = new ZbExportExcel();
		List<ZbBean> tmpBean = new ArrayList<ZbBean>();

		for (int i = 0, groupIdPre = 0; i < triggerBean.size(); i++) {
			TriggerBean bean = triggerBean.get(i);
			if (bean.getGroupId() != groupIdPre) {
				if (i != 0) {
					resBean.add(tmpBean);
				}
				tmpBean = new ArrayList<ZbBean>();
				ZbBean zbBean = new ZbBean();
				zbBean.setSbName(bean.getHostName());
				zbBean.setIpAddress(bean.getHostIp());
				zbBean.setJkObject(bean.getApplicationName());
				zbBean.setJkxName(bean.getTriggerDescri());
				zbBean.setGjfzbds(bean.getTriggerText());
				zbBean.setGroupName(bean.getGroupName());
				zbBean.setState(bean.getStatus());
				zbBean.setGjdj(bean.getPriority());
				tmpBean.add(zbBean);

				groupIdPre = bean.getGroupId();

			} else {

				ZbBean zbBean = new ZbBean();
				zbBean.setSbName(bean.getHostName());
				zbBean.setIpAddress(bean.getHostIp());
				zbBean.setJkObject(bean.getApplicationName());
				zbBean.setJkxName(bean.getTriggerDescri());
				zbBean.setGjfzbds(bean.getTriggerText());
				zbBean.setGroupName(bean.getGroupName());
				zbBean.setState(bean.getStatus());
				zbBean.setGjdj(bean.getPriority());
				tmpBean.add(zbBean);
				if (i == triggerBean.size() - 1) {
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
