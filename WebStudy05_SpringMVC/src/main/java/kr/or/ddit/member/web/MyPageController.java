package kr.or.ddit.member.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.CookieValue;
import kr.or.ddit.mvc.annotation.resolvers.SessionAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MyPageController{
	MemberService service = new MemberServiceImpl();
			
	@RequestMapping("/myPage.do")
	public String myPage(
			@SessionAttribute("authMember") MemberVO authMember
			, @CookieValue("JSESSIONID") String jsessionid
			, @CookieValue("JSESSIONID") Cookie sessionCookie
			, HttpServletRequest req
	) {
		log.info("jsessionid : {}, {}", jsessionid, sessionCookie.getValue());
		
		String memId = authMember.getMemId();
		MemberVO member = service.retrieveMember(memId);
		req.setAttribute("member", member);
		return "/member/myPage.tiles";
	}
}
