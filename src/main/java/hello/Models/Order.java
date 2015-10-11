package hello.Models;

import org.springframework.data.annotation.Id;


public class Order {

	@Id public String id;
	
	public int memberId;
	public String stock;
	public int buyPrice;
	public int sellPrice;
	public int qty;
	public int profitLoss;
	
	public Order(){};
	
	public Order(int memberId, String stock,int buyPrice, int sellPrice, int qty,int profitLoss){
		this.memberId = memberId;
		this.stock = stock;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.qty = qty;
		this.profitLoss = profitLoss;
	}
	
}
