package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.bankapp.FXApp;
import application.hibernate.entities.Account;
import application.hibernate.entities.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class IndexController implements Initializable {
	@FXML
	private TextField tfFirstName;

	@FXML
	private TextField tfLastName;

	@FXML
	private TextField tfAddress;

	@FXML
	private Button bntAddPerson;

	@FXML
	private TableView<Account> tvAccounts;

	@FXML
	private TableColumn<Account, String> colPerson;

	@FXML
	private TableColumn<Account, Double> colBalance;

	@FXML
	private TableColumn<Account, Double> colMaxWithdrawal;

	@FXML
	private TableColumn<Account, Double> colMaxOverdraft;

	@FXML
	private Button btnAddAccount;

	@FXML
	private ComboBox<Person> comboPersons;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initializing index controller...");

		// Resize window to match window size
		FXApp.resizeWindow(1000, 600);
	}

	@FXML
	void handleExit(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void onAddAccount(ActionEvent event) {

	}

}
