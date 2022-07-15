package kr.or.ddit.sample.view;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.collection.properties.DBInfoVO;
import kr.or.ddit.sample.controller.sampleController;
import kr.or.ddit.sample.service.SampleService;

public class ApplicationEntryPoint {
	public static void main(String[] args) {
		ConfigurableApplicationContext parentContainer = new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/Parent-Context.xml");
		ConfigurableApplicationContext childContainer1 = new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/sample/conf/child-context.xml"}, parentContainer);
		ConfigurableApplicationContext childContainer2 = new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/collection/properties/conf/Properties-Context.xml"}, parentContainer);
		childContainer1.registerShutdownHook();
		childContainer2.registerShutdownHook();
		parentContainer.registerShutdownHook();
		
		sampleController controller =childContainer1.getBean(sampleController.class);
		SampleView view = childContainer1.getBean(SampleView.class);
		String code="a001";
		Map<String,Object> model = new LinkedHashMap<>();
		controller.commandHandler(model, code);
		view.rendering(model);
		
		SampleService service = childContainer1.getBean(SampleService.class);
		System.err.println(service);
		
		DBInfoVO infoVO1 = childContainer2.getBean("infoVO1", DBInfoVO.class);
		System.err.println(infoVO1.getService());
		
		childContainer1.getBean("infoVO1", DBInfoVO.class);
	}

}
