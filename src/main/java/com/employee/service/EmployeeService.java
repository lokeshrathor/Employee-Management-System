package com.employee.service;

import java.util.List;

import com.employee.model.Employee;

public interface EmployeeService {
	
	public void addEmployee(Employee employee);
	
	public Employee getEmployee(Integer employeeId);
	
    public List<Employee> getAllEmployees();
    
    public void deleteEmployee(Integer employeeId);
    
    public void updateEmployee(Employee employee);

}
