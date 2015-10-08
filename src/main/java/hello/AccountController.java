package hello;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.MongoClient;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//
//import com.mongodb.BasicDBObject;
//import com.mongodb.BulkWriteOperation;
//import com.mongodb.BulkWriteResult;
//import com.mongodb.Cursor;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.DBObject;
//import com.mongodb.MongoClient;


@RestController
@EnableMongoRepositories
public class AccountController {
	@Autowired
	public PersonRepository repository;

//	MongoClient mongoClient = new MongoClient( "localhost/grockr" );



    @RequestMapping("/account/{id1}/{id2}")
    public String[] accountName(@PathVariable("id1") int id, @PathVariable("id2") int id1) throws IOException{
    	System.out.println(id+id1);
    	int sum = id+id1;
    	Stock stock = YahooFinance.get("GOOG");
    	BigDecimal price = stock.getQuote(true).getPrice();
    	System.out.println("The current price of GOOGLE is:" +price);
    	repository.save(new Person("WORK PLEASE","Longbottom"));
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
			 System.out.println(jb);
			 String thing = jb.toString();
			 response.setContentType("application/json");
			 JSONObject obj = new JSONObject(thing);
			 String firstName = obj.getString("name");
			 String lastName = "LASTNAME";
			 System.out.println(obj.getString("name"));
			 repository.save(new Person(firstName, lastName));

    	return json;
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
