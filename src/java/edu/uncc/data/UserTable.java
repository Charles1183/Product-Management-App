package edu.uncc.data;

import java.io.*;
import java.util.*;
import edu.uncc.nbad.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserTable {
	
    String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";

    static Connection connection = null;
    static PreparedStatement selectUser = null;
    static ResultSet resultset = null;
    
    //Static initializer, it runs when the class is intialized (it is executed once)
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
    
    public static boolean exists(String email) { //check in the user table to see if the user's email is already tied to a registration
        boolean result = false;
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement statement = connection.createStatement();
            ResultSet userEmailResult = statement.executeQuery("select * from user " + "where email = " + email);
            
            
            if(userEmailResult.next()){
                result=true;
            }
                        
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        return result;
    }
    
    public static void addRecord(User user) throws IOException {
        String query = "insert into user (firstName, lastName, email, password) "+
                 "values ('"+user.getFirstName()+"', " + 
                         "'"+user.getLastName()+"', "+
                         "'"+user.getEmail()+"', "+
                         "'"+user.getPassword()+"');";
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            if (exists(user.getEmail()) == false){ //only add the user if the provided email isn't in database
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            }
            
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
    }

    public static User getUser(String emailAddress) throws IOException {
        User user = new User();
        String query = "select * from user where email = '" + emailAddress + "';";
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement statement = connection.createStatement();
            ResultSet userResult = statement.executeQuery(query);
            
            while (userResult.next()){
                user.setFirstName(userResult.getString("FirstName"));
                user.setLastName(userResult.getString("LastName"));
                user.setEmail(userResult.getString("Email"));
                user.setPassword(userResult.getString("Password"));
            }
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        return user;
    }

    public static ArrayList<User> getUsers() throws IOException {
        User user = new User();
        ArrayList<User> userList = new ArrayList<>();
        
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement statement = connection.createStatement();
            ResultSet usersResult = statement.executeQuery("select * from user;");
            
            while (usersResult.next()){
                user.setFirstName(usersResult.getString("FirstName"));
                user.setLastName(usersResult.getString("LastName"));
                user.setEmail(usersResult.getString("Email"));
                user.setPassword(usersResult.getString("Password"));
                userList.add(user);
            }
        }catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        return userList;
    }

    public static HashMap<String, User> getUsersMap() throws IOException {
        User user = new User();
        HashMap<String, User> usersMap = new HashMap<>();
        try{
            String dbURL = "jdbc:mysql://localhost:3306/jspteam";
            String username = "root";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            
            Statement statement = connection.createStatement();
            ResultSet usersResult = statement.executeQuery("select * from user;");
            
            while (usersResult.next()){
                user.setFirstName(usersResult.getString("FirstName"));
                user.setLastName(usersResult.getString("LastName"));
                user.setEmail(usersResult.getString("Email"));
                user.setPassword(usersResult.getString("Password"));
                usersMap.put(user.getEmail(), user);
            }            
        } catch (SQLException e){
            for (Throwable t : e)
                t.printStackTrace();
        }
        
        return usersMap;
    }
}