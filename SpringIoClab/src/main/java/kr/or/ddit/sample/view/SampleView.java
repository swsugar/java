package kr.or.ddit.sample.view;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.sample.stereotype.ViewLayer;
import lombok.extern.slf4j.Slf4j;

@ViewLayer
@Slf4j
public class SampleView {
	

		public void rendering(Map<String,Object> model) {
			StringBuffer info = (StringBuffer) model.get("info");
			log.info("모델 : {}",info);
		}
	

}
