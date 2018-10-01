package com.org.service;

import java.util.List;

import com.org.exception.CustomGenericException;
import com.org.model.Employee;


public interface EmployeeService {
	
	public void addEmployee(Employee employee) throws CustomGenericException;

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer employeeId);

	public Employee getEmployee(int employeeid);

	public Employee updateEmployee(Employee employee);

	public List<Employee> getSearchEmployees(String searchString);
}
