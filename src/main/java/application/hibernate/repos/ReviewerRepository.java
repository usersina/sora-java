package application.hibernate.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Reviewer;
import application.hibernate.util.HibernateUtil;

public class ReviewerRepository {
    public Reviewer save(Reviewer reviewer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(reviewer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return reviewer;
    }

    public List<Reviewer> findAll() {
        Transaction transaction = null;
        List<Reviewer> reviewers = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            reviewers = session.createQuery("from Reviewer", Reviewer.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return reviewers;
    }
}
