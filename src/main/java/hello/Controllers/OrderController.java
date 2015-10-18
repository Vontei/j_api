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

import hello.Models.Account;
import hello.Models.Member;
import hello.Models.Order;
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
		@Autowired
		public OrderRepository orderRepo;
		@Autowired
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
	    
	    
	    
	    @RequestMapping(value="/buy", method=RequestMethod.POST)
	    public void buyStock(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
			 String orderid = obj.getString("id");
			 String price = obj.getString("price");
			 String stock = obj.getString("stock");
			 String qty = obj.get("qty").toString();
			 orderRepo.save(new Order(orderid, stock, price, qty, "0"));
			 Account account = accountRepo.findOne(orderid);
			 Float priceInt = Float.valueOf(price);
			 Float qtyInt = Float.valueOf(qty);
			 Float updatedBalance = (float) ((account.balance)-(priceInt*qtyInt));
			 account.setBalance(updatedBalance);
			 accountRepo.save(account);
			 
	    }
	    
	    
	    
	    
	    @RequestMapping("/orders/{id}")
		public List<Order> myOrders(@PathVariable("id") String id) throws IOException{
	    	List<Order> orders = orderRepo.findBymemberId(id);	    	
	    	return orders;
	    }
	    
	    
	    @RequestMapping(value="/sell", method=RequestMethod.POST)
	    public List<Order> sellStock(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
			 String orderid = obj.getString("orderid");
			 String accountid = obj.getString("accountid");
			 Float price = (float) obj.getInt("balance");
//			 boolean profit = obj.getBoolean("profit");
			 Account thisAccount = accountRepo.findOne(accountid);
	    	 Float newBalance = Float.valueOf(price);
//	    	 if(profit == true){
//				 Float balance = (thisAccount.balance + newBalance);
//			    thisAccount.setBalance(balance);
//
//			 } else {
//				 Float balance = (thisAccount.balance - newBalance);
//				 thisAccount.setBalance(balance);
//			 }
				 
	    	Float total = thisAccount.balance + newBalance; 
			thisAccount.setBalance(total);

	    	orderRepo.delete(orderid);
	    	accountRepo.save(thisAccount);
	    	
	    	List<Order> orders = orderRepo.findBymemberId(accountid);	    	
	    	return orders;
	    	
	    }
	    
	
}
