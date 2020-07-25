package am.tech42.server;

import am.tech42.common.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

public class OnlineShop extends UnicastRemoteObject implements ShoppingService{
	
	
	private static Map<UUID,ShoppingCard> shoppingCards;

	public OnlineShop ()throws RemoteException{
		shoppingCards = new HashMap<>();
	}

	@Override
	public List<Product> getProductList() {
		return DBManager.getProducts();
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
		Map <Product, Integer> basket = shopingCard.getBasket();
		if(basket.get(product) != null){
			qty += basket.get(product);
		}
		basket.put(product,qty);
		shoppingCards.put(sessionId,shopingCard);
	}
	@Override
	public UUID openSession() {
		return UUID.randomUUID();
	}

	@Override
	public void checkout(UUID sessionId){
		shoppingCards.remove(sessionId);
	}	

	private Product getProduct (int productId){
		for (Product product : DBManager.getProducts()){
			if(product.getId()==productId){
				return product;
			}
		}
		return null;
	}
	
}