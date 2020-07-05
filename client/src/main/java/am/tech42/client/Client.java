package am.tech42.client;

import java.util.List;
import java.rmi.Naming;
import am.tech42.common.*;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;




public class Client{

	public static void main (String [] args){
		try{
			ShoppingService shop = (ShoppingService) Naming.lookup("rmi://127.0.0.1:1099/shop1");
			int shoppingCardId = shop.getShoppingCard();

			List<Product> products = shop.getProductList();

			shop.buyProduct(shoppingCardId,"cup",2);
			shop.buyProduct(shoppingCardId,"pen",3);
			shop.buyProduct(shoppingCardId,"table",1);

			List<Product> order = shop.getOrder(shoppingCardId); 
			for(Product product : order){
				System.out.println(product);
			}
		}catch (RemoteException e){
			e.printStackTrace();
		}catch (NotBoundException e){
			throw new RuntimeException("Wrong URL or not bound a server");
		}catch (MalformedURLException e){
			throw new RuntimeException("not right URL sintaxis");
		}

	}

}