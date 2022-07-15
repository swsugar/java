package kr.or.ddit.collection.properties;

import kr.or.ddit.sample.service.SampleService;
import lombok.Data;

@Data
public class DBInfoVO {
	private String driverClassName;
	private Class driverClass;
	private String url;
	private String user;
	private String password;
	private int initialSize;
	private int maxTotal;
	private long maxWait;
	
	private SampleService service;

}
