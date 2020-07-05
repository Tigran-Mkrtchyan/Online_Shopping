package am.tech42.server;

import am.tech42.common.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;
import java.util.Map;
import java.util.HashMap;

public class Server{

	public static void main(String [] args){
		Product cup = new Product("cup", 500);
		Product pen = new Product("pen", 250);
		Product table =new Product("table", 1000);
		try{
			Registry registry= LocateRegistry.createRegistry(1099);
			OnlineShop shop = new OnlineShop( );
			shop.addProducts(cup, pen, table);
			registry.bind("shop1", shop);
		} catch(RemoteException e){
			throw new RuntimeException();
		} catch (AlreadyBoundException e){
			throw new RuntimeException();
		}
	}
}