package application.helpers.nodes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.helpers.nodes.interfaces.IModalController;
import application.hibernate.entities.Account;
import application.hibernate.services.AccountService;
import application.hibernate.services.AccountServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountModalController implements IModalController, Initializable {
    AccountService accountService;
    Account currentAccount;
    boolean didUpdate;

    @FXML
    private TextField tfBalance;

    @FXML
    private TextField tfMaxWithdrawal;

    @FXML
    private TextField tfMaxOverdraft;

    @FXML
    private TextField tfAccountId;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("AccountModal, initializing...");
        this.accountService = new AccountServiceImpl();
        this.tfAccountId.setDisable(true);
        this.currentAccount = null;
        this.didUpdate = false;
    }

    @FXML
    void handleCancel(ActionEvent event) {
        closeThisWindow(event);
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        try {
            Double balance = Double.parseDouble(this.tfBalance.getText());
            Double maxWithdrawal = Double.parseDouble(this.tfMaxWithdrawal.getText());
            Double maxOverdraft = Double.parseDouble(this.tfMaxOverdraft.getText());

            currentAccount.setMaxWithdrawal(maxWithdrawal);
            currentAccount.setMaxOverdraftNoCheck(maxOverdraft);
            currentAccount.setBalance(balance);

            this.accountService.updateAccount(currentAccount);
            this.didUpdate = true;

            closeThisWindow(event);
        } catch (Exception e) {
            System.err.println("ALERT: Cannot parse to double!");
        }
    }

    void closeThisWindow(Event event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    void populateUIFromObj(Account account) {
        this.currentAccount = account;
        this.tfAccountId.setText(account.getId().toString());
        this.tfBalance.setText(Double.toString(account.getBalance()));
        this.tfMaxWithdrawal.setText(Double.toString(account.getMaxWithdrawal()));
        this.tfMaxOverdraft.setText(Double.toString(account.getMaxOverdraft()));
    }

    @Override
    public void loadObjectData(Object obj) {
        System.out.println("Loaded object is: " + obj);
        populateUIFromObj((Account) obj);
    }

    @Override
    public boolean didUpdate() {
        return this.didUpdate;
    }

}
