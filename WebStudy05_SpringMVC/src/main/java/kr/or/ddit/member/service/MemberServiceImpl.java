package kr.or.ddit.member.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	MemberDAO memberDao;
	
	@Resource(name="authService")
	AuthenticateService authService;
	
	String appPassword = "java";
	AES256TextEncryptor textEncryptor;
	{
		textEncryptor = new AES256TextEncryptor();
		textEncryptor.setPassword(appPassword);
	}
	

	private void encryptMember(MemberVO member) {
		String plain = member.getMemPass();
		String encoded = new StrongPasswordEncryptor().encryptPassword(plain);
		member.setMemPass(encoded);
		
		String regno1 = member.getMemRegno1();
		String regno2 = member.getMemRegno2();
		String encodedRegno1 = textEncryptor.encrypt(regno1);
		String encodedRegno2 = textEncryptor.encrypt(regno2);
		member.setMemRegno1(encodedRegno1);
		member.setMemRegno2(encodedRegno2);
	}
	
	private void decryptMember(MemberVO member) {
		String encodedRegno1 = member.getMemRegno1();
		String encodedRegno2 = member.getMemRegno2();
		String regno1 = textEncryptor.decrypt(encodedRegno1);
		String regno2 = textEncryptor.decrypt(encodedRegno2);
		member.setMemRegno1(regno1);
		member.setMemRegno2(regno2);
	}
	
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(memberDao.selectMemberForAuth(member)==null) {
			// 개인식별데이터 암호화
			encryptMember(member);
			
			int rowcnt = memberDao.insertMember(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}
	

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) {
		pagingVO.setTotalRecord(memberDao.selectTotalRecord(pagingVO));
		List<MemberVO> memberList = memberDao.selectMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		return memberList;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = memberDao.selectMember(memId);
		// 개인식별데이터 복호화
		decryptMember(member);
		
		if(member==null) {
			throw new PKNotFoundException(String.format("%s 아이디를 가진 회원이 없음.", memId));
		}
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		MemberVO inputData = new MemberVO();
		inputData.setMemId(member.getMemId());
		inputData.setMemPass(member.getMemPass());
		ServiceResult result = authService.authenticate(inputData);
		switch (result) {
		case NOTEXIST:
			throw new PKNotFoundException(String.format("%s 에 해당하는 회원이 없음.", member.getMemId()));
		case INVALIDPASSWORD:
			break;
		default:
			int rowcnt = memberDao.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
			break;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = authService.authenticate(member);
		switch (result) {
		case NOTEXIST:
			throw new PKNotFoundException(String.format("%s 에 해당하는 회원이 없음.", member.getMemId()));
		case INVALIDPASSWORD:
			break;
		default:
			int rowcnt = memberDao.deleteMember(member.getMemId());
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
			break;
		}
		return result;
	}
}














