package com.luv2code.springsecurity.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springsecurity.demo.dao.EmployeeDao;
import com.luv2code.springsecurity.demo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	//project 21 (hibernate-with-service-demo)

	@Autowired
	private EmployeeDao employeeDao;
	
	public EmployeeServiceImpl() {
		
	}
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDao theEmployeeDao) {
		employeeDao = theEmployeeDao;
	}
	

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		
		return employeeDao.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		
		return employeeDao.findById(id);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		
		 employeeDao.save(employee);

	}

	@Override
	@Transactional
	public void deleteById(int id) {
		
		employeeDao.deleteById(id);

	}

	@Override
	public List<Employee> searchBy(String theName) {
		
		return null;
	}

}
