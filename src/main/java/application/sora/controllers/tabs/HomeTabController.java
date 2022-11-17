package application.sora.controllers.tabs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.helpers.nodes.interfaces.ITabPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class HomeTabController implements Initializable, ITabPage {
    @FXML
    private HBox featuredHBox;

    @FXML
    private HBox progressHBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.printf("Intializing %s...%n", this.getClass().getSimpleName());

        // This is the default open tab on initialization
        this.onTabOpen();
    }

    @Override
    public void onTabOpen() {
        System.out.printf("Opened the tab: %s%n", this.getClass().getSimpleName());
        try {
            this.populateProgressList();
            this.populateFeaturedList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateProgressList() throws IOException {
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = FXMLLoader.load(getClass().getResource("/views/nodes/progress_art_item.fxml"));
        }
        progressHBox.getChildren().addAll(nodes);
    }

    private void populateFeaturedList() throws IOException {
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = FXMLLoader.load(getClass().getResource("/views/nodes/featured_art_item.fxml"));
        }
        featuredHBox.getChildren().addAll(nodes);
    }
}
