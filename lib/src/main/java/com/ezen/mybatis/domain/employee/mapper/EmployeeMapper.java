package com.ezen.mybatis.domain.employee.mapper;

import java.util.List;

import com.ezen.mybatis.domain.employee.dto.Employee;

/**
 * Mybatis Mapper 인터페이스
 */
public interface EmployeeMapper {

	/** 사원 전체 목록 조회 */
	public List<Employee> findAll();

	/** 사원번호로 사원 조회 */
	public Employee findById(int id);
	
	/** 성으로 사원 조회*/
	public List<Employee> findByLastName(String lastName);

	/** 사원 등록 */
	public void create(Employee employee);
	

}
