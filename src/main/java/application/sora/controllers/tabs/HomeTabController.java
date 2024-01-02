package application.sora.controllers.tabs;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.helpers.nodes.interfaces.ITabPage;
import application.hibernate.entities.Artwork;
import application.hibernate.services.ArtworkService;
import application.hibernate.services.ArtworkServiceImpl;
import application.sora.constants.FXMLConstants;
import application.sora.controllers.nodes.FeaturedItemController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
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

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    try {
                        populateProgressList();
                        populateFeaturedList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                return null;
            }
        };

        task.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Throwable e = task.getException();
                e.printStackTrace();
            }
        });

        new Thread(task).start();
    }

    private void populateProgressList() throws IOException {
        Node[] nodes = new Node[3];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = FXMLLoader.load(getClass().getResource(FXMLConstants.PROGRESS_ART_ITEM));
        }
        progressHBox.getChildren().addAll(nodes);
    }

    private void populateFeaturedList() throws IOException {
        ArtworkService artworkService = new ArtworkServiceImpl();

        List<Artwork> artworks = artworkService.getRecentlyPublishedArtworks(5);
        System.out.printf("Featured list includes %s items %n", artworks.size());

        Node[] nodes = new Node[artworks.size()];
        for (int i = 0; i < nodes.length; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLConstants.FEATURED_ART_ITEM));
            loader.setController(new FeaturedItemController(artworks.get(i)));
            nodes[i] = loader.load();
        }
        featuredHBox.getChildren().addAll(nodes);
    }
}
