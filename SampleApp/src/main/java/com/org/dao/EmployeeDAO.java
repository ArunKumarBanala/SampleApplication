package com.org.dao;

import java.util.List;

import com.org.exception.CustomGenericException;
import com.org.model.Employee;


public interface EmployeeDAO {

	public void addEmployee(Employee employee) throws CustomGenericException;

	public List<Employee> getAllEmployees();

	public void deleteEmployee(Integer employeeId);

	public Employee updateEmployee(Employee employee);

	public Employee getEmployee(int employeeid);

	public List<Employee> getSearchEmployees(String searchString);
}
