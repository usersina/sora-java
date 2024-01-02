package application.sora.constants;

import application.hibernate.entities.User;

/**
 * Global variables used throughout the application.
 */
public class Globals {
    /**
     * The ID of the currently logged in user. This is set to default initially.
     * Also note that User IDs are expected to start at 1.
     */
    private static User LOGGED_USER;

    public static void setLoggedUser(User user) {
        LOGGED_USER = user;
    }

    public static User getLoggedUser() {
        return LOGGED_USER;
    }
}
