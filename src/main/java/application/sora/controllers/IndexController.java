package application.sora.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.sora.FXApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;

public class IndexController implements Initializable {

	@FXML
	private TabPane tpMain;

	@FXML
	private Tab dashboardTabPage;

	@FXML
	private Tab personsTabPage;

	@FXML
	private Tab accountsTabPage;

	@FXML
	private Tab operationsTabPage;

	@FXML
	private DashboardTabController dashboardTabController;

	@FXML
	private PersonsTabController personsTabController;

	@FXML
	private AccountsTabController accountsTabController;
	// "Controller" is appended to fx:id under fx:include,
	// therefore the above is not null

	@FXML
	private OperationsTabController operationsTabController;

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

	// On Tab change, reload the appropriate data
	void setTabsListener() {
		tpMain.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			String tabId = newTab.getId();
			System.out.println("Switching to tab: " + tabId);
			switch (tabId) {
				case "dashboardTabPage":
					dashboardTabController.onTabOpen();
					break;
				case "personsTabPage":
					personsTabController.onTabOpen();
					break;
				case "accountsTabPage":
					accountsTabController.onTabOpen();
					break;
				case "operationsTabPage":
					operationsTabController.onTabOpen();
					break;
				default:
					break;
			}
		});

	}

}
