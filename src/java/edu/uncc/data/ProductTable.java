package edu.uncc.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import edu.uncc.nbad.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class ProductTable {

    String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";

    static Connection connection = null;
    static PreparedStatement selectProduct = null;
    static ResultSet resultset = null;
	
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    public static List<Product> retrieveAllProducts() {
		//throw new NotImplementedException(); // remove this line and implement the logic
                List<Product> products = new ArrayList<>();
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement statement = connection.createStatement();
            ResultSet productIDResult = statement.executeQuery("select * from Product ");
            
            while (productIDResult.next()){
                Product product = new Product();
                product.setCode(productIDResult.getString("ProductCode"));
                //product.setName(productIDResult.getString("name"));
                product.setDescription(productIDResult.getString("ProductDescription"));
                //product.setBrand(productIDResult.getString("brand"));
                product.setPrice(productIDResult.getDouble("ProductPrice"));
                products.add(product);
            }            
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        return products;
    }

    public static Product retrieveProduct(String productCode) {
        //throw new NotImplementedException(); // remove this line and implement the logic
        Product result = null;
        Product product = new Product();
        String query = "select * from Product " + "where ProductCode = '"+ productCode+"';";
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement statement = connection.createStatement();
            ResultSet productIDResult = statement.executeQuery(query);
            
            while (productIDResult.next()){
                product.setCode(productIDResult.getString("ProductCode"));
                //product.setName(productIDResult.getString("name"));
                product.setDescription(productIDResult.getString("ProductDescription"));
                //product.setBrand(productIDResult.getString("brand"));
                product.setPrice(productIDResult.getDouble("ProductPrice"));
            }            
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        return product;
    }

    public static boolean exists(String productCode) {
        //throw new NotImplementedException(); // remove this line and implement the logic
        boolean result = false;
        String query = "select * from Product " + "where ProductCode = '"+productCode+"';";
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement statement = connection.createStatement();
            ResultSet productCodeResult = statement.executeQuery(query);
             
            if(productCodeResult.next()){
                result=true;
            }
                       
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        return result;
    }    

    public static void insertProduct(Product product) {
	//throw new NotImplementedException(); // remove this line and implement the logic
        //boolean exists = false;
        //String existanceCheck = "select * from products where code = " + product.getCode() + ";";
        //System.out.println("Product Code = "+product.getCode());
        String query = "insert into product (ProductCode, ProductDescription, ProductPrice) "+
                 "values ('"+product.getCode()+"', " + 
                         "'"+product.getDescription()+"', "+
                         "'"+product.getPrice()+"')";
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            //if (exists(product.getCode()) == false){
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            //}
            
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
    }

    public static void updateProduct(Product product) {
        //throw new NotImplementedException(); // remove this line and implement the logic
        String query = "update product set "+
                 "ProductCode = '"+product.getCode()+"', " + 
                 "ProductDescription = '"+product.getDescription()+"', "+
                 "ProductPrice = '"+product.getPrice()+"' "+
                 "where ProductCode = '"+product.getCode()+"'";
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            if (exists(product.getCode()) == true){
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            }
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
    }

    public static void deleteProduct(Product product) {
		//throw new NotImplementedException(); // remove this line and implement the logic
                String query = "delete from product "+
                         "where ProductCode = '" + product.getCode()+"'";
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            if (exists(product.getCode()) == true){
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            }
            
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
    }    
}