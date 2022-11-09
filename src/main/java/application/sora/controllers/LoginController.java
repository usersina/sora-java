package application.sora.controllers;

import java.io.IOException;

import application.sora.FXApp;
import application.hibernate.services.PersonServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {
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

	@FXML
	void handleSignIn(ActionEvent event) throws IOException {
		// Initialize database in here to have an instance ready in index
		System.out.println("Logging in!");
		new PersonServiceImpl();
		new FXApp().changeScene("/views/Index.fxml");
	}
}
