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
	private String description;
	
	public Meet() {
		
	}

	public Meet(int meetupID, int gameID, int creatorID, int capacity, int currPlayers, String location,
			String meetTime, String frequency, String description) {
		super();
		this.meetupID = meetupID;
		this.gameID = gameID;
		this.creatorID = creatorID;
		this.capacity = capacity;
		this.currPlayers = currPlayers;
		this.location = location;
		this.meetTime = meetTime;
		this.frequency = frequency;
		this.description = description;
	}
	
}
