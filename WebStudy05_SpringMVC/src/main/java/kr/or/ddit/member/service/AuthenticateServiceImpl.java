package kr.or.ddit.member.service;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;

@Service("authService")
public class AuthenticateServiceImpl implements AuthenticateService {
	@Inject
	MemberDAO memberDAO;
	
	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		ServiceResult result = null;
		MemberVO member = memberDAO.selectMemberForAuth(inputData);
		if(member!=null) {
			String inputPass = inputData.getMemPass();
			String savedPass = member.getMemPass();
//			if(PasswordUtils.matche(inputPass, savedPass)) {
			if((new StrongPasswordEncryptor()).checkPassword(inputPass, savedPass)) {
				try {
					BeanUtils.copyProperties(inputData, member);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}
