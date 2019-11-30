
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class accessData {

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
						int boardID = rs.getInt("gameID");
						PreparedStatement ownSearch = conn
								.prepareStatement("SELECT * from owned WHERE userID = ? AND gameID = ?");
						ownSearch.setInt(1, user.getID());
						ownSearch.setInt(2, boardID);
						rs = findBG.executeQuery();
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
				NodeList nList2 = doc.getElementsByTagName("item");
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
						PreparedStatement ownSearch = conn
								.prepareStatement("SELECT * from owned WHERE userID = ? AND gameID = ?");
						ownSearch.setInt(1, user.getID());
						ownSearch.setInt(2, boardID);
						rs = findBG.executeQuery();
						if (rs.next() == false) {
							PreparedStatement addWish = conn
									.prepareStatement("INSERT into wish(userID, gameID) values(?,?)");
							addWish.setInt(1, user.getID());
							addWish.setInt(2, boardID);
							addWish.executeUpdate();
						}
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
					rs = findBG.executeQuery();
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
			NodeList nList2 = doc.getElementsByTagName("item");
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
					PreparedStatement ownSearch = conn
							.prepareStatement("SELECT * from owned WHERE userID = ? AND gameID = ?");
					ownSearch.setInt(1, user.getID());
					ownSearch.setInt(2, boardID);
					rs = findBG.executeQuery();
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
