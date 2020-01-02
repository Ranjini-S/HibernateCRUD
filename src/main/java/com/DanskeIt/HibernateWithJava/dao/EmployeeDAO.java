package com.DanskeIt.HibernateWithJava.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.DanskeIt.HibernateWithJava.model.Employee;
import com.DansktIt.HibernateWithJava.util.HibernateUtil;

public class EmployeeDAO {

	public void saveEmployee(Employee employee) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start the transaction
			transaction = session.beginTransaction();

			// save object
			session.save(employee);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
	}

	public void insertEmployee() {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start a transaction

			transaction = session.beginTransaction();

			String hql = "INSERT INTO Employee (firstName, lastName, email) "

			+ "SELECT firstName, lastName, email FROM Employee";

			Query query = session.createQuery(hql);

			int result = query.executeUpdate();

			System.out.println("Rows affected: " + result);

			// commit transaction

			transaction.commit();

		} catch (Exception e) {

	/*		if (transaction != null) {

				transaction.rollback();

			}
*/
			e.printStackTrace();

		}

	}

	public void updateEmployee(Employee Employee) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start a transaction

			transaction = session.beginTransaction();

			// save the Employee object

			String hql = "UPDATE Employee set firstName = :firstName "
					+ "WHERE id = :EmployeeId";

			Query query = session.createQuery(hql);

			query.setParameter("firstName", Employee.getEmpFirstName());

			query.setParameter("EmployeeId", 1);

			int result = query.executeUpdate();

			System.out.println("Rows affected: " + result);

			// commit transaction

			transaction.commit();

		} catch (Exception e) {

			if (transaction != null) {

				transaction.rollback();

			}

			e.printStackTrace();

		}

	}

	public void deleteEmployee(int id) {

		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start a transaction

			transaction = session.beginTransaction();

			// Delete a Employee object

			Employee Employee = session.get(Employee.class, id);

			if (Employee != null) {

				String hql = "DELETE FROM Employee " + "WHERE id = :EmployeeId";

				Query query = session.createQuery(hql);

				query.setParameter("EmployeeId", id);

				int result = query.executeUpdate();

				System.out.println("Rows affected: " + result);

			}

			// commit transaction

			transaction.commit();

		} catch (Exception e) {

			if (transaction != null) {

				transaction.rollback();

			}

			e.printStackTrace();

		}

	}

	public Employee getEmployee(int id) {

		Transaction transaction = null;

		Employee Employee = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start a transaction

			transaction = session.beginTransaction();

			// get an Employee object

			String hql = " FROM Employee S WHERE S.id = :EmployeeId";

			Query query = session.createQuery(hql);

			query.setParameter("EmployeeId", id);

			List results = query.getResultList();

			if (results != null && !results.isEmpty()) {

				Employee = (Employee) results.get(0);

			}

			// commit transaction

			transaction.commit();

		} catch (Exception e) {
/*
			if (transaction != null) {

				//transaction.rollback();

			}
*/
			e.printStackTrace();

		}

		return Employee;

	}

	public List<Employee> getEmployees() {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			return session.createQuery("from Employee", Employee.class).list();

		}

	}
}
