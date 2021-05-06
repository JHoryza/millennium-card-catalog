package dev.horyza.mcc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.model.Collection;
import dev.horyza.mcc.model.Filter;

/**
 *
 * @author sqlitetutorial.net
 */
public class DatabaseHandler {
	
	/**
	 * Connect to the test.db database
	 * 
	 * @return the Connection object
	 */
	public Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:C:/MCC/cards.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public ArrayList<Card> selectAll(String table) {
		ArrayList<Card> cardList = new ArrayList<Card>();
		String sql = "SELECT * FROM " + table;
		
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String desc = rs.getString("desc");
				String type = rs.getString("type");
				String attribute = rs.getString("attribute");
				String race = rs.getString("race");
				String archetype = rs.getString("archetype");
				int atk = rs.getString("atk") == null ? -1 : Integer.parseInt(rs.getString("atk"));
				int def = rs.getString("def") == null ? -1 : Integer.parseInt(rs.getString("def"));
				int level = rs.getString("level") == null ? -1 : Integer.parseInt(rs.getString("level"));
				cardList.add(new Card(id, name, desc, type, attribute, race, archetype, 0, 0, 0));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return cardList;
	}
	
	public ArrayList<Card> selectFiltered(String table, Filter filter) {
		ArrayList<Card> cardList = new ArrayList<Card>();
		
		return cardList;
	}
}
