package application.hibernate.repos.composite;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Artwork;
import application.hibernate.entities.Reviewer;
import application.hibernate.entities.composite.ArtworkReview;
import application.hibernate.util.HibernateUtil;

public class ArtworkReviewRepository {
    /**
     * Create a new artwork rating. User and Artwork are supposed to be already
     * set in the artworkReview.
     * 
     * @param artworkReview
     * @return
     */
    public ArtworkReview save(ArtworkReview artworkReview) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(artworkReview);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return artworkReview;
    }

    /**
     * Save the artwork rating and assign it to the the specified userId and
     * artworkId.
     * 
     * @param artworkReview
     * @param userId
     * @param artworkId
     * @return
     */
    public ArtworkReview save(ArtworkReview artworkReview, Long userId, Long artworkId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artworkReview.setReviewer(session.get(Reviewer.class, userId));
            artworkReview.setArtwork(session.get(Artwork.class, artworkId));
            session.saveOrUpdate(artworkReview);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return artworkReview;
    }

    public List<ArtworkReview> findAll() {
        Transaction transaction = null;
        List<ArtworkReview> artworkReviews = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artworkReviews = session.createQuery("from artwork_review", ArtworkReview.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return artworkReviews;
    }
}
