package com.employee.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository//used for dao class for identification on mapping
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired//automatic inject the property using annotation
	private SessionFactory sessionFactory;
	
	@Override
	public void addEmployee(Employee employee) {
		this.sessionFactory.getCurrentSession().save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.sessionFactory.getCurrentSession().createQuery("from Employee").list();
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		//load method return the persistent object means the object in session
		//we can also use the get method, get method hit the database everytime you need the record
		//load method does not hit the database every time it returns a proxy object. when you need 
		Employee employee = (Employee) this.sessionFactory.getCurrentSession().load(Employee.class, employeeId);
		if (null != employee) {
        	this.sessionFactory.getCurrentSession().delete(employee);
        }
	}

	@Override
	public void updateEmployee(Employee employee) {
		//get the previous record of employee
		Employee empToUpdate = getEmployee(employee.getId());
		empToUpdate.setFirstname(employee.getFirstname());
		empToUpdate.setLastname(employee.getLastname());
		empToUpdate.setEmail(employee.getEmail());
		empToUpdate.setTelephone(employee.getTelephone());
		this.sessionFactory.getCurrentSession().update(empToUpdate);
		System.out.println("Employee "+employee.getId()+" Updated Successfully.....");
		
	}

	@Override
	public Employee getEmployee(Integer employeeId) {
		Employee employee = (Employee)this.sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		return employee;
	}
}
