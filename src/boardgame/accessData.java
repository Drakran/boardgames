package boardgame;

/**
 * accessData 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class accessData {
	//user = root
	//password = csci201
	private String cred = "jdbc:mysql://google/boardgame?cloudSqlInstance=inner-precept-256219:us-central1:sql-db-1&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=root&password=csci201";

	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;

	//Validates the user mate
	public int validateUser(User user) {
		open();
		/*
		 * 0 if username doesn't exist 1 if username exist but password wrong 2 if
		 * correct
		 */
		int userStatus = 0;
		try {
			// Check if username exists
			PreparedStatement usernameExist = conn
					.prepareStatement("select username from users " + "where username = ?");
			usernameExist.setString(1, user.getUsername());
			rs = usernameExist.executeQuery();
			// rs.next is true if there is a user with the name
			if (rs.next()) {
				userStatus = 1;
			}

			// Check if password exist
			PreparedStatement userCorrect = conn
					.prepareStatement("select username,password from users " + "where username = ? and password = ?");
			userCorrect.setString(1, user.getUsername());
			userCorrect.setString(2, user.getPassword());
			rs = userCorrect.executeQuery();
			// returns true if there is a user with that password and username
			if (rs.next()) {
				userStatus = 2;
				PreparedStatement getUser = conn
						.prepareStatement("SELECT * from users WHERE username = ? AND password = ?");
				getUser.setString(1, user.getUsername());
				getUser.setString(2, user.getPassword());
				rs = getUser.executeQuery();
				rs.next();
				user.setID(rs.getInt("userID"));
				// Userstatus should default to 2 here
				String ownurl = "https://www.boardgamegeek.com/xmlapi2/collection?username=" + user.getUsername()
						+ "&subtype=boardgame&own=1";
				String wishurl = "https://www.boardgamegeek.com/xmlapi2/collection?username=" + user.getUsername()
						+ "&subtype=boardgame&wishlist=1";
				PreparedStatement gameInsert = conn
						.prepareStatement("Insert into games(gameName, imgLink) values (?,?)");
				String xmlString = null;
				try {
					xmlString = createXMLStringFromURL(ownurl);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Document doc = convertStringToXMLDocument(xmlString);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("item");
				for (int i = 0; i < nList.getLength(); i++) {
					Node n = nList.item(i);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) n;
						PreparedStatement findBG = conn.prepareStatement("SELECT * from games WHERE gameName = ?");
						findBG.setString(1, eElement.getElementsByTagName("name").item(0).getTextContent());
						rs = findBG.executeQuery();
						if (rs.next() == false) {
							gameInsert.setString(1, eElement.getElementsByTagName("name").item(0).getTextContent());
							gameInsert.setString(2, eElement.getElementsByTagName("image").item(0).getTextContent());
							gameInsert.executeUpdate();
						}
						rs = findBG.executeQuery();
						rs.next();
						int boardID = rs.getInt("gameID");
						PreparedStatement ownSearch = conn
								.prepareStatement("SELECT * from owned WHERE userID = ? AND gameID = ?");
						ownSearch.setInt(1, user.getID());
						ownSearch.setInt(2, boardID);
						rs = ownSearch.executeQuery();
						if (rs.next() == false) {
							PreparedStatement addOwned = conn
									.prepareStatement("INSERT into owned(userID, gameID) values(?,?)");
							addOwned.setInt(1, user.getID());
							addOwned.setInt(2, boardID);
							addOwned.executeUpdate();

						}
						Game g = new Game(eElement.getElementsByTagName("name").item(0).getTextContent(),boardID);
						user.addOwned(g);
					}
				}

				try {
					xmlString = createXMLStringFromURL(wishurl);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Document doc2 = convertStringToXMLDocument(xmlString);
				doc2.getDocumentElement().normalize();
				NodeList nList2 = doc2.getElementsByTagName("item");
				for (int i = 0; i < nList2.getLength(); i++) {
					Node n = nList2.item(i);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) n;
						PreparedStatement findBG = conn.prepareStatement("SELECT * from games WHERE gameName = ?");
						findBG.setString(1, eElement.getElementsByTagName("name").item(0).getTextContent());
						rs = findBG.executeQuery();
						if (rs.next() == false) {
							gameInsert.setString(1, eElement.getElementsByTagName("name").item(0).getTextContent());
							gameInsert.setString(2, eElement.getElementsByTagName("image").item(0).getTextContent());
							gameInsert.executeUpdate();
						}
						rs = findBG.executeQuery();
						rs.next();
						int boardID = rs.getInt("gameID");
						PreparedStatement ownSearch = conn
								.prepareStatement("SELECT * from wish WHERE userID = ? AND gameID = ?");
						ownSearch.setInt(1, user.getID());
						ownSearch.setInt(2, boardID);
						rs = ownSearch.executeQuery();
						if (rs.next() == false) {
							PreparedStatement addWish = conn
									.prepareStatement("INSERT into wish(userID, gameID) values(?,?)");
							addWish.setInt(1, user.getID());
							addWish.setInt(2, boardID);
							addWish.executeUpdate();
						}
						Game g = new Game(eElement.getElementsByTagName("name").item(0).getTextContent(),boardID);
						user.addWish(g);
					}
				}
				PreparedStatement findMeet = conn.prepareStatement("SELECT * from usersMeetup WHERE userID = ?");
				findMeet.setInt(1,user.getID());
				rs = findMeet.executeQuery();
				ResultSet otherRS;
				while(rs.next()) {
					PreparedStatement findMeetup = conn.prepareStatement("SELECT * from meetups WHERE meetupID = ?");
					findMeetup.setInt(1,rs.getInt("meetupID"));
					otherRS = findMeetup.executeQuery();
					while(otherRS.next()) {
						System.out.println("test inside accessData meetup");
						ResultSet otherOtherRS; 
						PreparedStatement findName = conn.prepareStatement("SELECT * from games WHERE gameID = ?");
						findName.setInt(1, otherRS.getInt("gameID"));
						otherOtherRS = findName.executeQuery();
						otherOtherRS.next();
						String gameName = otherOtherRS.getString("gameName");
						String gameImage = otherOtherRS.getString("imgLink");
						PreparedStatement findCreator = conn.prepareStatement("SELECT * from users WHERE userID = ?");
						findCreator.setInt(1, otherRS.getInt("creatorID"));
						otherOtherRS = findCreator.executeQuery();
						otherOtherRS.next();
						String creatorName = otherOtherRS.getString("username");
						Meet m = new Meet(otherRS.getInt("meetupID"), otherRS.getInt("gameID"),otherRS.getInt("creatorID"), otherRS.getInt("capacity"), otherRS.getInt("currPlayers"),otherRS.getString("location")
								,otherRS.getString("meetTime"),otherRS.getString("frequency"), gameName, creatorName, gameImage);
						user.addMeet(m);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return userStatus;
	}

	public int registerUser(User user, String cPassword) {
		open();
		/*
		 * 0 if username already taken 1 if passwords don't match 2 if correct 3 if
		 * username isn't put in 4 if password isn't put in
		 */
		int userStatus = 2;
		try {

			// If username isn't put in
			if (user.getUsername() == null || user.getUsername().equals("")) {
				userStatus = 3;
				return userStatus;
			}
			// If password isn't put in
			if (user.getPassword() == null || user.getPassword().equals("")) {
				userStatus = 4;
				return userStatus;
			}

			// Check if username exists
			PreparedStatement usernameExist = conn
					.prepareStatement("select username from users " + " where username = ?");
			usernameExist.setString(1, user.getUsername());
			rs = usernameExist.executeQuery();
			// rs.next is true if there is a user with the name
			if (rs.next()) {
				userStatus = 0;
				return userStatus;
			}

			// sets userStatus to 1 if passwords do not match if they don't match
			if (!(user.getPassword().equals(cPassword))) {
				userStatus = 1;
				return userStatus;
			}
			System.out.println(user.getPassword());
			System.out.println(cPassword);

			// Insert into
			PreparedStatement userInsert = conn
					.prepareStatement("Insert into users(username,password)" + " values (?,?)");
			userInsert.setString(1, user.getUsername());
			userInsert.setString(2, user.getPassword());
			int affect = userInsert.executeUpdate();
			PreparedStatement getUser = conn
					.prepareStatement("SELECT * from users WHERE username = ? AND password = ?");
			getUser.setString(1, user.getUsername());
			getUser.setString(2, user.getPassword());
			rs = getUser.executeQuery();
			user.setID(rs.getInt("userID"));
			// Userstatus should default to 2 here
			String ownurl = "https://www.boardgamegeek.com/xmlapi2/collection?username=" + user.getUsername()
					+ "&subtype=boardgame&own=1";
			String wishurl = "https://www.boardgamegeek.com/xmlapi2/collection?username=" + user.getUsername()
					+ "&subtype=boardgame&wishlist=1";
			PreparedStatement gameInsert = conn.prepareStatement("Insert into games(gameName, imgLink) values (?,?)");
			String xmlString = null;
			try {
				xmlString = createXMLStringFromURL(ownurl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document doc = convertStringToXMLDocument(xmlString);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");
			for (int i = 0; i < nList.getLength(); i++) {
				Node n = nList.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) n;
					PreparedStatement findBG = conn.prepareStatement("SELECT * from games WHERE gameName = ?");
					findBG.setString(1, eElement.getElementsByTagName("name").item(0).getTextContent());
					rs = findBG.executeQuery();
					if (rs.next() == false) {
						gameInsert.setString(1, eElement.getElementsByTagName("name").item(0).getTextContent());
						gameInsert.setString(2, eElement.getElementsByTagName("image").item(0).getTextContent());
						gameInsert.executeUpdate();
					}
					rs = findBG.executeQuery();
					int boardID = rs.getInt("gameID");
					PreparedStatement ownSearch = conn
							.prepareStatement("SELECT * from owned WHERE userID = ? AND gameID = ?");
					ownSearch.setInt(1, user.getID());
					ownSearch.setInt(2, boardID);
					rs = ownSearch.executeQuery();
					if (rs.next() == false) {
						PreparedStatement addOwned = conn
								.prepareStatement("INSERT into owned(userID, gameID) values(?,?)");
						addOwned.setInt(1, user.getID());
						addOwned.setInt(2, boardID);
						addOwned.executeUpdate();
					}
				}
			}

			try {
				xmlString = createXMLStringFromURL(wishurl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document doc2 = convertStringToXMLDocument(xmlString);
			doc2.getDocumentElement().normalize();
			NodeList nList2 = doc2.getElementsByTagName("item");
			for (int i = 0; i < nList2.getLength(); i++) {
				Node n = nList2.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) n;
					PreparedStatement findBG = conn.prepareStatement("SELECT * from games WHERE gameName = ?");
					findBG.setString(1, eElement.getElementsByTagName("name").item(0).getTextContent());
					rs = findBG.executeQuery();
					if (rs.next() == false) {
						gameInsert.setString(1, eElement.getElementsByTagName("name").item(0).getTextContent());
						gameInsert.setString(2, eElement.getElementsByTagName("image").item(0).getTextContent());
						gameInsert.executeUpdate();
					}
					rs = findBG.executeQuery();
					int boardID = rs.getInt("gameID");
					PreparedStatement wishSearch = conn
							.prepareStatement("SELECT * wish owned WHERE userID = ? AND gameID = ?");
					wishSearch.setInt(1, user.getID());
					wishSearch.setInt(2, boardID);
					rs = wishSearch.executeQuery();
					if (rs.next() == false) {
						PreparedStatement addWish = conn
								.prepareStatement("INSERT into wish(userID, gameID) values(?,?)");
						addWish.setInt(1, user.getID());
						addWish.setInt(2, boardID);
						addWish.executeUpdate();
					}
				}
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return userStatus;
	}
	
	/**
	 * 
	 */
	public void createMeetup(User user, String gameName, int capacity, String location, String meetTime, String frequency) {
		open();
		
		try {
			// initialize sql prepared statements 
			PreparedStatement findGameSQL = conn.prepareStatement("SELECT * FROM games WHERE gameName = ?");
			PreparedStatement insertSQL = conn.prepareStatement("INSERT INTO meetups (gameID, capacity, currPlayers, location, meetTime, frequency, creatorID) VALUES (?, ?, 1, ?, ?, ?, ?)");
			PreparedStatement findMeetup = conn.prepareStatement("SELECT meetupID FROM meetups WHERE gameID = ? AND capacity = ? AND currPlayers = 1 AND location = ? AND meetTime = ? AND frequency = ? AND creatorID = ?");
			PreparedStatement insertUserMeetup = conn.prepareStatement("INSERT INTO usersMeetup (meetupID, userID) VALUES (?, ?)");
			int gameID = -1;
			String gameImage = "";
			// set prepared values, query for info, set result in gameID
			findGameSQL.setString(1, gameName);
			rs = findGameSQL.executeQuery();
			if (rs.next()) {
				gameID = rs.getInt("gameID");
				gameImage = rs.getString("imgLink");
			}
			
			// set prepared values, insert into database
			insertSQL.setInt(1, gameID);
			insertSQL.setInt(2, capacity);
			insertSQL.setString(3, location);
			insertSQL.setString(4, meetTime);
			insertSQL.setString(5, frequency);
			insertSQL.setInt(6, user.getID());
			insertSQL.execute();
			
			// set prepared values, query to get meetupID
			findMeetup.setInt(1, gameID);
			findMeetup.setInt(2, capacity);
			findMeetup.setString(3, location);
			findMeetup.setString(4, meetTime);
			findMeetup.setString(5, frequency);
			findMeetup.setInt(6, user.getID());
			rs = findMeetup.executeQuery();
			
			// create Meet object and add to User's meetups list
			if (rs.next()) {
				int id = rs.getInt("meetupID");
				
				insertUserMeetup.setInt(1, id);
				insertUserMeetup.setInt(2, user.getID());
				insertUserMeetup.execute();
				
				Meet meet = new Meet(id, gameID, user.getID(), capacity, 1, location, meetTime, frequency, gameName, user.getUsername(), gameImage);
				//user add meetup
				user.addMeet(meet);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void joinMeetup(User user, int meetupID) {
		open();
		try {
			PreparedStatement joinSQL = conn.prepareStatement("INSERT INTO usersMeetup(meetupID,userID) VALUES(?,?)");
			joinSQL.setInt(1,meetupID);
			joinSQL.setInt(2, user.getID());
			joinSQL.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

	}
	
	public List<Meet> getMeetupResults(User user) {
		//Select all meetups that have a join statement with wish and gameID of meetup
		List<Meet> meets = new ArrayList<>();
		
		open();
		
		try {
			// get wish list from user
			List<Game> wish = user.getWish();
			
			// make sql string
			String sql = "SELECT * FROM meetups";
			
			if (!wish.isEmpty()) {
				sql += " WHERE 1 = 2";
				for(Game game : wish) {
					sql += " OR gameID = " + game.getGameID();
				}
			}
			
			PreparedStatement findMeetup = conn.prepareStatement(sql);
			int id;
			int gameID;
			int userID;
			int capacity;
			int playerNum;
			String location;
			String meetTime;
			String frequency;
			String gameName = "";
			String username = "";
			String gameImage = "";
			rs = findMeetup.executeQuery();
			
			while (rs.next()) {
				 id = rs.getInt("meetupID");
				 gameID = rs.getInt("gameID");
				 userID = rs.getInt("creatorID");
				 capacity = rs.getInt("capacity");
				 playerNum = rs.getInt("currPlayers");
				 location = rs.getString("location");
				 meetTime = rs.getString("meetTime");
				 frequency = rs.getString("frequency");
				
				ResultSet rs2 = null;
				ResultSet rs3 = null;
				
				PreparedStatement findGameName = conn.prepareStatement("SELECT * FROM games WHERE gameID = " + gameID);
				PreparedStatement findUsername = conn.prepareStatement("SELECT username FROM users WHERE userID = " + userID);
				
				rs2 = findGameName.executeQuery();
				rs3 = findUsername.executeQuery();
				
				if (rs2.next()) {
					gameName = rs2.getString("gameName");
					gameImage = rs2.getString("imgLink");
				}
				
				if (rs3.next()) {
					username = rs3.getString("username");
				}
				
				Meet meet = new Meet(id, gameID, userID, capacity, playerNum, location, meetTime, frequency, gameName, username, gameImage);
				//user add meetup
				meets.add(meet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return meets;
	}
	
	public List<Meet> getAllMeetups() {
		//Select all meetups that have a join statement with wish and gameID of meetup
		List<Meet> meets = new ArrayList<>();
		
		open();
		
		try {
			
			// make sql string
			PreparedStatement findMeetup = conn.prepareStatement("SELECT * FROM meetups");
			int id;
			int gameID;
			int userID;
			int capacity;
			int playerNum;
			String location;
			String meetTime;
			String frequency;
			String gameName = "";
			String username = "";
			String gameImage = "";
			rs = findMeetup.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt("meetupID");
				gameID = rs.getInt("gameID");
				userID = rs.getInt("creatorID");
				capacity = rs.getInt("capacity");
				playerNum = rs.getInt("currPlayers");
				location = rs.getString("location");
				meetTime = rs.getString("meetTime");
				frequency = rs.getString("frequency");

				ResultSet rs2 = null;
				ResultSet rs3 = null;

				PreparedStatement findGameName = conn.prepareStatement("SELECT * FROM games WHERE gameID = " + gameID);
				PreparedStatement findUsername = conn.prepareStatement("SELECT username FROM users WHERE userID = " + userID);

				rs2 = findGameName.executeQuery();
				rs3 = findUsername.executeQuery();
				
				if (rs2.next()) {
					gameName = rs2.getString("gameName");
					gameImage = rs2.getString("imgLink");
				}
				
				if (rs3.next()) {
					username = rs3.getString("username");
				}
				
				Meet meet = new Meet(id, gameID, userID, capacity, playerNum, location, meetTime, frequency, gameName, username, gameImage);
				//user add meetup
				meets.add(meet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return meets;
	}

	// Standard Open connection method
	private void open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(cred);
			st = conn.createStatement();
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		} finally {
		}
	}

	// Standard Close connection method
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}

	private String createXMLStringFromURL(String desiredUrl) throws Exception {
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;

		try {
			url = new URL(desiredUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(15 * 1000);
			connection.connect();

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			stringBuilder = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}

			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// close your reader!
			reader.close();
		}
	}

	//Converts string to xml
	private static Document convertStringToXMLDocument(String xmlString) {
		// Parser that produces DOM object trees from XML content
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// API to obtain DOM Document instance
		DocumentBuilder builder = null;
		try {
			// Create DocumentBuilder with default configuration
			builder = factory.newDocumentBuilder();

			// Parse the content to Document object
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
