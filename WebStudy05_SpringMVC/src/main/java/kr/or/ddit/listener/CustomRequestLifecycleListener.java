package kr.or.ddit.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class CustomRequestLifecycleListener implements ServletRequestListener {
	public static final String STARTATTR = "CustomRequestLifecycleListener.start";

	public void requestInitialized(ServletRequestEvent sre)  { 
		ServletRequest request = sre.getServletRequest();
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.printf("%s 의 요청 발생\n", req.getRemoteAddr());
		long start = System.currentTimeMillis();
		req.setAttribute(STARTATTR, new Long( start ));
	}

    public void requestDestroyed(ServletRequestEvent sre)  { 
    	ServletRequest request = sre.getServletRequest();
		HttpServletRequest req = (HttpServletRequest) request;
    	long end = System.currentTimeMillis();
    	long start = ((Long) req.getAttribute(STARTATTR)).longValue();
    	System.out.printf("소요 시간 : %d\n", (end-start));
    }
}
