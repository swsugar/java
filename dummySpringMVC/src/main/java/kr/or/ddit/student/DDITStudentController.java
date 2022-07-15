package kr.or.ddit.student;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.InsertGroup;

@Controller
@RequestMapping("/student")
public class DDITStudentController {
	
	@RequestMapping("resultView.do")
	public String resultView() {
		return "student/resultView";
	}
	
	@RequestMapping(value="regist.do", method=RequestMethod.GET)
	public String getHandler(){
		return "student/registForm";
	}
	
	@RequestMapping(value="regist.do", method=RequestMethod.POST)
	public String postHandler(
			@Validated(InsertGroup.class) @ModelAttribute("student") DDITStudentVO studentVO
			, Errors errors
//			, @RequestPart MultipartFile photo
			, RedirectAttributes redirectAttributes
			, Model model
	) throws IOException{
		
//		studentVO.setPhoto(photo);
//		boolean valid = validata(studentVO);
		boolean valid = !errors.hasErrors();
		
		String message = null;
		String view = null;
		if(valid) {
			
			File saveFolder = new File("d:/contents");
			studentVO.saveTo(saveFolder);
			
			message = "등록 완료";
			redirectAttributes.addFlashAttribute("student", studentVO);
			redirectAttributes.addFlashAttribute("message", message);
			view = "redirect:/student/resultView.do";
		}else {
			message = "등록 실패, 검증 실패";
			model.addAttribute("message", message);
			view = "student/registForm";
		}
		return view;
	}
	
	
	
	@ModelAttribute("gradeMap")
	public Map<String,String[]> makeGradeList() {
		Map<String,String[]> gradeMap = new LinkedHashMap<>();
		gradeMap.put("G001", new String[]{"G001", "고졸"});
		gradeMap.put("G002", new String[]{"G001", "초대졸"});
		gradeMap.put("G003", new String[]{"G001", "대졸"});
		gradeMap.put("G004", new String[]{"G001", "석사"});
		gradeMap.put("G005", new String[]{"G001", "박사"});
		return gradeMap;
	}
	
	@ModelAttribute("licenseMap")
	public Map<String,String[]> makeLicenseList() {
		Map<String,String[]> licenseMap = new LinkedHashMap<>();
		licenseMap.put("L001", new String[]{"L001", "정보처리산업기사"});
		licenseMap.put("L002", new String[]{"L002", "정보처리기사"});
		licenseMap.put("L003", new String[]{"L003", "정보보안산업기사"});
		licenseMap.put("L004", new String[]{"L004", "정보보안기사"});
		licenseMap.put("L005", new String[]{"L005", "SQLD"});
		licenseMap.put("L006", new String[]{"L006", "SQLP"});
		return licenseMap;
	}
}
