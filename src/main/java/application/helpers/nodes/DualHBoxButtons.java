package application.helpers.nodes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class DualHBoxButtons {
    private final HBox hbox = new HBox(5);

    private final Button firstBtn;
    private final Button secondBtn;

    public DualHBoxButtons(String firstButtonText, String secondButtonText) {
        this.firstBtn = new Button(firstButtonText);
        this.secondBtn = new Button(secondButtonText);
        this.hbox.getChildren().addAll(this.firstBtn, this.secondBtn);
        this.hbox.setAlignment(Pos.CENTER);
    }

    public void setEventHandlers(EventHandler<ActionEvent> firstBtnAction, EventHandler<ActionEvent> secondBtnAction) {
        this.firstBtn.setOnAction(firstBtnAction);
        this.secondBtn.setOnAction(secondBtnAction);
    }

    public void addBtnStyles(String firstBtnStyle, String secondBtnStyle) {
        this.firstBtn.getStyleClass().add(firstBtnStyle);
        this.secondBtn.getStyleClass().add(secondBtnStyle);
    }

    public HBox getMainNode() {
        return this.hbox;
    }

}
