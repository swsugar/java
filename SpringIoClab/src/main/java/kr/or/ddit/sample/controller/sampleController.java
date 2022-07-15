package kr.or.ddit.sample.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
	public class sampleController {
	@Inject
	ApplicationContext context;
	
	@PostConstruct // 모든 의존성 주입 끝난 이후에 동작
	public void init() {
		log.info("{} 객체 초기화", this.getClass());
		log.info("컨테이너 hashcode : {}", context.hashCode());
	}
	
		private SampleService service;
		
	@Inject
	public void setService(SampleService service){
		this.service = service;
	}

		
	public void commandHandler(Map<String, Object> model,String code) {
		StringBuffer info = service.retriveSampleData(code);
		model.put("info",info);
	}
}
