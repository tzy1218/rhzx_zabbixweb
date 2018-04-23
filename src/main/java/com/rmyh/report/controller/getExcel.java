package com.rmyh.report.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;


@WebServlet(asyncSupported = true, urlPatterns = { "/excel/getExcel" })
public class getExcel extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public getExcel() {
		super(); 
	}

	/*
	 * 处理终端下载文件请求
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 输入流
		InputStream in = null;
		// 输出流
		OutputStream out = null;

		// 获得请求文件名
		String fullFileName = request.getParameter("fileName");
		String fileName = fullFileName.substring((Integer)(fullFileName.lastIndexOf("/")+1));
		try {

			// 设置文件MIME类型
			response.setContentType(getServletContext().getMimeType(fileName));
//			response.setContentType("application/vnd.ms-excel");
			
			// 设置Content-Disposition
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		
			// 读取目标文件，通过response将目标文件写到客户端
			// 获得输入流
			in = new FileInputStream(fullFileName);
			// 获得输出流
			out = response.getOutputStream();
			// 写文件
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
		} finally {
			// 关闭输入输出流
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

