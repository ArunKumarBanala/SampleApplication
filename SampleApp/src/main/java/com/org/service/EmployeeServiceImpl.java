package com.org.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.dao.EmployeeDAO;
import com.org.exception.CustomGenericException;
import com.org.model.Employee;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public void addEmployee(Employee employee) throws CustomGenericException {
		employeeDAO.addEmployee(employee);
	}

	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		LOGGER.info("Employee Service listEmpoyee:::STARTED");
		List<Employee> list = employeeDAO.getAllEmployees();
		LOGGER.info("Employee Service listEmpoyee:::END");
		return list;
	}

	@Override
	@Transactional
	public void deleteEmployee(Integer employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}

	public Employee getEmployee(int empid) {
		return employeeDAO.getEmployee(empid);
	}

	public Employee updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	public List<Employee> getSearchEmployees(String searchString) {
		return employeeDAO.getSearchEmployees(searchString);
	}

}
