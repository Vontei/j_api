package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	public PersonRepository repository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping(value="/greeting", method=RequestMethod.POST)
//    public void newGreeting(@RequestParam(value="name", defaultValue="World") String name) {
//    	repository.save(new Person(name, "BANANAS"));
//    	
//    }
    
    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    
    @RequestMapping("/hello")
    public String hello(){
		repository.save(new Person("NICKY","BADADUCCI"));
		for (Person person : repository.findAll()) {
			System.out.println(person);
		}
    	return "HELLO THERE";
    }
    
    
}
