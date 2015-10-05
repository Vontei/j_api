//package hello;
//
//import java.util.List;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.query.Param;
//
//
//public interface PersonRepository extends MongoRepository<Person, String> {
//
//	public Person save(Person person);
//    public Person findByFirstName(String firstName);
////    public List<Person> findByLastName(String lastName);
//	public List<Person> findAll();
//	public <T> T findById(Object id);
//	public Person findByLastName(@Param("name") String lastName);
//
//}



package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findByLastName(@Param("name") String lastName);

}