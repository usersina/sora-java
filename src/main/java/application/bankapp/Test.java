package application.bankapp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Account;
import application.hibernate.entities.Person;
import application.hibernate.services.AccountService;
import application.hibernate.services.AccountServiceImpl;
import application.hibernate.util.HibernateUtil;

public class Test {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		AccountService accountService = new AccountServiceImpl();
		System.out.println(accountService.getRichestAccount());
		for (Account a : accountService.getAllOverdraftAccounts()) {
			System.out.println(a);
		}
	}

	public static void main1(String[] args) {
		AccountService accountService = new AccountServiceImpl();
		// accountService.updateAccount(new Account(3L), 1L);
		accountService.getAccount(47L);
		accountService.deleteAccountById(47L);
	}

	public static void main2(String[] args) {
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
			students.forEach(s -> System.out.println(s));
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}