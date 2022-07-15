package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOTest {
	MemberDAO dao = new MemberDAOImpl();			
	MemberVO member;
	PagingVO<MemberVO> pagingVO;
	@Before
	public void setUp() {
		pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(1);
		member = new MemberVO();
		member.setMemId("b002");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemBir("1999-01-01");
		member.setMemZip("000-000");
		member.setMemAdd1("대전");
		member.setMemAdd2("오류");
		member.setMemHp("000-000-0000");
		member.setMemMail("aa@gmail.net");
	}
	

	@Test
	public void testSelectMemberForAuth() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		MemberVO member = dao.selectMemberForAuth(inputData);
		System.out.println(member);
		assertNotNull(member);
	}

	@Test(expected=RuntimeException.class)
	public void testInsertMemberThrow() {
		MemberVO member = new MemberVO();
		dao.insertMember(member);
	}
	
	@Test
	public void testInsertMember() {
		
		int rowcnt = dao.insertMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList(pagingVO);
		assertNotNull(memberList);
		assertNotEquals(0, memberList.size());
	}
	
	@Test 
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
		System.out.println(member);
		System.out.println(member.getBuyList());
	}

	@Test
	public void testUpdateMember() {
		member.setMemId("a002");
		int rowcnt = dao.updateMember(member);
		assertEquals(1, rowcnt);
	}
	
	@Test
	public void deleteMember() {
		int rowcnt = dao.deleteMember("a001");
		assertEquals(1, rowcnt);
	}
}


















