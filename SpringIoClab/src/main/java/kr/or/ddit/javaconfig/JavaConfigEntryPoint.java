package kr.or.ddit.javaconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaConfigEntryPoint {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigForContainer.class);
//		SampleDAO dao1 = context.getBean(SampleDAO.class);
//		SampleDAO dao2 = context.getBean(SampleDAO.class);
//		
//		log.info("dao1==dao2 : {}",dao1==dao2);
		
		SampleService service = context.getBean(SampleService.class);
		log.info("info : {}", service.retriveSampleData("a001"));
	}
}
