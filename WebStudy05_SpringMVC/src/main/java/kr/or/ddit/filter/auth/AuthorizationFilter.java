package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

/**
 * 보호자원을 요청하고 있는 인증된 사용자의 인가 여부 확인.
 *
 */
public class AuthorizationFilter implements Filter{

	private ServletContext application;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		application = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, String[]> securedResources = (Map<String, String[]>) 
												application.getAttribute(AuthenticateFilter.SECUREDRESOURCENAME);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
//		/member/memberList.do;jsessionid=asdfasdf
		uri = uri.split(";")[0];
		boolean secureFlag = securedResources.containsKey(uri);
		boolean pass = false;
		if(secureFlag) {
			MemberVOWrapper wrapperPrincipal = (MemberVOWrapper) req.getUserPrincipal();
			MemberVO authMember = wrapperPrincipal.getRealMember();
			String role = authMember.getMemRole();
			String[] roles = securedResources.get(uri);
			
			int searched = Arrays.binarySearch(roles, role);
			if(searched<0) {
				pass = false;
			}else {
				pass = true;
			}
			
		}else {
			pass = true;
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "권한 없음.");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}












