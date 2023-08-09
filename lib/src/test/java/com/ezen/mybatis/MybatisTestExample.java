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


public class MybatisTestExample {
	
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
		//SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "development");
		//SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "product");
		
		// 트랜잭션 Auto Commit
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		// Not Auto Commit
		//SqlSession sqlSession = sqlSessionFactory.openSession(false);
		//sqlSession.commit();
		//sqlSession.rollback();
		
		
		System.out.println("==================== sqlSession 생성 완료 ====================");
		
		String today = sqlSession.selectOne(namespace + ".findToday");
		System.out.println(today);
		
		System.out.println("==================== 전체사원 조회 ========================");
		//List<Employee> employeeList = sqlSession.selectList("com.ezen.mybatis.domain.employee.mapper.EmployeeMapper.findAll");
		List<Employee> employeeList = sqlSession.selectList(namespace + ".findAll");
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}
		
		System.out.println("==================== 사원번호로 사원조회 ========================");
		int id = 200;
		Employee employee = sqlSession.selectOne(namespace + ".findById", id);
		System.out.println(employee);
		
		System.out.println("==================== 사원 급여조회 ========================");
		int salary = sqlSession.selectOne(namespace + ".findBySalary", id);
		System.out.println("받는 급여 : " + salary);
		
		System.out.println("==================== 급여범위로 사원검색 ========================");
		Map<String, Integer> params = new HashMap<>();
		params.put("min", 10000);
		params.put("max", 100000);
		List<Employee> list = sqlSession.selectList(namespace + ".findBySalaryRange", params);
		for (Employee emp : list) {
			System.out.println(emp);
		}
		
		
		System.out.println("==================== 성으로 사원검색 ========================");
		String searchName = "e";
		searchName = searchName.toUpperCase(); // E(e)가 포함된 모든 성
		List<Employee> list2 = sqlSession.selectList(namespace + ".findByLastName", searchName);
		for (Employee emp : list2) {
			System.out.println(emp);
		}
		
		
		System.out.println("==================== 부서명 포함 전체사원 조회(테이블 조인) ========================");
		List<Map<String, Object>> list3 = sqlSession.selectList(namespace + ".findByJoin");
		for (Map<String, Object> row : list3) {
			BigDecimal empId = (BigDecimal) row.get("id");
			String firstName = (String) row.get("firstName");
			String lastName = (String) row.get("lastName");
			String departmentName = (String) row.get("departmentName");
			System.out.println(empId + "\t" + firstName + "\t" + lastName + "\t" + departmentName);
		}
		
		
		System.out.println("==================== ResultMap을 이용한 전체사원 조회 ========================");
		List<Employee> list4 = sqlSession.selectList(namespace + ".findByAll2");
		for (Employee emp : list4) {
			System.out.println(emp);
		}
		
		System.out.println("==================== 사원 등록 ========================");
		Employee emp = new Employee();
		emp.setFirstName("KiJung");
		emp.setLastName("Kim");
		emp.setEmail("kimkijung@gmail.com");
		emp.setPhoneNumber("010.9179.87087");
		emp.setHireDate("2023-01-01");
		emp.setJobId("IT_PROG");
		emp.setSalary(50000);
		emp.setManagerId(150);
		emp.setDepartmentId(60);

//		sqlSession.insert(namespace + ".create", emp);
//		sqlSession.commit();
		//sqlSession.rollback();
//		System.out.println("신규사원 등록 완료");
		
		Employee updateEmp = new Employee();
		updateEmp.setId(200);
//		updateEmp.setFirstName("볶이");
		updateEmp.setLastName("라");
		updateEmp.setSalary(10000);
		sqlSession.update(namespace + ".update2", updateEmp);
		sqlSession.commit();
		System.out.println("사원정보 수정 완료");
		
//		삭제시 사용하는 메소드
//		sqlSession.delete(searchName);
		
		
		System.out.println("==================== 검색타입별 사원 검색(동적SQL 활용) ========================");
		Map<String, Object> searchParams = new HashMap<String, Object>();
//		searchParams.put("type", "id");
//		searchParams.put("value", 150);

		searchParams.put("type", "name");
		searchParams.put("value", "e");

		List<Employee> searchList = sqlSession.selectList(namespace + ".search", searchParams);
		for (Employee searchEmp : searchList) {
			System.out.println(searchEmp);
		}
		
	}
	
}











