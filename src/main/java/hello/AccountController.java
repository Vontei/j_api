package hello;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.MongoClient;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


@RestController
@EnableMongoRepositories
public class AccountController {
	@Autowired
	public PersonRepository repository;

//	MongoClient mongoClient = new MongoClient( "localhost/grockr" );



    @RequestMapping("/account/{id1}/{id2}")
    public String accountName(@PathVariable("id1") int id, @PathVariable("id2") int id1) throws IOException{
    	System.out.println(id+id1);
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
