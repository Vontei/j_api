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
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception {

		
		// save a couple of customers
		repository.save(new Person("Nevil","Longbottom"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Person person : repository.findAll()) {
			System.out.println(person);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
//		System.out.println(repository.findByFirstName("Alice"));
//		System.out.println("-----------HI---------------------");
//		System.out.println(repository.findAll());
//		System.out.println("--------------------------------");
//		System.out.println(repository.findById("56118a7fd4c6ea4c1821555c"));
//

	

	}

}
