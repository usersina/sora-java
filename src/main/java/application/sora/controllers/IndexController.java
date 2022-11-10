package application.sora.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.sora.FXApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

enum AppTabPage {
	HOME,
	COLLECTION,
	SAVED,
	PROFILE
}

/**
 * The index controller of the wrapper that popus after the initial login and
 * contains all other tabs.
 * <p>
 * 
 * Note that:
 * <ul>
 * <li>This file also includes references to all other controllers to handle
 * which logic should be executed when the tab changes with {@code onTabOpen()}.
 * <li>The "Controller" is appended to the "fx:id" attributed under <fx:include>
 * therefore the references are not null.
 * </ul>
 */
public class IndexController implements Initializable {
	@FXML
	private AnchorPane mainAp;

	@FXML
	private TabPane mainTp;

	@FXML
	private Tab home;

	@FXML
	private Tab featured;

	@FXML
	private Tab collection;

	@FXML
	private Tab settings;

	@FXML
	private HomeTabController homeTabController;

	@FXML
	private CollectionTabController collectionTabController;

	@FXML
	private SavedTabController savedTabController;

	@FXML
	private ProfileTabController profileTabController;

	@FXML
	void handleExit(MouseEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.printf("Intializing %s...%n", this.getClass().getSimpleName());
		// Resize window to match window size
		FXApp.resizeWindow((int) mainAp.getPrefWidth(), (int) mainAp.getPrefHeight());
		setTabsListener();
	}

	// On Tab change, reload the appropriate data
	private void setTabsListener() {
		mainTp.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			String tabId = newTab.getId().toUpperCase();
			System.out.println("Switching to tab: " + tabId);
			switch (AppTabPage.valueOf(tabId)) {
				case HOME:
					homeTabController.onTabOpen();
					break;
				case COLLECTION:
					collectionTabController.onTabOpen();
					break;
				case SAVED:
					savedTabController.onTabOpen();
					break;
				case PROFILE:
					profileTabController.onTabOpen();
					break;
				default:
					System.err.println("Opening unhandled tab " + tabId);
					break;
			}
		});
	}
}
