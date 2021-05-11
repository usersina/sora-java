package application.bankapp.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.hibernate.entities.Person;
import application.hibernate.services.PersonService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
		// Make table editable
		tvPersons.setEditable(true);
		colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
		colLastName.setCellFactory(TextFieldTableCell.forTableColumn());
		colAddress.setCellFactory(TextFieldTableCell.forTableColumn());

		// Link table columns to class attributes
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

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
				btnDeletePerson.setId("btnDelete");
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

	}

	void refreshPersons() {
		tvPersons.getItems().setAll(personService.getAllPersons());
	}
}
