package application.hibernate.util;

import org.hibernate.Session;

/**
 * This is a helper class used to feed initial data into the database.
 * This can be very convenient for dropping/re-creating the database and testing
 * without manually having to add the data.
 */
public class DatabaseSeeder {
    Session session;

    public DatabaseSeeder() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public void populateDatabase() {
        System.out.println("Should populate base data");
    }
}
