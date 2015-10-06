package hello;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@RestController
@EnableMongoRepositories
public class AccountController {
	@Autowired
	public PersonRepository repository;

    @RequestMapping("/account")
    public String accountName(){
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
