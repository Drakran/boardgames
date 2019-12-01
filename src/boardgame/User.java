package boardgame;
import java.util.ArrayList;
import java.util.List;

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
	private List<Meet> meetups = new ArrayList<>();
	private List<Game> owned = new ArrayList<>();
	private List<Game> wish = new ArrayList<>();


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
	public List<Meet> getMeetups() {
		return meetups;
	}

	public void setMeetups(List<Meet> meetups) {
		this.meetups = meetups;
	}

	public List<Game> getOwned() {
		return owned;
	}

	public void setOwned(List<Game> owned) {
		this.owned = owned;
	}

	public List<Game> getWish() {
		return wish;
	}

	public void setWish(List<Game> wish) {
		this.wish = wish;
	}

	//Adds a wishlist string to wish
	public void addWish(Game wishGame) {
		wish.add(wishGame);
	}
	
	//Adds an owned game to owned
	public void addOwned(Game ownedGame) {
		owned.add(ownedGame);
	}
	
	public void addMeet(Meet meet) {
		meetups.add(meet);
	}

}
