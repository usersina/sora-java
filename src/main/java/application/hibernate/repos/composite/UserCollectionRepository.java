package application.hibernate.repos.composite;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import application.hibernate.entities.Artwork;
import application.hibernate.entities.User;
import application.hibernate.entities.composite.UserCollection;
import application.hibernate.util.HibernateUtil;

public class UserCollectionRepository {
    /**
     * Create a new user collection. User and Artwork are supposed to be already
     * set in the userCollection.
     * 
     * @param userCollection
     * @return
     */
    public UserCollection save(UserCollection userCollection) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(userCollection);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return userCollection;
    }

    /**
     * Save the user collection and assign the specified userId and artworkId
     * to it.
     * 
     * @param userCollection
     * @param userId
     * @param artworkId
     * @return
     */
    public UserCollection save(UserCollection userCollection, Long userId, Long artworkId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userCollection.setUser(session.get(User.class, userId));
            userCollection.setArtwork(session.get(Artwork.class, artworkId));
            session.saveOrUpdate(userCollection);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return userCollection;
    }

    public List<UserCollection> findAll() {
        Transaction transaction = null;
        List<UserCollection> userCollections = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userCollections = session.createQuery("from user_collection", UserCollection.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return userCollections;
    }
}
