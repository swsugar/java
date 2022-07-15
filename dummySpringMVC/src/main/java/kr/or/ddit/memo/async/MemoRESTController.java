package kr.or.ddit.memo.async;

import java.util.Collection;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.memo.MemoController;
import kr.or.ddit.memo.MemoVO;
import kr.or.ddit.memo.service.MemoService;

@Controller
@RestController
@RequestMapping(value="/memo", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MemoRESTController {

	private MemoService service;
	
	@GetMapping
	public Collection<MemoVO> list() {
		return memoTable.values();
	}
	
	@PostMapping
	public MemoVO insert(@RequestBody MemoVO memo ) {
		
		memoTable.put(memo.getCode(), memo);
		return memo;
	}
	public  
	
	@PutMapping("{code}")
	   public MemoVO update(
	         @RequestBody MemoVO memo
	         // 주소에서 필요한 값을 찾아내는 것임(putmapping에서 경로 뒤에 붙는 것에 대한 name값을 지정해줘야함) --> 이름을 맞춰주는것이 괜찮음
	         , @PathVariable() int code
	         ) {
	      memo.setCode(code);
	      memoTable.put(code, memo);
	      
	      return memo;
	   }
	@DeleteMapping("{code}")
	public MemoVO delete(@PathVariable int code) {
		return memoTable.remove(code);
	}
}












