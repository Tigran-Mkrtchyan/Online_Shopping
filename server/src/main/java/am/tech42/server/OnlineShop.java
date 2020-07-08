package am.tech42.server;

import am.tech42.common.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;



public class OnlineShop extends UnicastRemoteObject implements ShoppingService{
	private static List<Product> products = Arrays.asList(
		new Product("cup", 500),
		new Product("pen", 250),
		new Product("table", 1000)
		);
	private static Map<UUID,ShoppingCard> shoppingCards;

	public OnlineShop ()throws RemoteException{
		shoppingCards = new HashMap<>();
	}

	@Override
	public List<Product> getProductList() {
		return products;
	}

	@Override
	public ShoppingCard getSoppingcard(UUID sessionId){
		return shoppingCards.get(sessionId);
	}

	@Override
	public void addProductToShoppingCard(UUID sessionId,int productId, int qty) {
		Product product = getProduct(productId);
		if (product == null){
			throw new IllegalArgumentException();
		}
		ShoppingCard shopingCard = shoppingCards.get(sessionId);
		if(shopingCard == null){
			shopingCard = new ShoppingCard();
		}
		Map <Product, Integer> products = shopingCard.getProducts();
		if(products.get(product) != null){
			qty += products.get(product);
		}
		products.put(product,qty);
		shoppingCards.put(sessionId,shopingCard);
	}
	@Override
	public UUID openSession() {
		return UUID.randomUUID();
	}

	@Override
	public void checkout(UUID sessionId){
		shoppingCards.get(sessionId).setProducts(null);
		shoppingCards.remove(sessionId);
	}	

	private Product getProduct (int productId){
		for (Product product : products){
			if(product.getId() == productId ){
				return product;
			}
		}
		return null;
	}
	
}