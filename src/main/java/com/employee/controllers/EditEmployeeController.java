package com.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

//indicate that the annotated class is controller
//by default all the class are @component which represent the bean in xml file
@Controller
public class EditEmployeeController {

	@Autowired//tell the compiler where an injecation need to occur
	private EmployeeService employeeService;
	
	/*public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}*/

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(ModelMap map) {
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listEmployees(ModelMap map) {
		
		map.addAttribute("employee", new Employee());
		map.addAttribute("employeeList", employeeService.getAllEmployees());

		return "editEmployeeList";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//serves as a result holder for a database binder
	public String addEmployee(@ModelAttribute(value = "employee") Employee employee, BindingResult result) {
		employeeService.addEmployee(employee);
		//we use redirect when we want to do another request on a particular URL
		//we redirect on url here we are redirecting the request on /list whose method is listEmployees
		//here after performing the add operation we want to show the list
		return "redirect:/list";
	}

	@RequestMapping("/delete/{employeeId}")
	public String deleteEmplyee(@PathVariable("employeeId") Integer employeeId) {
		employeeService.deleteEmployee(employeeId);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/update/{employeeId}", method=RequestMethod.GET)
	//@PathVariable indicate that method parameter should be bound
    public String updateEmplyeePage(@PathVariable("employeeId") Integer employeeId, ModelMap map) {
		Employee employee = employeeService.getEmployee(employeeId);
		map.addAttribute("employee", employee);
        return "updateEmployeeForm";
    }
	
	@RequestMapping(value = "/update/{employeeId}", method=RequestMethod.POST)
	public String updateEmployee(@ModelAttribute(value = "employee") Employee employee, @PathVariable Integer employeeId){
		employeeService.updateEmployee(employee);
		return "redirect:/list";
	}
	

	//match the url from application-security.xml
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		//model object add the supplied attribute to the supplied name
		model.addAttribute("error", "true");
		return "denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}
}
	