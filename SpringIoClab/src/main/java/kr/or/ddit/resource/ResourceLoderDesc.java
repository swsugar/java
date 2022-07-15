package kr.or.ddit.resource;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceLoderDesc {

	public static void main(String[] args) throws URISyntaxException {
		ConfigurableApplicationContext context = 
				 new ClassPathXmlApplicationContext();
//		File file1 = new File("d:/contents/mini.jpg");
//		URL file2 = ResourceLoderDesc.class.getResource("/log4j2.xml");
//	    URI file3 = new URI("https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
	    
	    Resource file1 = context.getResource("file:d:/contents/mini.jpg");
	    Resource file2 = context.getResource("classpath:/log4j2.xml");
	    Resource file3 = context.getResource("https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
	    log.info("file1 : {}", file1.getClass().getName());
	    log.info("file2 : {}", file2.getClass().getName());
	    log.info("file3 : {}", file3.getClass().getName());
	}
}
