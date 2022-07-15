package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.util.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/file/upload.do")
public class FileUploadServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 저장위치
		File fileSystemFolder = new File("d:/contents");
		String webPath = "/resources/images";
		String fileSystemPath = getServletContext().getRealPath(webPath);
		File webResourceFolder = new File(fileSystemPath);
		
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(itemFactory);
		try {
			List<FileItem> itemList = uploadHandler.parseRequest(req);
			if(itemList!=null && !itemList.isEmpty()) {
				List<String> savePathList = new ArrayList<>();
				for(FileItem item : itemList) {
					String partName = item.getFieldName();
					if(item.isFormField()) {
						String encoding = req.getCharacterEncoding();
						String partValue = item.getString(encoding);
						log.info("파트명 : {}, 파트값 : {}", partName, partValue);
					}else {
						String savePath = null;
						if(partName.startsWith("fileSystem")) {
							String saveName = uploadFile(item, fileSystemFolder);
							savePath = new File(fileSystemFolder, saveName).getAbsolutePath();
						}else if(partName.startsWith("web")) {
							if(item.getContentType().startsWith("image/")) {
								String saveName = uploadFile(item, webResourceFolder);
								savePath = webPath + "/" +saveName;
							}
						}
						savePathList.add(savePath);
					} // if(item.isFormField()) end
				}// for end
				req.getSession().setAttribute("savePathList", savePathList);
			}// if(itemList!=null && !itemList.isEmpty()) end
			
			resp.sendRedirect(req.getContextPath() + "/15/fileUploadForm.jsp");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String uploadFile(FileItem item, File saveFolder) throws IOException {
		String orginalFilename = item.getName();
		String saveName = UUID.randomUUID().toString();
		try(
			InputStream is = item.getInputStream();
		){
			File dest = new File(saveFolder, saveName);
			FileUtils.copyInputStreamToFile(is, dest);
			return saveName;
		}
	}
}
















