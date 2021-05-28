package application.helpers.nodes;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ConfirmActionAlert {
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    // "Confirm account deletion"
    // Are you sure you want to delete this person?\nAll associated accounts will be
    // deleted as well!

    public ConfirmActionAlert(String title, String body, String confirmBtnText) {
        this.alert.setTitle(title);
        this.alert.setHeaderText(null);
        this.alert.setContentText(body);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(confirmBtnText);
    }

    public Optional<ButtonType> showAlertAndWait() {
        return this.alert.showAndWait();
    }

    public boolean isConfirmed(Optional<ButtonType> res) {
        return res.get().getButtonData().toString().equals("OK_DONE");
    }

    public Alert getMainNode() {
        return this.alert;
    }
}
