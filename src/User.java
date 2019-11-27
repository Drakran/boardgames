/*
 * The User will store the User
 * 
 * Terry Wang'
 * 
 * November 2nd, 2019
 */


public class User {
	
	private String username;
	private String password;
	private int id;
	public User() {
		
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username) {
		this.username = username;
		this.password = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}

}
