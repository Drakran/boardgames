package boardgame;

public class Game {
	
	private String gameName;

	public Game() {
		
	}
	
	public Game(String game) {
		gameName = game;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
}
