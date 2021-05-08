package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Person;
import application.hibernate.repos.PersonRepository;

public class PersonServiceImpl implements PersonService {
	PersonRepository personRepository = new PersonRepository();

	@Override
	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person updatePerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public void deletePersonById(Long id) {
		personRepository.deleteById(id);
	}

	@Override
	public Person getPerson(Long id) {
		return personRepository.findById(id);
	}

	@Override
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

}
