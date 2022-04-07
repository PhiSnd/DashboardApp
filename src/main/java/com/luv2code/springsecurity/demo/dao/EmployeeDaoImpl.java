package com.luv2code.springsecurity.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springsecurity.demo.entity.Employee;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	
	//project 21 (hibernate-with-service-demo) AS EXAMPLE !!
	private EntityManager entityManager;
	
	
	//set up constructor injection
	@Autowired
	public EmployeeDaoImpl(EntityManager theEntityManager) { //EntityManager automatically created bei Spring Boot
		entityManager = theEntityManager;
	}
	
	
	
	@Override
	public List<Employee> findAll() {
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee",Employee.class);
		
		
		//execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		//return the result
		return employees;
	}

	
	
	
	@Override
	public Employee findById(int id) {
		
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//OLD CODE -------------------------------------------------------------------------------------
		//now retrieve/read from database using id
		/*
		 * Query<Employee> theQuery =
		 * currentSession.createQuery("from Employee where id=:uid", Employee.class);
		 * theQuery.setParameter("uid", theQuery); Employee theEmployee = null; try {
		 * theEmployee = theQuery.getSingleResult(); }catch(Exception e) { theEmployee =
		 * null; }
		 */
		//----------------------------------------------------------------------------------------------------
		//get the employee 
		Employee theEmployee = currentSession.get(Employee.class, id);
		
		//return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create Employee
		currentSession.saveOrUpdate(theEmployee);

	}

	@Override
	public void deleteById(int id) {
		//get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//delete the employee with primary key
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", id);
		
		theQuery.executeUpdate();
	}

}
