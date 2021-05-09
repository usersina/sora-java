package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.bankapp.FXApp;
import javafx.fxml.Initializable;

public class IndexController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Hello, I'm being initialized!");
		FXApp.resizeWindow(1000, 600);
	}

}
