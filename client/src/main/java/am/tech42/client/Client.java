package am.tech42.client;

import java.util.List;
import java.util.UUID;
import java.util.Scanner;
import java.rmi.Naming;
import am.tech42.common.*;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;


public class Client{
	private static ShoppingService shop;
	private static boolean running = true;
	private static Scanner scaner;
	private static UUID shoppingCardId;


	public static void main (String [] args){
		try{
			shop = (ShoppingService) Naming.lookup("rmi://127.0.0.1:1099/shop1");
			UUID shoppingCardId = shop.openSession();
			scaner = new Scanner(System.in);
			while(running){
				System.out.println("(L) product list");
				System.out.println("(A) add product in shopping card");
				System.out.println("(O) get shopping card");
				System.out.println("(C) checkout");
				System.out.println("(Q) quit the application");

				String command = scaner.nextLine(); 
				switch(command.toLowerCase()){
					case "l":
						getProductList();
						break;
					case "a":
						addProductToShoppingCard();
						break;
					case "o":
						getSoppingcard();
						break;
					case "c":
						checkout();
						break;
					case "q":
						checkout();
						running = false;
						break;	
				}						
			}
		
		}catch (RemoteException e){
			e.printStackTrace();
		}catch (NotBoundException e){
			throw new RuntimeException("Wrong URL or not bound a server");
		}catch (MalformedURLException e){
			throw new RuntimeException("not right URL sintaxis");
		}

	}
	public static void getProductList() throws RemoteException{
		List<Product> products = shop.getProductList();
		System.out.println("====================products====================");
		for (Product product :products ){
			System.out.println(product);
			System.out.println("------------------------------------------------");

		}
	}
	public static void addProductToShoppingCard()throws RemoteException{
		System.out.println("Enter product's Id");
		int productId = Integer.parseInt( scaner.nextLine()); 
		System.out.println("Enter quantity");
		int quantity = Integer.parseInt( scaner.nextLine());
		try{
			shop.addProductToShoppingCard(shoppingCardId, productId, quantity);
		}catch (IllegalArgumentException e){
			System.out.println("Incorrect entered product Id");
			addProductToShoppingCard();
		}
			
	}
	public static void getSoppingcard()throws RemoteException{

		ShoppingCard order = shop.getSoppingcard(shoppingCardId);
		System.out.println(order);

	}
	public static void checkout()throws RemoteException{
		shop.checkout(shoppingCardId);
	}
	

}