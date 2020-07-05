package am.tech42.server;

import am.tech42.common.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class OnlineShop extends UnicastRemoteObject implements ShoppingService{
	private Map <String , Product> products;
	private Map <Integer , List<Product>> shoppingCards;

	public OnlineShop ()throws RemoteException{
		products = new HashMap<>();
		shoppingCards = new HashMap<>();
	}

	@Override
	public List<Product> getProductList() {
		return new ArrayList<Product>(products.values());
	}

	@Override
	public List<Product> getOrder(int shopingCard){
		List <Product> order = shoppingCards.get(shopingCard) ;
		return order;
	}

	@Override
	public boolean buyProduct(int shopingCard,String productName, int count){
		productName = productName.toLowerCase();
		if(products.get(productName) == null){
			return false;
		}
		List<Product> order = shoppingCards.get(shopingCard);
		for (int i = 0; i < count; i++){
			order.add(products.get(productName));
		}
		shoppingCards.put(shopingCard,order);
		return true;
	}

	@Override
	public Integer getShoppingCard(){
		Integer id = generateId();
		while (shoppingCards.get(id) != null){
			id = generateId();
		}
		shoppingCards.put(id, new ArrayList<>()); 
		return id;
	}

	@Override
	public void checkout(int shopingCard){
		shoppingCards.remove(shopingCard);
	}

	private Integer generateId(){
		return new Random().nextInt(10000);
	}

	public void  addProduct(Product product){
		products.put(product.getProductType().toLowerCase(),product);
	}

	public void  addProducts(List<Product> productlist){
		for(Product product : productlist){
			products.put(product.getProductType().toLowerCase(),product);
		}	
	}
	public void  addProducts(Product ... productlist){
		for(Product product : productlist){
			products.put(product.getProductType().toLowerCase(),product);
		}
	}	
	
}