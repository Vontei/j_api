package hello.Controllers;
import org.springframework.web.bind.annotation.RestController;

import hello.Repositories.AccountRepository;
import hello.Repositories.MemberRepository;
import hello.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;




public class MemberController {
	@RestController
	@EnableMongoRepositories
	public class AccountController {
		
		@Autowired
		public MemberRepository memberRepo;
		public OrderRepository orderRepo;
		public AccountRepository accountRepo;

//	    @RequestMapping("/account/{id1}/{id2}")
//	    public String[] accountName(@PathVariable("id1") int id, @PathVariable("id2") int id1) throws IOException{
//	    	System.out.println(id+id1);
//	    	int sum = id+id1;
//	    	Stock stock = YahooFinance.get("GOOG");
//	    	BigDecimal price = stock.getQuote(true).getPrice();
//	    	System.out.println("The current price of GOOGLE is:" +price);
//	    	String[] results = {price.toString(), Integer.toString(sum)}; 
//	    	return results;
//	    }
//	    
	    
	    

	    
	}
}
