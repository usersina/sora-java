package application.hibernate.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Genre;
import application.hibernate.util.HibernateUtil;

public class GenreRepository {
    public Genre save(Genre genre) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(genre);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return genre;
    }

    public List<Genre> findAll() {
        Transaction transaction = null;
        List<Genre> genres = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            genres = session.createQuery("from Genre", Genre.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return genres;
    }
}
