package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class Application implements CommandLineRunner {

	
	@Autowired
	public PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	public void run(String... args) throws Exception {
		repository.save(new Person("NICK", "NEANDERTEI"));
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Person person : repository.findAll()) {
			System.out.println(person);
		}


	}

}
