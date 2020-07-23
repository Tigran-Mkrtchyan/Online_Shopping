package am.tech42.server;

import am.tech42.common.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OnlineShop extends UnicastRemoteObject implements ShoppingService{
	
	private List<Product> getProducts(){
		List<Product> productList = new ArrayList<>();
		try(
			Connection connection = DriverManager.getConnection("jdbc:postgresql://fm-toolbox.duckdns.org:5433/shop","test","test");
			Statement s = connection.createStatement();){
			ResultSet resultSet = s.executeQuery("select p.id,name,price from products p inner join product_details pd on p.id = pd.product_id");
			while (resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				int price = resultSet.getInt(3);
				productList.add(new Product(id,name,price));
			}
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		return	productList;
	}
	
	private static Map<UUID,ShoppingCard> shoppingCards;

	public OnlineShop ()throws RemoteException{
		shoppingCards = new HashMap<>();
	}

	@Override
	public List<Product> getProductList() {
		return getProducts();
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
		for (Product product : getProducts()){
			if(product.getId()==productId){
				return product;
			}
		}
		return null;
	}
	
}