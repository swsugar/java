package kr.or.ddit.servlet10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/file/download.do")
public class FileDownloadServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filePath = req.getParameter("file");
		if(StringUtils.isBlank(filePath)) {
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
		
		File file = new File(filePath);
		if(!file.exists()) {
			resp.sendError(404, "해당 파일은 없음.");
			return;
		}
		String filename = file.getName();
		filename = URLEncoder.encode(filename, "UTF-8").replace("+", " ");
		resp.setHeader("Content-Disposition", "attathment;filename=\""+filename+"\"");
		resp.setContentLengthLong(file.length());
		
		// commons-compress : 폴더를 압축.
//		FileInputStream fis = new FileInputStream(file);
		try(
			OutputStream os = resp.getOutputStream();
		){
			FileUtils.copyFile(file, os);
		}
	}
}














