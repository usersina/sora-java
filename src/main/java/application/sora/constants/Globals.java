package application.sora.constants;

/**
 * Global variables used throughout the application.
 */
public class Globals {
    /**
     * The ID of the currently logged in user. This is set to default initially.
     * Also note that User IDs are expected to start at 1.
     */
    private static Long LOGGED_USER_ID;

    public static void setLoggedUserId(Long userId) {
        LOGGED_USER_ID = userId;
    }

    public static Long getLoggedUserId() {
        return LOGGED_USER_ID;
    }
}
