package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleView2 {

	public static void main(String[] args) {
//		entry point 에서 컨테이너 객체 생성.
		ConfigurableApplicationContext context = new GenericXmlApplicationContext();
		context.registerShutdownHook();
		SampleService service1 = context.getBean(SampleService.class);
		log.info("service1 : {}",service1);
		SampleService service2 = context.getBean(SampleService.class);
		log.info("service2 : {}",service2);
	}
}
