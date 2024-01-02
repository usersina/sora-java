package application.sora.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.hibernate.entities.User;
import application.hibernate.services.UserServiceImpl;
import application.hibernate.util.DatabaseSeeder;
import application.sora.FXApp;
import application.sora.constants.FXMLConstants;
import application.sora.constants.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController implements Initializable {
	@FXML
	private TextField tfEmail;

	@FXML
	private PasswordField tfPassword;

	@FXML
	private Button btnSignIn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DatabaseSeeder databaseSeeder = new DatabaseSeeder();
		databaseSeeder.populateDatabase();
	}

	/**
	 * Get the user input and verify whether it corresponds to an actual user.
	 * If so, then set the userId to the Global state and redirect to the Index
	 * scene. Otherwise show an error alert.
	 *
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void handleSignIn(ActionEvent event) throws IOException {
		String username = tfEmail.getText();
		String password = tfPassword.getText();

		User user = new UserServiceImpl().findUserByEmailOrUsername(username);
		if (Optional.ofNullable(user).isEmpty() || (!user.getPassword().equals(password))) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot login with the specified credentials!");
			alert.setHeaderText(null);
			alert.setResizable(true);
			alert.setTitle("Login error");
			alert.show();
			return;
		}
		Globals.setLoggedUserId(user.getId());
		new FXApp().changeScene(FXMLConstants.INDEX);

		// DEVONLY: Directly redirect to index
		// Globals.setLoggedUserId(1L);
		// new FXApp().changeScene(FXMLConstants.INDEX);
	}

	@FXML
	void handleExit(MouseEvent event) {
		System.exit(0);
	}
}
