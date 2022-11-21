package application.hibernate.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Artwork;
import application.hibernate.util.HibernateUtil;

/**
 * This is only here to fetch all artworks in one go.
 */
public class ArtworkRepository {
    public List<Artwork> findAll() {
        Transaction transaction = null;
        List<Artwork> artworks = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artworks = session.createQuery("from Artwork", Artwork.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return artworks;
    }

    public List<Artwork> findAllPublished() {
        Transaction transaction = null;
        List<Artwork> artworks = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artworks = session.createQuery("from Artwork WHERE publishedAt IS NOT NULL", Artwork.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return artworks;
    }

    /**
     * Find the last {@code limit} published books.
     * 
     * @param limit
     * @return
     */
    public List<Artwork> findRecentlyPublished(int limit) {
        Transaction transaction = null;
        List<Artwork> artworks = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artworks = session
                    .createQuery("from Artwork WHERE publishedAt IS NOT NULL ORDER BY publishedAt DESC", Artwork.class)
                    .setMaxResults(limit).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        }
        return artworks;
    }
}
