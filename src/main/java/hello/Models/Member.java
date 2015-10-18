package hello.Models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

public class Member {

	@Id
	public String id;

	public String name;
	public int accountId;
	public double balance;
	public ArrayList<Integer> orderIds;

	public Member() {
	}

	public Member(String name, int accountId) {
		this.name = name;
		this.accountId = accountId;
		this.balance = 1000000.00;
		this.orderIds = new ArrayList<Integer>();
	}

	@Override
	public String toString() {
		return String.format(
				// "Member[id='%s', name='%s', accountId='%4.3f', balance='%d%n'
				// ]",
				id, name, balance);
	}

}