package hello.Models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {
	@Id public String id;
	
	public String userName;
	public String password;
	public ArrayList<Integer> membersId;
	
	public Account(){}
	
	public Account(String userName, String password){
		this.userName = userName;
		this.password = password;
		this.membersId = new ArrayList<Integer>();
	}
	
	
	 @Override
	  public String toString() {
	     return String.format(
//	           "Account[id='%s', userName='%s', accountId='%4.3f', balance='%d%n' ]",
	           id, userName, membersId);
	 }

}
