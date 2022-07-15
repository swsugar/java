package kr.or.ddit.collection.properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesTestView {
	public static void main(String[] args) {
//		System.getenv().forEach((name,value)->{
//			log.info("env_name : {}, env_value :{]", name,value);
//		});  //os의 환경변수를 가지고 있음
		System.getProperties().forEach((name,value)->{
			
			log.info("env_name : {}, env_value :{]", name,value);
			
		});
//		ConfigurableApplicationContext context = 
//				new ClassPathXmlApplicationContext("kr/or/ddit/collection/properties/conf/Properties-Context.xml");
//		context.registerShutdownHook();
//		DBInfoVO infoVO2 = context.getBean("infoVO2",DBInfoVO.class);
//		log.info("infoVO2 : {}", infoVO2);
	}

}
