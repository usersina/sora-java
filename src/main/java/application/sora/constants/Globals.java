package application.sora.constants;

/**
 * Global variables used throughout the application.
 */
public class Globals {
    /**
     * The ID of the currently logged in user. User IDs are expected to start
     * at 1 and NOT 0.
     */
    private static Long LOGGED_USER_ID = 0L;

    public static void setLoggedUserId(Long userId) {
        LOGGED_USER_ID = userId;
    }

    public static Long getLoggedUserId() {
        return LOGGED_USER_ID;
    }
}
