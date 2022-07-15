package kr.or.ddit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * /sample/test.do 요청에 대한 처리.
 * post 요청이 발생하면, 시스템 콘솔에 사용자의 입력데이터를 출력하고,
 * 입력데이터들을 가지고, sampleForm 으로 이동(명령 처리 완료됨을 가정함).
 *
 */
@Controller
public class SampleController {
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping(value="/sample/test.do", method=RequestMethod.GET)
	public String getHandler(){
		return "sample/sampleForm";
	}
	@RequestMapping(value="/sample/test.do", method=RequestMethod.POST)
	public String postHandler(
			@RequestParam("param1") String text
			, @RequestParam(required=false, defaultValue="1") int param2
//			, HttpSession session
//			, Model model
			, RedirectAttributes redirectAttributes
	){
		log.info("param1 : {}, param2 : {}", text, param2);
//		model.addAttribute("param1", text);
//		model.addAttribute("param2", param2);
		
		redirectAttributes.addFlashAttribute("param1", text);
		redirectAttributes.addFlashAttribute("param2", param2);
//		PRG pattern
		return "redirect:/sample/test.do";
//		return "sample/sampleForm";
	}
}













