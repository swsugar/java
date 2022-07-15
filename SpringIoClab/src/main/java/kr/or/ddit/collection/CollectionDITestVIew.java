package kr.or.ddit.collection;
import java.util.Arrays;
import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CollectionDITestVIew {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/collection/conf/CollectionDI-context.xml");
		context.registerShutdownHook();
		CollectionDIVO colVO1 = context.getBean("colVO1",CollectionDIVO.class);
		Arrays.stream(colVO1.getArray()).forEach((element)->{
			log.info("element value : {}",element);
		});
		colVO1.getList().forEach((element)->{
			log.info("element value: {}",element);
		});
		colVO1.getSet().forEach((element)->{
			log.info("element value: {}",element);
		});
		colVO1.getMap().forEach((key,value)->{
			log.info("key : {}, value: {}",key,value);
		});
		colVO1.getProps().forEach((key,value)->{
			log.info("key : {}, value: {}",key,value);
		});
		
		Date now = context.getBean("now", Date.class);
		log.info("now : {}", now); 
	}
}
