package com.org.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.org.exception.CustomGenericException;
import com.org.model.Employee;



@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addEmployee(Employee employee) throws CustomGenericException {
		try {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
		} catch(Exception e) {
			throw new CustomGenericException("E123", "Error while saving Employee");
		}

	}

	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {

		return sessionFactory.getCurrentSession().createQuery("from Employee")
				.list();
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(
				Employee.class, employeeId);
		if (null != employee) {
			this.sessionFactory.getCurrentSession().delete(employee);
		}

	}

	public Employee getEmployee(int empid) {
		return (Employee) sessionFactory.getCurrentSession().get(
				Employee.class, empid);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
		return employee;
	}

	@Override
	public List<Employee> getSearchEmployees(String searchString) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Employee WHERE name like concat('%',:searchString,'%')");
        query.setParameter("searchString", searchString);
       List<Employee> list = query.list();
		return list;
	}

}