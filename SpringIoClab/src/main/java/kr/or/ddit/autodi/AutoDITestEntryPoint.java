package kr.or.ddit.autodi;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.controller.sampleController;
import kr.or.ddit.sample.view.SampleView;

public class AutoDITestEntryPoint {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent = new ClassPathXmlApplicationContext("kr/or/ddit/OutoDI/conf/Parent-context.xml");
		ConfigurableApplicationContext child = new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/OutoDI/conf/Child-Context.xml"}, parent);
		
		sampleController controller =child.getBean(sampleController.class);
		SampleView view = child.getBean(SampleView.class);
		String code="a001";
		Map<String,Object> model = new LinkedHashMap<>();
		controller.commandHandler(model, code);
		view.rendering(model);
	}
}
