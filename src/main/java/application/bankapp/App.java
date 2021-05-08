package application.bankapp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entity.Person;
import application.hibernate.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		Person person = new Person("Test", "Ben Tester", "40 Far Street");
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student objects
			session.save(person);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Person> students = session.createQuery("from Person", Person.class).list();
			students.forEach(s -> System.out.println(s.getFirstName()));
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}