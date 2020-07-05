package am.tech42.common;
 
 import java.rmi.Remote;
 import java.rmi.RemoteException;
 import java.util.List;

public interface ShoppingService extends Remote{

	List<Product> getProductList() throws RemoteException;
	List<Product> getOrder(int shopingCard) throws RemoteException;
	boolean buyProduct(int shopingCard,String productName, int count) throws RemoteException;
	Integer getShoppingCard() throws RemoteException;
	void checkout(int shopingCard)throws RemoteException;
}