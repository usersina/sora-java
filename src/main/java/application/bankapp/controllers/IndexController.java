package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.bankapp.FXApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class IndexController implements Initializable {

	@FXML
	private TabPane mainTabs;

	@FXML
	void handleExit(MouseEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initializing index controller...");

		// Resize window to match window size
		FXApp.resizeWindow(1000, 600);
		setTabsListener();
	}

	void setTabsListener() {
		/*
		 * Nullpointer
		 * 
		 * mainTabs.getSelectionModel().selectedItemProperty().addListener((ov, oldTab,
		 * newTab) -> { System.out.println("Tab changed!"); });
		 */
	}

}
