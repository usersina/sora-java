package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.hibernate.entities.Person;
import application.hibernate.services.PersonService;
import application.hibernate.services.PersonServiceImpl;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class PersonsTabController implements Initializable {
	PersonService personService;

	@FXML
	private TableView<Person> tvPersons;

	@FXML
	private TableColumn<Person, String> colFirstName;

	@FXML
	private TableColumn<Person, String> colLastName;

	@FXML
	private TableColumn<Person, String> colAddress;

	@FXML
	private TableColumn<Person, Person> colDelete;

	@FXML
	private TextField tfFirstName;

	@FXML
	private TextField tfLastName;

	@FXML
	private TextField tfAddress;

	@FXML
	private Button bntAddPerson;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("PersonsTab, initializing...");
		this.personService = new PersonServiceImpl();
		refreshPersons();

		// Link table columns to class attributes
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

		// Make table editable
		tvPersons.setEditable(true);
		colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
		colLastName.setCellFactory(TextFieldTableCell.forTableColumn());
		colAddress.setCellFactory(TextFieldTableCell.forTableColumn());

		// Set onEdit commit to update in table & in database
		colFirstName.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> event) {
				Person person = event.getRowValue();
				person.setFirstName(event.getNewValue());
				personService.savePerson(person);
			}
		});
		colLastName.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> event) {
				Person person = event.getRowValue();
				person.setLastName(event.getNewValue());
				personService.savePerson(person);
			}
		});
		colAddress.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> event) {
				Person person = event.getRowValue();
				person.setAddress(event.getNewValue());
				personService.savePerson(person);
			}
		});

		// Add the delete button to the persons row
		colDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		colDelete.setCellFactory(param -> new TableCell<Person, Person>() {
			private final Button btnDeletePerson = new Button("Delete");

			@Override
			public void updateItem(Person person, boolean empty) {
				super.updateItem(person, empty);
				if (person == null) {
					setGraphic(null);
					return;
				}
				btnDeletePerson.getStyleClass().add("btnDelete");
				setGraphic(btnDeletePerson);
				btnDeletePerson.setOnAction(event -> {
					personService.deletePersonById(person.getId());
					refreshPersons();
				});
			}
		});
	}

	@FXML
	void handleAddPerson(ActionEvent event) {
		if (tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || tfAddress.getText().isEmpty()) {
			System.out.println("One or more fields are empty!");
			return;
		}
		this.personService.savePerson(new Person(tfFirstName.getText(), tfLastName.getText(), tfAddress.getText()));
		tfFirstName.setText("");
		tfLastName.setText("");
		tfAddress.setText("");
		refreshPersons();
	}

	// ----------------------------------//
	void refreshPersons() {
		tvPersons.getItems().setAll(personService.getAllPersons());
	}

}
