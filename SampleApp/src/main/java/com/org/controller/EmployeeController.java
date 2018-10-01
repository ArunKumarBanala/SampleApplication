package com.org.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.org.exception.CustomGenericException;
import com.org.model.Employee;
import com.org.service.EmployeeService;

@Controller
public class EmployeeController {

	private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

	public EmployeeController() {
		System.out.println("EmployeeController()");
	}

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		LOGGER.info("Employee Controller listEmployee()  STARTED");
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("home");
		LOGGER.info("Employee Controller listEmployee()  END");
		return model;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchEmp(@RequestParam("empName") String searchString, ModelAndView model) {
		LOGGER.info("Employee Controller searchEmp()  STARTED");
		List<Employee> listEmployee = employeeService.getSearchEmployees(searchString);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("home");
		LOGGER.info("Employee Controller searchEmp()  END");
		return model;
	}

	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Employee employee = new Employee();
		model.addObject("employee", employee);
		model.setViewName("EmployeeForm");
		return model;
	}

	@RequestMapping(value = "/searchEmployee", method = RequestMethod.GET)
	public ModelAndView searchContact(ModelAndView model) {
		model.setViewName("SearchEmployeeForm");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) throws CustomGenericException {
		if (employee.getId() == 0) { // if employee id is 0 then creating the
			// employee other updating the employee
			if (employee.getEmail() == "" || employee.getEmail().length() == 0)
				employee.setEmail(null);
			employeeService.addEmployee(employee);
		} else {
			employeeService.updateEmployee(employee);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		employeeService.deleteEmployee(employeeId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(employeeId);
		ModelAndView model = new ModelAndView("EmployeeForm");
		model.addObject("employee", employee);

		return model;
	}

	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {

		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;

	}

}