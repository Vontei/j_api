package hello.Models;

import org.springframework.data.annotation.Id;

public class Order {

	@Id
	public String id;

	public String memberId;
	public String stock;
	public String buyPrice;
	public String sellPrice;
	public String qty;

	public Order() {
	};

	public Order(String memberId, String stock, String buyPrice, String qty, String sellPrice) {
		this.memberId = memberId;
		this.stock = stock;
		this.buyPrice = buyPrice;
		this.qty = qty;
		this.sellPrice = sellPrice;
	}

}
