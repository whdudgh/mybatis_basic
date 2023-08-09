package com.ezen.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ezen.mybatis.domain.employee.dto.Employee;
import com.ezen.mybatis.domain.employee.mapper.EmployeeMapper2;

public class AnnotationEmployeeMapperExample {
	
	public static void main(String[] args) {
		
		String resource = "mybatis-config.xml";
		
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		System.out.println("==================== 전체사원 조회 ========================");
		EmployeeMapper2 mapper = sqlSession.getMapper(EmployeeMapper2.class);
		List<Employee> list = mapper.findAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}
		
		System.out.println("==================== 사원번호로 사원조회 ========================");
		Employee employee = mapper.findById(150);
		System.out.println(employee);
	}
}







