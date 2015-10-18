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

import com.fasterxml.jackson.databind.JsonDeserializer;

import hello.Models.Account;
import hello.Models.Member;
import hello.Repositories.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@RestController
@EnableMongoRepositories
public class AccountController {
	
	@Autowired
	public AccountRepository accountRepo;
	@Autowired
	public MemberRepository memberRepo;
	@Autowired
	public OrderRepository orderRepo;
	
	
	@RequestMapping(value="/account/new" ,method=RequestMethod.POST)
	public List<Account> createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		 System.out.println(jb);
		 String thing = jb.toString();
		 JSONObject obj = new JSONObject(thing);
		 String userName = obj.getString("userName");
		 String password = obj.getString("password");
		 String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

		 accountRepo.save(new Account(userName, hashed));
//		 if (BCrypt.checkpw(password, hashed))
//				System.out.println("It matches");
//			else
//				System.out.println("It does not match");
		 List<Account> returnInfo = accountRepo.findByUserName(userName);
		 System.out.println(returnInfo);

		return returnInfo;
	}
	
	
	
		
	@RequestMapping(value="account/login", method=RequestMethod.POST)
		public List<Account> login(HttpServletRequest request, HttpServletResponse response) throws IOException {
			StringBuffer jb = new StringBuffer();
			  String line = null;
			  try {
			    BufferedReader reader = request.getReader();
			    while ((line = reader.readLine()) != null)
			      jb.append(line);
			  } catch (Exception e) { /*report an error*/ }
			 String thing = jb.toString();
			 JSONObject obj = new JSONObject(thing);
			 String userName = obj.getString("userName");
			 String password = obj.getString("password");
			 List<Account> cookie = (List<Account>) accountRepo.findByUserName(userName);
			 List<Account> rejection = null;
			 String pw = cookie.get(0).password;
			 if (BCrypt.checkpw(password, pw))
			 		return cookie;		
			 	else
			 		return rejection;
	}
	
	
	
	
	@RequestMapping("/balance/{id}")
	public float getBalance(@PathVariable("id") String id) throws IOException{
		Account account = accountRepo.findOne(id);
		return account.balance;
		
	}
	
	
	

    @RequestMapping("/account/{id1}/{id2}")
    public String[] accountName(@PathVariable("id1") int id, @PathVariable("id2") int id1) throws IOException{
    	System.out.println(id+id1);
    	int sum = id+id1;
    	Stock stock = YahooFinance.get("GOOG");
    	BigDecimal price = stock.getQuote(true).getPrice();
    	System.out.println("The current price of GOOGLE is:" +price);
    	String[] results = {price.toString(), Integer.toString(sum)}; 
    	return results;
    }
    
    
	@RequestMapping(value="/account/trial" , method=RequestMethod.POST)
    public String[] doStuff(HttpServletRequest request, HttpServletResponse response) throws IOException{

			 StringBuffer jb = new StringBuffer();
			  String line = null;
			  try {
			    BufferedReader reader = request.getReader();
			    while ((line = reader.readLine()) != null)
			      jb.append(line);
			  } catch (Exception e) { /*report an error*/ }
			 String[] json= {jb.toString()};
			 System.out.println(json);
			 System.out.println(jb);
			 String thing = jb.toString();
			 response.setContentType("application/json");
			 JSONObject obj = new JSONObject(thing);
//			 String firstName = obj.getString("name");
			 System.out.println(obj.getString("name"));
//			 repository.save(new Member(firstName, lastName));
			 for (Member person : memberRepo.findAll()) {
					System.out.println(person);
				}
    	return json;
      }
    	
    
	

    
    @RequestMapping("/account/people")   
    public List<String> accountPeople(){
		List<String> x = new ArrayList<String>();	
    	for (Member person : memberRepo.findAll()) {
    		x.add(person.toString());
		}
		return x;
    }
    
    
}
