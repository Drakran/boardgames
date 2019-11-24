/**
 * accessData 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class accessData {
	
	private String cred = "jdbc:mysql://google/boardgames?cloudSqlInstance="
			+ "cobalt-deck-255423:us-central1:boardgame&"
			+ "socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false"
			+ "&user=username&password=password";
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
    public int validateUser(User user) {    	
    	open();
    	/*
    	 * 0 if username doesn't exist
    	 * 1 if username exist but password wrong
    	 * 2 if correct
    	 */
    	int userStatus = 0;
    	try {
    		//Check if username exists
    		PreparedStatement usernameExist = conn.prepareStatement("select username from Users "
    				+ "where username = ?");
    		usernameExist.setString(1, user.getUsername());
    		rs = usernameExist.executeQuery();
    		//rs.next is true if there is a user with the name
    		if(rs.next()) {
    			userStatus = 1; 
    		}
    		
    		//Check if password exist
    		PreparedStatement userCorrect = conn.prepareStatement("select username,password from Users "
    				+ "where username = ? and password = ?");
    		userCorrect.setString(1, user.getUsername());
    		userCorrect.setString(2, user.getPassword());
    		rs = userCorrect.executeQuery();
    		//returns true if there is a user with that password and username
    		if(rs.next()) {
    			userStatus = 2;
    		}
    	}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
    	return userStatus; 	
    }
    
    public int registerUser(User user, String cPassword) {
    	open();
    	/*
    	 * 0 if username already taken
    	 * 1 if passwords don't match
    	 * 2 if correct
    	 * 3 if username isn't put in
    	 * 4 if password isn't put in
    	 */
    	int userStatus = 2;
    	try {
    		
    		//If username isn't put in
    		if(user.getUsername() == null || user.getUsername().equals("")) {
    			userStatus = 3;
    			return userStatus;
    		}
    		//If password isn't put in
    		if(user.getPassword() == null || user.getPassword().equals("")) {
    			userStatus = 4;
    			return userStatus;
    		}
    		
    		//Check if username exists
    		PreparedStatement usernameExist = conn.prepareStatement("select username from User "
    				+ "where username = ?");
    		usernameExist.setString(1, user.getUsername());
    		rs = usernameExist.executeQuery();
    		//rs.next is true if there is a user with the name
    		if(rs.next()) {
    			userStatus = 0; 
    			return userStatus;
    		}
    		    		
    		
    		//sets userStatus to 1 if passwords do not match if they don't match
    		if(!(user.getPassword().equals(cPassword))) {
    			userStatus = 1;
    			return userStatus;
    		}
    		System.out.println(user.getPassword());
    		System.out.println(cPassword);
    		
    		//Insert into
    		PreparedStatement userInsert = conn.prepareStatement("Insert into Users(username,password)"
    				+ "values (?,?)");
    		userInsert.setString(1, user.getUsername());
    		userInsert.setString(2, user.getPassword());
    		int affect = userInsert.executeUpdate();
    		//Userstatus should default to 2 here
    	}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
    	return userStatus; 	
    }
	
    //Standard Open connection method
	private void open() {		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection(cred);
			 st = conn.createStatement();
		 }
		catch (SQLException sqle) {
			 System.out.println (sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			 System.out.println (cnfe.getMessage());
		} finally {
			}
	}
	
	//Standard Close connection method
	private void close() {
		 try {
			 if (rs != null) { rs.close(); }
			 if (st != null) { st.close(); }
			 if (conn != null) { conn.close(); }
		 } catch (SQLException sqle) {
			 System.out.println(sqle.getMessage());			 
		 }
	}

}
