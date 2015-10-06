package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	
	//create account add to //sign up post
	//post to account
    @RequestMapping("/account")
    public String accountName(){
    	return "BRAND NEW ACCOUNT";
    }
    
    //add members to account
    //post to addMember
    //updates member
    
    
    
}
