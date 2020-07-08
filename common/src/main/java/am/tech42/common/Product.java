package am.tech42.common;

import java.io.Serializable;

public class Product implements Serializable{
	private int id;
	private String name;
	private int price;
	private static int idSequence = 0;

	public Product(String name, int price){
		id = ++idSequence;
		this.name = name;
		this.price = price;
	}

	public String getName(){
		return name;
	}
	public int getPrice(){
		return price; 
	}
	public int getId(){
		return id; 
	}

	@Override
	public String toString(){
		return "|\t" +id + "\t|\t"+ name +  "\t|\t" +price + "\t|";
	}
}