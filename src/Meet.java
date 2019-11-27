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
	private Timestamp meetTime;
	private String frequency;
	
	public Meet() {
		
	}

	public Meet(int meetupID, int gameID, int creatorID, int capacity, int currPlayers, String location,
			Timestamp meetTime, String frequency) {
		super();
		this.meetupID = meetupID;
		this.gameID = gameID;
		this.creatorID = creatorID;
		this.capacity = capacity;
		this.currPlayers = currPlayers;
		this.location = location;
		this.meetTime = meetTime;
		this.frequency = frequency;
	}
	
}
