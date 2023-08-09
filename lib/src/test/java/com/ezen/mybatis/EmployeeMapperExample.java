package com.ezen.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ezen.mybatis.domain.employee.dto.Employee;
import com.ezen.mybatis.domain.employee.mapper.EmployeeMapper;


public class EmployeeMapperExample {
	
	private static final String namespace = "com.ezen.mybatis.domain.employee.mapper.EmployeeMapper";
	
	public static void main(String[] args) {
		String resource = "mybatis-config.xml";
		
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		// 트랜잭션 Auto Commit
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// Not Auto Commit
		//SqlSession sqlSession = sqlSessionFactory.openSession(false);
		//sqlSession.commit();
		//sqlSession.rollback();
		
		
		System.out.println("==================== sqlSession 생성 완료 ====================");
		
		System.out.println("==================== 전체사원 조회 ========================");
		//List<Employee> employeeList = sqlSession.selectList(namespace + ".findAll");
		EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
		List<Employee> employeeList= employeeMapper.findAll();
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}
		
		System.out.println("==================== 사원번호로 사원조회 ========================");
		int id = 200;
		Employee employee = employeeMapper.findById(id);
		System.out.println(employee);
		
	}
	
}











