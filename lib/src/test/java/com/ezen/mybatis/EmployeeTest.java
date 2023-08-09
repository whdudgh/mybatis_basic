package com.ezen.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.ezen.mybatis.domain.employee.dto.Employee;

import lombok.extern.slf4j.Slf4j;

/**
 * JUnit5를 이용해보기
 * @author 조영호
 *
 */
@Slf4j
public class EmployeeTest {
	
	private static final String namespace = "com.ezen.mybatis.domain.employee.mapper.EmployeeMapper";
	private SqlSession sqlSession = null;
	/**
	 * 실행되기전 한번실행되어야해서 BeforeEach
	 */
	@BeforeEach
	void init() {
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
	void selectAll() {
		List<Employee> employeeList = sqlSession.selectList(namespace + ".findAll");
//		log.debug("==== 사원 전체목록 ===");
//		for (Employee employee : employeeList) {
//			log.debug("{}",employee);
//		}
		assertThat(employeeList).isNotNull();
	}
	
	@Test
	void selectByIdTest() {
		//given
		int employeeId = 150;
		//when
		Employee employee = sqlSession.selectOne(namespace + ".findById", employeeId);
		//then
		assertThat(employee).isNotNull();
	}
}
