package application.hibernate.repos.composite;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Artwork;
import application.hibernate.entities.User;
import application.hibernate.entities.composite.ArtworkRating;
import application.hibernate.util.HibernateUtil;

public class ArtworkRatingRepository {
    /**
     * Create a new artwork rating. User and Artwork are supposed to be already
     * set in the artworkRating.
     * 
     * @param artworkRating
     * @return
     */
    public ArtworkRating save(ArtworkRating artworkRating) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(artworkRating);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return artworkRating;
    }

    /**
     * Save the artwork rating and assign it to the the specified userId and
     * artworkId.
     * 
     * @param artworkRating
     * @param userId
     * @param artworkId
     * @return
     */
    public ArtworkRating save(ArtworkRating artworkRating, Long userId, Long artworkId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artworkRating.setUser(session.get(User.class, userId));
            artworkRating.setArtwork(session.get(Artwork.class, artworkId));
            session.saveOrUpdate(artworkRating);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return artworkRating;
    }

    public List<ArtworkRating> findAll() {
        Transaction transaction = null;
        List<ArtworkRating> artworkRatings = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artworkRatings = session.createQuery("from artwork_rating", ArtworkRating.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return artworkRatings;
    }
}
