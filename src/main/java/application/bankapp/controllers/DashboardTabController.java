package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.hibernate.entities.Account;
import application.hibernate.services.AccountService;
import application.hibernate.services.AccountServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardTabController implements Initializable {
	AccountService accountService;
	Account richestAccount;

	@FXML
	private TableView<Account> tvRedAccounts;

	@FXML
	private TableColumn<Account, Integer> colAccountId;

	@FXML
	private TableColumn<Account, Double> colBalance;

	@FXML
	private TableColumn<Account, Double> colMaxWithdrawal;

	@FXML
	private TableColumn<Account, Double> colMaxOverdraft;

	@FXML
	private Label labelRichestAccountId;

	@FXML
	private Label labelRichestBalance;

	@FXML
	private Label labelRichestPersonName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("DahsboardTab, initializing...");
		this.accountService = new AccountServiceImpl();
		onTabOpen();

		// Link table columns to class attributes
		colAccountId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		colMaxWithdrawal.setCellValueFactory(new PropertyValueFactory<>("maxWithdrawal"));
		colMaxOverdraft.setCellValueFactory(new PropertyValueFactory<>("maxOverdraft"));
	}

	void onTabOpen() {
		try {
			this.tvRedAccounts.getItems().setAll(this.accountService.getAllOverdraftAccounts());
		} catch (Exception e) {
			System.out.println("No accounts yet!");
		}

		try {
			this.richestAccount = this.accountService.getRichestAccount();
			this.labelRichestAccountId.setText(richestAccount.getId().toString());
			this.labelRichestBalance.setText(Double.toString(richestAccount.getBalance()));
			this.labelRichestPersonName.setText(richestAccount.getPerson().toString());
		} catch (Exception e) {
			this.labelRichestAccountId.setText("");
			this.labelRichestBalance.setText("No data yet!");
			this.labelRichestPersonName.setText("");
			System.out.println("No richest account yet!");
		}
	}

}
