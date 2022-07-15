package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;


@Controller
public class LoginProcessController{
	
	AuthenticateService service=new AuthenticateServiceImpl();
	
	private boolean validate(MemberVO member) {
		return StringUtils.isNotBlank(member.getMemId()) 
				&& 
			   StringUtils.isNotBlank(member.getMemPass());
	}
	
	@RequestMapping(value="/login/loginProcess.do", method=RequestMethod.POST)
	public String loginProcess(HttpServletRequest req, HttpSession session, HttpServletResponse resp) throws ServletException, IOException {
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		 // 모든 컨트롤러의 첫번째
		MemberVO inputData = new MemberVO();
		inputData.setMemId(req.getParameter("memId"));
		inputData.setMemPass(req.getParameter("memPass"));
//		1. 검증
		boolean valid = validate(inputData);
		String view = null;
		if(valid) {
//		   - 통과
//		   	 2. 처리(로그인 여부 판단)
			ServiceResult result = service.authenticate(inputData);
			if(ServiceResult.OK.equals(result)) {
//				Post-Redirect-Get 패턴
//		   	 	- 로그인 성공 : welcome 페이지로 이동 (redirect)
				MemberVO authMember = inputData;
				session.setAttribute("message", "로그인 성공");
				session.setAttribute("authMember", authMember);
				view = "redirect:/";
			}else {
//		   	 	- 실패 : loginForm 으로 이동(forward)
				String message = null;
				if(ServiceResult.NOTEXIST.equals(result)) {
					message = "회원 가입이 필요함.";
				}else {
					message = "비밀번호 오류.";
				}
				session.setAttribute("message", message);
				view = "redirect:/login/loginForm.jsp";
			}
		}else {
//		   - 불통 : loginForm 으로 이동(forward)
			session.setAttribute("message", "검증 실패");
			view = "redirect:/login/loginForm.jsp";
		}
		
		return view;
	}
}










