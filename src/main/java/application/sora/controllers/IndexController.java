package application.sora.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.sora.FXApp;
import application.sora.chat.ChatWindow;
import application.sora.constants.Globals;
import application.sora.controllers.tabs.CollectionTabController;
import application.sora.controllers.tabs.HomeTabController;
import application.sora.controllers.tabs.ProfileTabController;
import application.sora.controllers.tabs.SavedTabController;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
	private Label fullnameLb;

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
		System.out.printf("Initializing %s...%n", this.getClass().getSimpleName());

		// Sanity Check, if no userId is present crash the app
		if (Globals.getLoggedUser() == null) {
			throw new RuntimeException("Cannot Load the Index window without a valid userId");
		}

		// Resize stage to match current size and center stage to center
		FXApp.resizeWindow((int) mainAp.getPrefWidth(), (int) mainAp.getPrefHeight());
		FXApp.centerStage();

		fullnameLb.setText(Globals.getLoggedUser().getFirstName() + " " + Globals.getLoggedUser().getLastName());

		setTabsListener();

		// Show the chat window after 2 seconds
		PauseTransition delay = new PauseTransition(Duration.seconds(2));
		delay.setOnFinished(event -> new ChatWindow().show());
		delay.play();
	}

	/**
	 * This makes each tab controller aware that it was opened by calling
	 * the `onTabOpen` function depending on the opened tab.
	 */
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
