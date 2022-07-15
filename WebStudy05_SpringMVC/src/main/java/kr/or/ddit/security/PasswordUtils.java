package kr.or.ddit.security;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtils {
	public static boolean matche(String plain, String savedData){
		String encoded = encryptSha512(plain);
		return savedData.equals(encoded);
	}
	
	public static String encryptSha512(String plain) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] encrypted = md.digest(plain.getBytes());
	 		String encoded = Base64.getEncoder().encodeToString(encrypted);
	 		return encoded;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
