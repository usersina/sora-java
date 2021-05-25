package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.hibernate.entities.Account;
import application.hibernate.services.AccountService;
import application.hibernate.services.AccountServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class OperationsTabController implements Initializable {
	AccountService accountService;
	Account selectedSrcAccount = null;
	Account selectedTrgAccount = null;

	@FXML
	private Button btnMakeTransaction;

	@FXML
	private ComboBox<Account> comboSrcAccList;

	@FXML
	private ComboBox<Account> comboTrgAccList;

	@FXML
	private TextField tfAmount;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("OperationsTab, initializing...");
		this.accountService = new AccountServiceImpl();
	}

	@FXML
	void handleMakeTransaction(ActionEvent event) {
		try {
			Double amount = Double.parseDouble(tfAmount.getText());
			if (selectedSrcAccount.withdraw(amount, false)) {
				selectedTrgAccount.deposit(amount);
				accountService.updateAccount(selectedSrcAccount);
				accountService.updateAccount(selectedTrgAccount);
				onTabOpen();
				return;
			}
			System.err.println("Cannot make transaction!");
		} catch (Exception e) {
			System.err.println("Cannot convert non number");
		}
	}

	@FXML
	void handleSrcAccChange(ActionEvent event) {
		this.selectedSrcAccount = comboSrcAccList.getValue();
		validateTransactBtn();
	}

	@FXML
	void handleTrgAccChange(ActionEvent event) {
		this.selectedTrgAccount = comboTrgAccList.getValue();
		validateTransactBtn();
	}

	void validateTransactBtn() {
		if (this.selectedSrcAccount != null && this.selectedTrgAccount != null
				&& this.selectedSrcAccount.getId() != this.selectedTrgAccount.getId())
			enableMakeTranBtn();
		else
			disableMakeTranBtn();
	}

	void refreshComboAccounts() {
		comboSrcAccList.getItems().setAll(this.accountService.getAllAccounts());
		comboTrgAccList.getItems().setAll(this.accountService.getAllAccounts());
	}

	void disableMakeTranBtn() {
		btnMakeTransaction.setDisable(true);
	}

	void enableMakeTranBtn() {
		btnMakeTransaction.setDisable(false);
	}

	void onTabOpen() {
		refreshComboAccounts();
		disableMakeTranBtn();
		tfAmount.setText("");
		this.selectedSrcAccount = null;
		this.selectedTrgAccount = null;
	}
}
