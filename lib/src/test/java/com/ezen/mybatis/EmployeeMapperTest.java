package com.ezen.mybatis;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ezen.mybatis.domain.employee.dto.Employee;
import com.ezen.mybatis.domain.employee.mapper.EmployeeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeMapperTest {
	SqlSession sqlSession;	
	
	@BeforeEach
	public void setUp() {
		String resource = "mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@Test
	@Disabled
	public void findAllTest(){
		System.out.println("==================== 전체사원 조회 ========================");
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		log.debug("{}",mapper);
		List<Employee> list = mapper.findAll();
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	@Test
	@Disabled
	public void findByIdTest(){
		log.debug("==================== 사원번호로 사원조회 ========================");
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		Employee employee = mapper.findById(150);
		System.out.println(employee);
	}
	
	@Test
	@Disabled
	public void findByLastNameTest(){
		log.debug("==================== 사원이름으로 사원조회 ========================");
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		List<Employee> findEmployees = mapper.findByLastName("E");
		findEmployees.forEach((employee)->{
			log.debug("검색된 사원: {}", employee);
		});
		
	}
	
	@Test
	@DisplayName("사원 등록")
	public void createTest(){
		//given
		Employee newEmployee = new Employee();
		newEmployee.setFirstName("KiJung");
		newEmployee.setLastName("Kim");
		newEmployee.setEmail("kimkijung2@gmail.com");
		newEmployee.setPhoneNumber("010.9179.87087");
		newEmployee.setHireDate("2023-01-01");
		newEmployee.setJobId("IT_PROG");
		newEmployee.setSalary(50000);
		newEmployee.setManagerId(150);
		newEmployee.setDepartmentId(60);
		
		//when
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);		
		
		//then
		mapper.create(newEmployee);
		log.debug("등록된 사원 : {}", newEmployee);
	}
	
	
	@AfterEach
	public void destory() {
		sqlSession.close();
	}
}







