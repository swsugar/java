package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImpl implements EmployeeDAO {
	SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<EmployeeVO> selectEmployeeList(Integer managerId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			EmployeeDAO mapper = sqlSession.getMapper(EmployeeDAO.class);
			return mapper.selectEmployeeList(managerId);
		}
	}

}
