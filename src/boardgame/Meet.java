package boardgame;
import java.sql.Timestamp;

/**
 * 
 */

/**
 * @author terry
 *
 */
public class Meet {
	
	private int meetupID, gameID, creatorID, capacity, currPlayers;
	private String location;
	private String meetTime;
	private String frequency;
	private String gameName;
	
	public Meet() {
		
	}

	public Meet(int meetupID, int gameID, int creatorID, int capacity, int currPlayers, String location,
			String meetTime, String frequency, String gameName) {
		super();
		this.meetupID = meetupID;
		this.gameID = gameID;
		this.creatorID = creatorID;
		this.capacity = capacity;
		this.currPlayers = currPlayers;
		this.location = location;
		this.meetTime = meetTime;
		this.frequency = frequency;
		this.gameName = gameName;
	}

	public int getMeetupID() {
		return meetupID;
	}

	public void setMeetupID(int meetupID) {
		this.meetupID = meetupID;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(int creatorID) {
		this.creatorID = creatorID;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCurrPlayers() {
		return currPlayers;
	}

	public void setCurrPlayers(int currPlayers) {
		this.currPlayers = currPlayers;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(String meetTime) {
		this.meetTime = meetTime;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
