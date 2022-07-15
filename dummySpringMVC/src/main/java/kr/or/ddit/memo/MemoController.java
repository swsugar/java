package kr.or.ddit.memo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.InsertGroup;

/** 
 * RESTful URI 구조
 *  /memo/write.do (작성) 
 *  /memo/list.do (전체 메모 조회)
 */
@Controller
@RequestMapping("/memo")
public class MemoController {
	
	
	@ModelAttribute("memo")
	public MemoVO memo() {
		return new MemoVO();
	}
	
	// 메모 작성 기능, 작성된 메모 조회
	@GetMapping("write.do")
	public String memoForm(Model model) {
//		model.addAttribute("memo", new MemoVO());
		return "memo/memoForm";
	}
	
	@PostMapping("write.do")
	public String memoWrite(
			@Validated(InsertGroup.class) @ModelAttribute("memo") MemoVO memo
			, Errors errors
	) {
		if(errors.hasErrors()) {
			return "memo/memoForm";
		}else {
			int length = memoTable.size();
			memo.setCode(length+1);
			memoTable.put(memo.getCode(), memo);
//			PRG pattern
			return "redirect:/memo/list.do";
		}
	}
	
	@GetMapping("list.do")
	public ModelAndView memoList() {
		Collection<MemoVO> memoList = memoTable.values();
		ModelAndView mav = new ModelAndView();
		mav.addObject("memoList", memoList); // model.addAttribute
		mav.setViewName("memo/memoList");
		return mav;
	}
}

















