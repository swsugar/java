package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {
	ProdDAO dao = new ProdDAOImpl();
	
	@Test
	public void testInsertProd() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectProdList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		assertNotNull(prod);
		System.out.println(prod);
		System.out.println(prod.getBuyer()); // 1:1
		System.out.println(prod.getMemberSet()); // 1:N
	}

	@Test
	public void testUpdateProd() {
		fail("Not yet implemented");
	}

}











