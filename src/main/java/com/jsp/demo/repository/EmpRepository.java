package com.jsp.demo.repository;


	import org.springframework.data.jpa.repository.JpaRepository;

	import com.jsp.demo.entity.Employee;



	public interface EmpRepository extends JpaRepository<Employee, Integer> {

	}