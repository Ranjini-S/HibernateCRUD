package com.DanskeIt.HibernateWithJava;

import java.util.List;

import com.DanskeIt.HibernateWithJava.dao.EmployeeDAO;
import com.DanskeIt.HibernateWithJava.model.Employee;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Employee employee = new Employee ("Ranjini","S",1345);
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		employeeDAO.saveEmployee(employee);
		
		Employee emp = employeeDAO.getEmployee(employee.getEmployeeId());
		System.out.println("Employee details " + emp);
		
		employee.setEmpLastName("Sathyanarayana");
		emp = employeeDAO.getEmployee(employee.getEmployeeId());
		System.out.println("Employee details " + emp);
		
		employeeDAO.insertEmployee();
		employeeDAO.insertEmployee();
		
		List<Employee> emplist = employeeDAO.getEmployees();
		
		for (Employee e: emplist)
		{
			System.out.println("Employees " + e);
		}
		
		employeeDAO.deleteEmployee(2);
		
		emplist = employeeDAO.getEmployees();
		
		for (Employee e: emplist)
		{
			System.out.println("Employees " + e);
		}
		
	}

}

