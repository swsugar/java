package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.DelegatingViewResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/file/upload_3.do")
@MultipartConfig(maxFileSize=-1, maxRequestSize=-1)
public class FileUploadServletSpec3 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");
		Part fileSystemPart = req.getPart("fileSystemResource");
		Part webResourcePart = req.getPart("webResource");
		log.info("uploader : {}", uploader);
		log.info("fileSystemResource : {}", fileSystemPart);
		log.info("webResource : {}", webResourcePart);

		// 저장위치
		File fileSystemFolder = new File("d:/contents");
		String webPath = "/resources/images";
		String fileSystemPath = getServletContext().getRealPath(webPath);
		File webResourceFolder = new File(fileSystemPath);
		
		List<String> savePathList = new ArrayList<>();
		req.getSession().setAttribute("savePathList", savePathList);
		String savePath = null;
		
		String saveName = uploadFile(fileSystemPart, fileSystemFolder);
		savePath = new File(fileSystemFolder, saveName).getAbsolutePath();
		savePathList.add(savePath);
		if(webResourcePart.getContentType().startsWith("image/")) {
			saveName = uploadFile(webResourcePart, webResourceFolder);
			savePath = webPath + "/" +saveName;
		}
		savePathList.add(savePath);
		new DelegatingViewResolver().viewResolve("redirect:/15/fileUploadForm.jsp", req, resp);
	}
	
	private String uploadFile(Part part, File saveFolder) throws IOException {
		String orginalFilename = part.getSubmittedFileName();
		String saveName = UUID.randomUUID().toString();
		try(
			InputStream is = part.getInputStream();
		){
			File dest = new File(saveFolder, saveName);
			FileUtils.copyInputStreamToFile(is, dest);
			return saveName;
		}
	}
}








