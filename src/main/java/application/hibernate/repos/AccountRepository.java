package application.hibernate.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Account;
import application.hibernate.entities.Person;
import application.hibernate.util.HibernateUtil;

public class AccountRepository {
	public Account save(Account account, Long personId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			account.setPerson(session.get(Person.class, personId));
			session.saveOrUpdate(account);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return account;
	}

	public Account update(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(account);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return account;
	}

	public void deleteById(Long id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Account acc = session.get(Account.class, id);
			acc.getPerson().getAccounts().remove(acc);
			session.delete(acc);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			if (transaction != null)
				transaction.rollback();
		}
	}

	public Account findById(Long id) {
		Transaction transaction = null;
		Account account = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			account = session.get(Account.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return account;
	}

	public List<Account> findAll() {
		Transaction transaction = null;
		List<Account> persons = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			persons = session.createQuery("from Account", Account.class).list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return persons;
	}

	public Account findRichest() {
		Transaction transaction = null;
		Account account = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			account = session
					.createQuery("FROM Account WHERE balance = (SELECT MAX(balance) FROM Account)", Account.class)
					.list().get(0);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return account;
	}

	public List<Account> findAllOverdraft() {
		Transaction transaction = null;
		List<Account> accounts = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			accounts = session.createQuery("FROM Account WHERE overdraft != 0 ORDER BY overdraft DESC", Account.class)
					.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
		}
		return accounts;
	}
}
