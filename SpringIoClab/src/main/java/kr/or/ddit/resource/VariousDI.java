package kr.or.ddit.resource;

import org.springframework.core.io.Resource;

import lombok.Data;

@Data
public class VariousDI {

	private int number;
	private boolean boolData;
	private char ch;
	
	private Resource file;
}
