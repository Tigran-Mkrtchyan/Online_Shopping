package am.tech42.server;

import am.tech42.common.Product;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

class DBManager{

	public static List<Product> getProducts(){
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
	
}