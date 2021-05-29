package application.helpers.nodes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.helpers.nodes.interfaces.IModalController;
import application.hibernate.entities.Person;
import application.hibernate.services.PersonService;
import application.hibernate.services.PersonServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonModalController implements IModalController, Initializable {
    PersonService personService;
    Person currentPerson;
    boolean didUpdate;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfPersonId;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("PersonModal, initializing...");
        this.personService = new PersonServiceImpl();
        this.tfPersonId.setDisable(true);
        this.currentPerson = null;
        this.didUpdate = false;
    }

    @FXML
    void handleCancel(ActionEvent event) {
        closeThisWindow(event);
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        currentPerson.setFirstName(this.tfFirstName.getText());
        currentPerson.setLastName(this.tfLastName.getText());
        currentPerson.setAddress(this.tfAddress.getText());
        this.personService.updatePerson(currentPerson);
        this.didUpdate = true;
        closeThisWindow(event);
    }

    void closeThisWindow(Event event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    void populateUIFromObj(Person person) {
        this.currentPerson = person;
        this.tfPersonId.setText(person.getId().toString());
        this.tfFirstName.setText(person.getFirstName());
        this.tfLastName.setText(person.getLastName());
        this.tfAddress.setText(person.getAddress());
    }

    @Override
    public void loadObjectData(Object obj) {
        System.out.println("Loaded object is: " + obj);
        populateUIFromObj((Person) obj);
    }

    @Override
    public boolean didUpdate() {
        return this.didUpdate;
    }

}
