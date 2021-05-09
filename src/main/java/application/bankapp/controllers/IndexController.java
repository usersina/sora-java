package application.bankapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class IndexController {

	@FXML
	private TextField tfEmail;

	@FXML
	private PasswordField tfPassword;

	@FXML
	private Button btnSignIn;

	@FXML
	void handleExit(MouseEvent event) {
		System.exit(0);
	}

}
