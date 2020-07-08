package am.tech42.server;


import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;


public class Server{

	public static void main(String [] args){
	
		try{
			Registry registry= LocateRegistry.createRegistry(1099);
			OnlineShop shop = new OnlineShop( );
			registry.bind("shop1", shop);
		} catch(RemoteException e){
			throw new RuntimeException();
		} catch (AlreadyBoundException e){
			throw new RuntimeException();
		}
	}
}