package kr.or.ddit.cal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * /calculate_version2.do
 *
 */
@Controller
@RequestMapping(value="/calculate_version2.do")
public class CalculateControllerVersion2 {
	@RequestMapping
	public String getHandler(){
		return "cal/calForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String postHandler(
			@ModelAttribute("calVO") CalculateVO calVO
			, RedirectAttributes redirectAttributes 
	){
//		CalculateVO calVO = new CalculateVO(operand1, operand2, operator);
//		model.addAttribute("calVO", calVO);
//		PRG pattern
		redirectAttributes.addAttribute("test", "324");
		redirectAttributes.addFlashAttribute("calVO", calVO);
		return "redirect:/calculate_version2.do";
	}
}




















