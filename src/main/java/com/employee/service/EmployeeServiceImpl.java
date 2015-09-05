package com.employee.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.employee.dao.EmployeeDAO;
import com.employee.model.Employee;
import com.employee.service.mailServices.MailService;

//indicate that the annotated class is business layer class
@Service
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private MailService mailService;
	
	@Override
	@Transactional//to handle any method in a transaction
	public void addEmployee(Employee employee) {
		employeeDAO.addEmployee(employee);	
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
	
		return employeeDAO.getAllEmployees();
	}

	@Override
	@Transactional
	public void deleteEmployee(Integer employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		try {
			mailService.sendMail(employee.getEmail(), employee.getFirstname(), "Your Record has been updated...");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeDAO.updateEmployee(employee);
		
	}

	@Override
	@Transactional
	public Employee getEmployee(Integer employeeId) {
		return employeeDAO.getEmployee(employeeId);
	}

}
