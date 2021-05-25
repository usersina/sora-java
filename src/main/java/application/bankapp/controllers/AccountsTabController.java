package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.hibernate.entities.Account;
import application.hibernate.entities.Person;
import application.hibernate.services.AccountService;
import application.hibernate.services.AccountServiceImpl;
import application.hibernate.services.PersonService;
import application.hibernate.services.PersonServiceImpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

public class AccountsTabController implements Initializable {
	AccountService accountService;
	PersonService personService;
	Person selectedPerson;
	Account selectedAccount = null;

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
	private TableColumn<Account, Account> colDelete;

	@FXML
	private Button btnAddAccount;

	@FXML
	private TextField tfChangeAmount;

	@FXML
	private Button btnDeposit;

	@FXML
	private Button btnWithdraw;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("AccountsTab, initializing...");
		this.accountService = new AccountServiceImpl();
		this.personService = new PersonServiceImpl();
		setTvAccountsListener();

		// Link table columns to class attributes
		colNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
		colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		colMaxWithdrawal.setCellValueFactory(new PropertyValueFactory<>("maxWithdrawal"));
		colMaxOverdraft.setCellValueFactory(new PropertyValueFactory<>("maxOverdraft"));

		// Make table editable
		tvAccounts.setEditable(true);
		colMaxWithdrawal.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		colMaxOverdraft.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		// Set onEdit commit to update in table & in database
		colMaxWithdrawal.setOnEditCommit(new EventHandler<CellEditEvent<Account, Double>>() {
			@Override
			public void handle(CellEditEvent<Account, Double> event) {
				Account account = event.getRowValue();
				account.setMaxWithdrawal(event.getNewValue());
				accountService.updateAccount(account);
			}
		});
		colMaxOverdraft.setOnEditCommit(new EventHandler<CellEditEvent<Account, Double>>() {
			@Override
			public void handle(CellEditEvent<Account, Double> event) {
				Account account = event.getRowValue();
				account.setMaxOverdraft(event.getNewValue());
				accountService.updateAccount(account);
			}
		});

		// Add the delete button to the persons row
		colDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		colDelete.setCellFactory(param -> new TableCell<Account, Account>() {
			private final Button btnDeletePerson = new Button("Delete");

			@Override
			public void updateItem(Account account, boolean empty) {
				super.updateItem(account, empty);
				if (account == null) {
					setGraphic(null);
					return;
				}
				btnDeletePerson.getStyleClass().add("btnDelete");
				setGraphic(btnDeletePerson);
				btnDeletePerson.setOnAction(event -> {
					// Remove from UI
					tvAccounts.getItems().remove(account);
					// Remove from DB
					accountService.deleteAccountById(account.getId());
				});
			}
		});
	}

	@FXML
	void handleAddAccount(ActionEvent event) {
		System.out.println("Adding an account");
		try {
			this.accountService.saveAccount(new Account(Double.parseDouble(tfBalance.getText())),
					this.selectedPerson.getId());
			refreshAccountsBySelectedPerson();
			tfBalance.setText("0");
		} catch (Exception e) {
			System.out.println("Error adding your account! Check balance input or account!");
		}
	}

	@FXML
	void handleCbPersonsChange(ActionEvent event) {
		try {
			this.selectedPerson = comboPersons.getValue();
			enableAddBtn();
			refreshAccountsBySelectedPerson();
		} catch (Exception e) {
			System.out.println("On combo change is null!");
		}
	}

	@FXML
	void handleDeposit(ActionEvent event) {
		if (selectedAccount == null) {
			// Should be unreachable
			return;
		}
		try {
			Double amount = Double.parseDouble(tfChangeAmount.getText());
			selectedAccount.deposit(amount);
			accountService.updateAccount(selectedAccount);
			refreshAccountsBySelectedPerson();
		} catch (Exception e) {
			System.err.println("Cannot convert non number");
		}
	}

	@FXML
	void handleWithdraw(ActionEvent event) {
		if (selectedAccount == null) {
			// Should be unreachable
			return;
		}
		try {
			Double amount = Double.parseDouble(tfChangeAmount.getText());
			if (selectedAccount.withdraw(amount, true)) {
				accountService.updateAccount(selectedAccount);
				refreshAccountsBySelectedPerson();
				return;
			}
			System.err.println("Cannot withdraw!");
		} catch (Exception e) {
			System.err.println("Cannot convert non number");
		}
	}

	// -----------------------------//

	void setTvAccountsListener() {
		tvAccounts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection == null) {
				disableOpsBtn();
				return;
			}
			enableOpsBtn();
			System.out.println("Selected account is: " + newSelection);
			selectedAccount = newSelection;
		});
	}

	void refreshCbPersons() {
		resetFields();
		comboPersons.getItems().addAll(personService.getAllPersons());
	}

	void refreshAccountsBySelectedPerson() {
		// Re-fetch the selected person to get his accounts again
		// A better approach is to create & call
		// this.accountService.getAccountsByPersonId();
		this.selectedPerson = this.personService.getPerson(this.selectedPerson.getId());
		tvAccounts.getItems().setAll(this.selectedPerson.getAccounts());
	}

	void resetFields() {
		comboPersons.getItems().clear();
		tvAccounts.getItems().clear();
		tfBalance.setText("0");
		tfChangeAmount.setText("");
	}

	void enableOpsBtn() {
		btnDeposit.setDisable(false);
		btnWithdraw.setDisable(false);
		tfChangeAmount.setDisable(false);
	}

	void disableOpsBtn() {
		btnDeposit.setDisable(true);
		btnWithdraw.setDisable(true);
		tfChangeAmount.setDisable(true);
	}

	void enableAddBtn() {
		btnAddAccount.setDisable(false);
	}

	void disableAddBtn() {
		btnAddAccount.setDisable(true);
	}

	void onTabOpen() {
		refreshCbPersons();
		disableOpsBtn();
		disableAddBtn();
	}
	/*
	 * Matches integer, update to match double & call in initialize void
	 * validateBalance() { tfBalance.textProperty().addListener((observable,
	 * oldValue, newValue) -> { if (!newValue.matches("\\d*")) {
	 * tfBalance.setText(newValue.replaceAll("[^\\d]", "")); } }); }
	 */
}
