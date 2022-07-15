package kr.or.ddit.servlet08;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class Model1AlterController{
	/**
	 * enum은 그 자체로 immutable object 의미를 포함함.
	 *
	 */
	public static enum ServiceType{
		FACTORIAL("/04/factorial.jsp"), CALENDAR("/06/calendar.jsp");
		
		private String jspPath;

		private ServiceType(String jspPath) {
			this.jspPath = jspPath;
		}
		
		public String getJspPath() {
			return jspPath;
		}
		
	}
	 
	@RequestMapping("/model1/service.do")
	public String alterGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 String cmdParam = req.getParameter("command");
		 if(StringUtils.isBlank(cmdParam)) {
			 resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락");
			 return null;
		 }
		 
		 try {
			 ServiceType command = ServiceType.valueOf(cmdParam);
			 String contents = command.getJspPath();
			 return "/model1"+contents+".tiles";
		 }catch (IllegalArgumentException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "제공하지 않는 서비스임");
			return null;
		}
	}
	
	@RequestMapping(value="/model1/service.do", method=RequestMethod.POST)
	public String alterPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return alterGet(req, resp);
	}
}



























