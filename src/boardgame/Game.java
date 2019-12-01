package boardgame;

public class Game {
	
	private String gameName;
	private int gameID;

	public Game() {
		
	}
	
	public Game(String game, int id) {
		gameName = game;
		gameID = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
}
