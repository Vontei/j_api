package hello.Controllers;
import java.io.BufferedReader;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.Models.Member;
import hello.Repositories.AccountRepository;
import hello.Repositories.MemberRepository;
import hello.Repositories.OrderRepository;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@RestController
@EnableMongoRepositories
public class OrderController {
		
		@Autowired
		public MemberRepository memberRepo;
		public OrderRepository orderRepo;
		public AccountRepository accountRepo;

	    @RequestMapping(value="/yahoo")
	    public String[] accountName() throws IOException{
	    	Stock microsoft = YahooFinance.get("MSFT");
	    	Stock google = YahooFinance.get("GOOG");
	    	Stock apple = YahooFinance.get("AAPL");

	    	BigDecimal MSFTprice = microsoft.getQuote(true).getPrice();
	    	BigDecimal AAPLprice = apple.getQuote(true).getPrice();
	    	BigDecimal GOOGprice = google.getQuote(true).getPrice();

	    
	    	String[] results = {MSFTprice.toString(),AAPLprice.toString(), GOOGprice.toString()}; 
	    	System.out.println(results);
	    	return results;
	    }
	    
	    
	    
//	    @RequestMapping(value="/account/trial" , method=RequestMethod.POST)
//	    public String[] doStuff(HttpServletRequest request, HttpServletResponse response) throws IOException{
////			     repository.deleteAll();
//
//				 StringBuffer jb = new StringBuffer();
//				  String line = null;
//				  try {
//				    BufferedReader reader = request.getReader();
//				    while ((line = reader.readLine()) != null)
//				      jb.append(line);
//				  } catch (Exception e) { /*report an error*/ }
//				 String[] json= {jb.toString()};
//				 System.out.println(jb);
//				 String thing = jb.toString();
//				 response.setContentType("application/json");
//				 JSONObject obj = new JSONObject(thing);
//				 String firstName = obj.getString("name");
//				 int accountid = 12345;
//
//				 System.out.println(obj.getString("name"));
//				 memberRepo.save(new Member(firstName, accountid));
//				 for (Member person : memberRepo.findAll()) {
//						System.out.println(person);
//					}
//	    	return json;
//	      }
	    
	
}
