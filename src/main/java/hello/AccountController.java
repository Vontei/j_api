package hello;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@RestController
@EnableMongoRepositories
public class AccountController {
	@Autowired
	public PersonRepository repository;

    @RequestMapping("/account")
    public String accountName() throws IOException{
    	Stock stock = YahooFinance.get("INTC");
    	BigDecimal price = stock.getQuote(true).getPrice();
    	System.out.println("The current price of INTEL is:" +price);
    	repository.save(new Person("WORK PLEASE","Longbottom"));
    	return "BRAND NEW NEW ACCOUNT";
    }
    
    
    @RequestMapping("/account/people")   
    public List<String> accountPeople(){
		List<String> x = new ArrayList<String>();
    	for (Person person : repository.findAll()) {
    		x.add(person.toString());
		}
		return x;
    }
    
    
}
