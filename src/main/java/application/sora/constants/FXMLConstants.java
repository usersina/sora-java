package application.sora.constants;

/**
 * The class that has all FXML file names that should be references from here.
 * This is in case file names where to change.
 */
public final class FXMLConstants {
    private FXMLConstants() {
        // restrict instantiation
    }

    private static final String VIEWS_DIR = "/views";

    public static final String LOGIN = VIEWS_DIR + "/login.fxml";
    public static final String INDEX = VIEWS_DIR + "/index.fxml";

    public static final String PROGRESS_ART_ITEM = VIEWS_DIR + "/nodes/progress_art_item.fxml";
    public static final String FEATURED_ART_ITEM = VIEWS_DIR + "/nodes/featured_art_item.fxml";
}
