package am.tech42.common;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable{
	private int id;
	private String name;
	private int price;
	
	public Product(int id , String name, int price){
		this.id = id;
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
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
		 if (o == null || getClass() != o.getClass()) return false;
		 Product product = (Product) o;
		 return id == product.id && Objects.equals(name, product.name);
	}

	@Override
	public int hashCode() {
	 	return Objects.hash(id, name);
	}
}