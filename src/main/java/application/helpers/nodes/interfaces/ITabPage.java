package application.helpers.nodes.interfaces;

public interface ITabPage {
    /**
     * The function to execute when the user opens the tab.
     * <p>
     * Initialization is not enough since the user may update the content of one
     * tab in another. When they then navigate back, only the old data is still
     * shown. We therefore execute this function to handle any logic of refreshing.
     */
    public void onTabOpen();
}
