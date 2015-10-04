package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

	public Person save(Person person);
    public Person findByFirstName(String firstName);
    public List<Person> findByLastName(String lastName);
	public List<Person> findAll();

}

