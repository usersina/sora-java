package application.helpers.nodes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalWindow {
    private FXMLLoader loader;
    private Stage modalStage;

    /**
     * Open a new Modal with the specified FXML file.
     * 
     * @param fxmlFile
     * @param title
     */
    public ModalWindow(String fxmlFile, String title) {
        this.modalStage = new Stage();
        this.modalStage.initModality(Modality.APPLICATION_MODAL);
        this.modalStage.setResizable(false);
        this.modalStage.setTitle(title);
        try {
            this.loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = this.loader.load();
            Scene scene = new Scene(pane);
            this.modalStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showModalAndWait() {
        this.modalStage.showAndWait();
    }
}
