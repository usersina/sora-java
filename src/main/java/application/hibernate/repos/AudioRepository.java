package application.hibernate.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Audio;
import application.hibernate.entities.Book;
import application.hibernate.entities.User;
import application.hibernate.util.HibernateUtil;

public class AudioRepository {
    public Audio save(Audio audio, Long userId, Long bookId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            audio.setUser(session.get(User.class, userId));
            if (bookId != 0) {
                // Book ID exists, meaning that this is an Audio Book
                audio.setBook(session.get(Book.class, bookId));
            }
            session.saveOrUpdate(audio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return audio;
    }

    public List<Audio> findAll() {
        Transaction transaction = null;
        List<Audio> audios = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            audios = session.createQuery("from Audio", Audio.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return audios;
    }

    public Audio update(Audio book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return book;
    }
}
