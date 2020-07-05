package am.tech42.common;

import java.io.Serializable;

public class Product implements Serializable{
	private String productType;
	private int productPrice;

	public Product(String productType, int productPrice){
		this.productType = productType;
		this.productPrice = productPrice;
	}

	public String getProductType(){
		return productType;
	}
	public int getProductPrice(){
		return productPrice; 
	}

	@Override
	public String toString(){
		return productType + "'s price is " + productPrice;
	}
}