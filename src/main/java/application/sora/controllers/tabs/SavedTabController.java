package application.sora.controllers.tabs;

import java.net.URL;
import java.util.ResourceBundle;

import application.helpers.nodes.interfaces.ITabPage;
import javafx.fxml.Initializable;

public class SavedTabController implements Initializable, ITabPage {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.printf("Intializing %s...%n", this.getClass().getSimpleName());
    }

    @Override
    public void onTabOpen() {
        System.out.printf("Opened the tab: %s%n", this.getClass().getSimpleName());
    }
}
