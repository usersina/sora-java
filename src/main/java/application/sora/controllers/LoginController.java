package application.sora.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.hibernate.util.DatabaseSeeder;
import application.sora.FXApp;
import application.sora.constants.FXMLConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	 * Verify whether the inputs are valid and redirect the user to the Index
	 * scene.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void handleSignIn(ActionEvent event) throws IOException {
		// TODO: Pass which user was Signed in
		new FXApp().changeScene(FXMLConstants.INDEX);
	}

	@FXML
	void handleExit(MouseEvent event) {
		System.exit(0);
	}
}
