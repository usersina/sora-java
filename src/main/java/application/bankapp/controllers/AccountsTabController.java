package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.hibernate.entities.Account;
import application.hibernate.entities.Person;
import application.hibernate.services.AccountService;
import application.hibernate.services.AccountServiceImpl;
import application.hibernate.services.PersonService;
import application.hibernate.services.PersonServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

public class AccountsTabController implements Initializable {
	AccountService accountService;
	PersonService personService;
	Person selectedPerson;

	@FXML
	private ComboBox<Person> comboPersons;

	@FXML
	private TextField tfBalance;

	@FXML
	private TableView<Account> tvAccounts;

	@FXML
	private TableColumn<Account, Integer> colNumber;

	@FXML
	private TableColumn<Account, Double> colBalance;

	@FXML
	private TableColumn<Account, Double> colMaxWithdrawal;

	@FXML
	private TableColumn<Account, Double> colMaxOverdraft;

	@FXML
	private Button btnAddAccount;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("AccountsTab, initializing...");
		this.accountService = new AccountServiceImpl();
		this.personService = new PersonServiceImpl();
		refreshCbPersons();

		// Link table columns to class attributes
		colNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
		colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		colMaxWithdrawal.setCellValueFactory(new PropertyValueFactory<>("maxWithdrawal"));
		colMaxOverdraft.setCellValueFactory(new PropertyValueFactory<>("maxOverdraft"));

		// Make table editable
		tvAccounts.setEditable(true);
		colBalance.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		colMaxWithdrawal.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		colMaxOverdraft.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
	}

	@FXML
	void handleAddAccount(ActionEvent event) {
		System.out.println("Adding an account");
		try {
			this.accountService.saveAccount(new Account(Double.parseDouble(tfBalance.getText())),
					this.selectedPerson.getId());
			refreshAccountsBySelectedPerson();
		} catch (Exception e) {
			System.out.println("Error adding your account! Check balance input or account!");
		}
	}

	@FXML
	void handleCbPersonsChange(ActionEvent event) {
		this.selectedPerson = comboPersons.getValue();
		refreshAccountsBySelectedPerson();
	}

	// -----------------------------//
	void refreshCbPersons() {
		comboPersons.getItems().addAll(personService.getAllPersons());
	}

	void refreshAccountsBySelectedPerson() {
		// Re-fetch the selected person to get his accounts again
		// A better approach is to create & call
		// this.accountService.getAccountsByPersonId();
		this.selectedPerson = this.personService.getPerson(this.selectedPerson.getId());
		tvAccounts.getItems().setAll(this.selectedPerson.getAccounts());
	}

	/*
	 * Matches integer, update to match double & call in initialize void
	 * validateBalance() { tfBalance.textProperty().addListener((observable,
	 * oldValue, newValue) -> { if (!newValue.matches("\\d*")) {
	 * tfBalance.setText(newValue.replaceAll("[^\\d]", "")); } }); }
	 */
}