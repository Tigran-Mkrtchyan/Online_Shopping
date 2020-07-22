package am.tech42.common;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class ShoppingCard implements Serializable{

	private  Map<Product,Integer> basket;

	public ShoppingCard(){
		basket = new HashMap<>();
	}

	public int getTotalPrice(){
		int totalPrice = 0;
		for (Map.Entry<Product,Integer> productRow : basket.entrySet()){
			totalPrice += productRow.getKey().getPrice() * productRow.getValue();
		}
		return totalPrice;
	}

	public Map<Product,Integer> getBasket(){
		return basket;
	}
	public void setBasket(Map<Product,Integer> basket){
		this.basket = basket;
	}

	@Override
	public String toString(){
		String result = "=========Product==============Price=========Quantity====\n"+
						 "--------------------------------------------------------\n";
		for (Map.Entry<Product,Integer> productRow : basket.entrySet()){
			result += "|\t" + productRow.getKey().getName() +
			 "\t|\t"+  productRow.getKey().getPrice()  +
			  "\t|\t"+  productRow.getValue() +"\t|\n" +
			"--------------------------------------------------------\n";
		}
		result +="============== " + "total price - "+ getTotalPrice() +" ===============\n";
		return result;
	}


}