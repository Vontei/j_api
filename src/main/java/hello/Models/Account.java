package hello.Models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Account {
	@Id
	public String id;

	public String userName;
	public String password;
	public float balance;
	public ArrayList<Integer> membersId;

	public Account() {
	}

	public Account(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.balance = (float) 100000.00;
		this.membersId = new ArrayList<Integer>();
	}

	public void setBalance(float newBalance) {
		this.balance = newBalance;
	}

	@Override
	public String toString() {
		return String.format("Account[id='%s', userName='%s', password='%s']", id, userName, password, balance,
				membersId);
	}

}
