package com.luv2code.springsecurity.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springsecurity.demo.entity.Employee;
import com.luv2code.springsecurity.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	
	private EmployeeService employeeService;
	
	public EmployeeController (EmployeeService theEmployeeService ) {
		employeeService =  theEmployeeService;
	}
	
	@GetMapping("/list")
	public String listEmployees (Model theModel) {
		
		//get employees from db
		List<Employee> theEmployees = employeeService.findAll();
		
		//add to the Spring Model
		theModel.addAttribute("employees",theEmployees);
				
		return "/employees/list-employees";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		//delete the employee
		employeeService.deleteById(theId);
		
		//redirect to /employees/list
		return "redirect:/employees/list";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		//save the Employee
		employeeService.save(theEmployee);
		
		//use a redirect to prevent duplicate submissions
		
		return "redirect:/employees/list";
		}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "/employees/employee-form";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		//get the employee from service
		Employee theEmployee = employeeService.findById(theId);
		
		//set employee as model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		//send over to our form
		return"/employees/employee-form";
	}
	
	
	
	
}
