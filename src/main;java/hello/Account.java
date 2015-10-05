package hello;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {
	@Id private String id;
	
	public String name;
	public String password;
	
	public Account(){}
	
	public Account(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	


}
