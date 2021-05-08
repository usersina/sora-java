package application.hibernate.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Person;
import application.hibernate.util.HibernateUtil;

// TODO:
public class PersonRepository {
	public PersonRepository() {
	}

	public Person save(Person person) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(person);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return person;
	}

	public void deleteById(Long id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(session.get(Person.class, id));
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
	}

	public Person findById(Long id) {
		Transaction transaction = null;
		Person person = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			person = session.get(Person.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return person;
	}

	public List<Person> findAll() {
		Transaction transaction = null;
		List<Person> persons = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			persons = session.createQuery("from Person", Person.class).list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return persons;
	}
}
