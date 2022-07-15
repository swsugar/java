package kr.or.ddit.cal;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * /calculate_version3.do
 *
 */
@Controller
@RequestMapping(value="/calculate_version3.do")
public class CalculateControllerVersion3 {
	@RequestMapping
	public ModelAndView getHandler(){
//		return "cal/calForm";
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cal/calForm");
		Date today = new Date();
		mav.addObject("today", today);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String postHandlerHTML(CalculateVO calVO) {
		return calVO.getExpression();
	}
	
	// produces -> Accept request header, Content-Type respons header
	@RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public CalculateVO postHandlerJSON(@ModelAttribute("calculateVO") CalculateVO calVO) {
		return calVO;
	}
	
//	@RequestMapping(method=RequestMethod.POST)
	public void postHandler_bak(
			@ModelAttribute("calVO") CalculateVO calVO
			, HttpServletResponse resp
	) throws StreamWriteException, DatabindException, IOException{
		resp.setContentType("application/json;charset=UTF-8");
		// marshalling -> serialization
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(resp.getWriter(), calVO);
	}
}




















