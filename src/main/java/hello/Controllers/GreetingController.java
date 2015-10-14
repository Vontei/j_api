package hello.Controllers;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.tomcat.util.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.api.client.json.JsonObjectParser;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mongodb.util.JSON;

import hello.Models.Greeting;
import hello.Models.Member;
import hello.Repositories.MemberRepository;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class GreetingController {
	public MemberRepository repository;

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();


    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    
    @RequestMapping("/hello")
    public String hello(){
//		repository.save(new Member("NICKY","BADADUCCI"));
		for (Member member : repository.findAll()) {
			System.out.println(member);
		}
    	return "HELLO THERE";
    }
    
    
    @RequestMapping(value="/alchemy" , method=RequestMethod.GET)
    @JsonProperty
    public String all() throws UnirestException, URISyntaxException, MalformedURLException{

        String myUrl = "https://access.alchemyapi.com/calls/data/GetNews?apikey=YOUR API KEY&return=enriched.url.title&start=1444003200&end=1444690800&q.enriched.url.enrichedTitle.entities.entity=|text=IBM,type=company|&q.enriched.url.enrichedTitle.docSentiment.type=positive&q.enriched.url.enrichedTitle.taxonomy.taxonomy_.label=technology%20and%20computing&count=25&outputMode=json";
        URL url = new URL(myUrl);
        String nullFragment = null;
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), nullFragment);
        String newURI = uri.toString();
    	com.mashape.unirest.http.HttpResponse<JsonNode> request = Unirest.get(newURI).asJson();
     	System.out.println(request.toString());
     	System.out.println(request.getBody());
     	JSONObject obj = new JSONObject(request);
     	System.out.println(obj);
		return obj.toString();
    }
    
    

    
    @RequestMapping(value="/API", method=RequestMethod.GET)
    @JsonProperty   
    public String alchemyCall() throws UnirestException, IOException{
    	com.mashape.unirest.http.HttpResponse<JsonNode> req = Unirest.get("http://www.omdbapi.com/?s=disney").asJson();
     	Object answer = req.getBody();
     	String other = answer.toString();
		JSONObject obj = new JSONObject(other);
		System.out.println(obj);
		
		
		Stock tesla = YahooFinance.get("TSLA", true);
		System.out.println(tesla.getHistory());
		return other;

    }
    
    		
    
}
