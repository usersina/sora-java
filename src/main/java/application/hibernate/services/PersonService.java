package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Person;

public interface PersonService {
	Person savePerson(Person person);

	Person updatePerson(Person person);

	void deletePersonById(Long id);

	Person getPerson(Long id);

	List<Person> getAllPersons();
}
