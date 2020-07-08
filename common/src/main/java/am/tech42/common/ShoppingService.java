package am.tech42.common;
 
 import java.rmi.Remote;
 import java.rmi.RemoteException;
 import java.util.List;
 import java.util.UUID;

public interface ShoppingService extends Remote{

	List<Product> getProductList() throws RemoteException;
	ShoppingCard getSoppingcard(UUID sessionId) throws RemoteException;
	void addProductToShoppingCard(UUID sessionId,int productId, int qty) throws RemoteException;
	UUID openSession() throws RemoteException;
	void checkout(UUID sessionId)throws RemoteException;
}