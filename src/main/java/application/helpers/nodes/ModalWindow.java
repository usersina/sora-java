package application.helpers.nodes;

import java.io.IOException;

import application.helpers.nodes.controllers.IModalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalWindow {
    public static String AccountModal = "accountModal";
    public static String PersonModal = "personModal";

    private FXMLLoader loader;
    private Stage modalStage;

    public ModalWindow(String currentView, String title) {
        this.modalStage = new Stage();
        this.modalStage.initModality(Modality.APPLICATION_MODAL);
        this.modalStage.setResizable(false);
        this.modalStage.setTitle(title);
        try {
            this.loader = new FXMLLoader(getClass().getResource("/views/modals/" + currentView + ".fxml"));
            Parent pane = this.loader.load();
            Scene scene = new Scene(pane);
            this.modalStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadObjectData(Object obj) {
        ((IModalController) this.loader.getController()).loadObjectData(obj);
    }

    public boolean didUpdate() {
        return ((IModalController) this.loader.getController()).didUpdate();
    }

    public void showModalAndWait() {
        this.modalStage.showAndWait();
    }

}
