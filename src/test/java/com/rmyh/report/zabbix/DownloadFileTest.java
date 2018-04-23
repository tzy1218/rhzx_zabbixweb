package com.rmyh.report.zabbix;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownloadFileTest {
	
	public static void main(String[] args) {
	}
	
	public static void main11(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "201804010800201804040801.xls";
		OutputStream out = null;
		FileInputStream fis = null;
		// 1.获取资源文件的路径，当文件名是中文的时候出现不正常的情况，所以需要进行url编码
		String path = System.getProperty("user.dir") +"/"+ fileName;
		try {
			// 2.根据获取到的路径，构建文件流对象
			fis = new FileInputStream(path);
			out = response.getOutputStream();
			// 3.设置让浏览器不进行缓存，不然会发现下载功能在opera和firefox里面好好的没问题，在IE下面就是不行，就是找不到文件
			response.setContentType("application/octet-stream");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", -1);
			// 4.设置Content-type字段
			// 5.设置http响应头，告诉浏览器以下载的方式处理我们的响应信息
			response.setHeader("content-disposition", "attachment;filename=\"" + fileName+"\"");
			// 6.开始写文件
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
		} finally {
			if (fis != null) {
				fis.close();
			}

		}
	}

}
