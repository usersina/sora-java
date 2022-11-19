package application.hibernate.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Book;
import application.hibernate.entities.User;
import application.hibernate.util.HibernateUtil;

public class BookRepository {
    public Book save(Book book, Long userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            book.setUser(session.get(User.class, userId));
            session.saveOrUpdate(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return book;
    }

    public List<Book> findAll() {
        Transaction transaction = null;
        List<Book> books = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            books = session.createQuery("from Book", Book.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return books;
    }
}
