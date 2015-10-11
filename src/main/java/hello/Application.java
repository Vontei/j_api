package hello;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import hello.Models.Account;
import hello.Models.Member;
import hello.Repositories.AccountRepository;
import hello.Repositories.MemberRepository;


@SpringBootApplication
@EnableMongoRepositories
public class Application implements CommandLineRunner {

	
	@Autowired
//	public MemberRepository repository;
	public AccountRepository repo;

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	public void run(String... args) throws Exception {
////		repository.save(new Member("NICKYDIAMONDs", 45));
//		repo.save(new Account("JavaFamily", "passwords"));
//		// fetch all customers
		System.out.println("Spring Booted");
		System.out.println("-------------------------------");
//		for (Member person : repository.findAll()) {
//			System.out.println(person);
//		}


	}

}
