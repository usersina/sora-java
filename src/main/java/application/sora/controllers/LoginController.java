package application.sora.controllers;

import java.io.IOException;

import org.hibernate.Session;

import application.hibernate.util.HibernateUtil;
import application.sora.FXApp;
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

	/**
	 * **TODO:** Implement an actual sign in
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void handleSignIn(ActionEvent event) throws IOException {
		// Initialize database in here to have an instance ready in index
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Logging in!" + session);
		new FXApp().changeScene("/views/index.fxml");
	}
}
