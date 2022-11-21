package application.sora.controllers.nodes;

import java.net.URL;
import java.util.ResourceBundle;

import application.hibernate.entities.Artwork;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FeaturedItemController implements Initializable {
    private Artwork artwork;

    @FXML
    private Label authorLbl;

    @FXML
    private ImageView coverImg;

    @FXML
    private Label genreLbl;

    @FXML
    private Label titleLbl;

    public FeaturedItemController(Artwork artwork) {
        this.artwork = artwork;
    };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.printf("Artwork is set to %s | ID: %s %n", artwork.getClass().getSimpleName(), artwork.getId());
    }
}
