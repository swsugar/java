package kr.or.ddit.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordUtilsTest {
	String plain = "java";

	@Test
	public void testPasswordUtils() {
		String encoded = PasswordUtils.encryptSha512(plain);
		log.info("평문 : {}, 암호문 : {}", plain, encoded);
	}
	
	@Test
	public void useJasyptPasswordEncrypt() {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		String encoded = encryptor.encryptPassword(plain);
		log.info("평문 : {}, 암호문 : {}", plain, encoded);
	}
	
	@Test
	public void useJasyptAESEncryptor() {
		AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
		textEncryptor.setPassword("java");
		log.info("regno1 : {}, regno2 : {}",  textEncryptor.encrypt("123456"), textEncryptor.encrypt("1234567") );
	}
	
}
