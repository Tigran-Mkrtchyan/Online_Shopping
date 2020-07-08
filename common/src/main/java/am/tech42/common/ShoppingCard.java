package am.tech42.common;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class ShoppingCard implements Serializable{

	private  Map<Product,Integer> products;

	public ShoppingCard(){
		products = new HashMap<>();
	}

	public int getTotalPrice(){
		int totalPrice = 0;
		for (Map.Entry<Product,Integer> productRow : products.entrySet()){
			totalPrice += productRow.getKey().getPrice() * productRow.getValue();
		}
		return totalPrice;
	}

	public Map<Product,Integer> getProducts(){
		return products;
	}
	public void setProducts(Map<Product,Integer> products){
		this.products = products;
	}

	@Override
	public String toString(){
		String result = "=====Product==========Price=========Quantity=====\n"+
						 "-------------------------------------------------\n";
		for (Map.Entry<Product,Integer> productRow : products.entrySet()){
			result += "|\t" + productRow.getKey().getName() +
			 "\t|\t"+  productRow.getKey().getPrice()  +
			  "\t|\t"+  productRow.getValue() +"\t|\n" +
			"-------------------------------------------------\n";
		}
		result +="============== " + "total price - "+ getTotalPrice() +" ===============\n";
		return result;
	}


}