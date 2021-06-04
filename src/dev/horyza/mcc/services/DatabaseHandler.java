package dev.horyza.mcc.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dev.horyza.mcc.model.Card;

/**
 *
 * @author sqlitetutorial.net
 */
public class DatabaseHandler {

	/**
	 * Connect to the database
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

	/**
	 * Retrieves all cards from a selected table
	 * @param table The database table of cards
	 * @return A List of Card objects
	 */
	public List<Card> selectAll(String table) {
		List<Card> cardList = new ArrayList<Card>();
		String sql = "SELECT * FROM " + table;

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name") == null ? "" : rs.getString("name");
				String desc = rs.getString("desc") == null ? "" : rs.getString("desc");
				String type = rs.getString("type") == null ? "" : rs.getString("type");
				String attribute = rs.getString("attribute") == null ? "" : rs.getString("attribute");
				String race = rs.getString("race") == null ? "" : rs.getString("race");
				String archetype = rs.getString("archetype") == null ? "" : rs.getString("archetype");
				int atk = rs.getString("atk") == null ? -1 : Integer.parseInt(rs.getString("atk"));
				int def = rs.getString("def") == null ? -1 : Integer.parseInt(rs.getString("def"));
				int level = rs.getString("level") == null ? -1 : Integer.parseInt(rs.getString("level"));
				cardList.add(new Card(id, name, desc, type, attribute, race, archetype, atk, def, level));
			}
		} catch (SQLException e) {

		}
		return cardList;
	}

	/**
	 * Retrieves cards from a table with a specified ID
	 * @param table The database table of cards
	 * @param idMap HashMap of (id, count) pairs
	 * @return A List of Card objects
	 */
	public List<Card> selectById(String table, HashMap<Integer, Integer> idMap) {
		List<Card> cardList = new ArrayList<Card>();
		String sql = "SELECT * FROM " + table + " WHERE id IN (";

		List<Integer> idList = new ArrayList<Integer>(idMap.keySet());
		for (int i = 0; i < idList.size(); i++) {
			if (i == idList.size() - 1)
				sql += idList.get(i) + ")";
			else
				sql += idList.get(i) + ",";
		}

		try (Connection conn = this.connect();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name") == null ? "" : rs.getString("name");
				String desc = rs.getString("desc") == null ? "" : rs.getString("desc");
				String type = rs.getString("type") == null ? "" : rs.getString("type");
				String attribute = rs.getString("attribute") == null ? "" : rs.getString("attribute");
				String race = rs.getString("race") == null ? "" : rs.getString("race");
				String archetype = rs.getString("archetype") == null ? "" : rs.getString("archetype");
				int atk = rs.getString("atk") == null ? -1 : Integer.parseInt(rs.getString("atk"));
				int def = rs.getString("def") == null ? -1 : Integer.parseInt(rs.getString("def"));
				int level = rs.getString("level") == null ? -1 : Integer.parseInt(rs.getString("level"));
				int quantity = idMap.get(id);
				Card card = new Card(id, name, desc, type, attribute, race, archetype, atk, def, level);
				for (int i = 0; i < quantity; i++) {
					cardList.add(card);
				}
			}
		} catch (SQLException e) {

		}
		return cardList;
	}

	/**
	 * Adds a List of Cards to a specified database table
	 * @param table The database table of cards
	 * @param cards List of cards to be added to the database
	 * @return true if database add was successful
	 */
	public boolean add(String table, List<Card> cards) {
		String sql = "INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?,?,?,?)";

		try (Connection conn = this.connect()) {
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql);
			Iterator<Card> iterator = cards.iterator();

			while (iterator.hasNext()) {
				Card card = iterator.next();
				statement.setInt(1, card.getId());
				statement.setString(2, card.getName());
				statement.setString(3, card.getType());
				statement.setString(4, card.getDescription());
				statement.setInt(5, card.getAtk());
				statement.setInt(6, card.getDef());
				statement.setInt(7, card.getLevel());
				statement.setString(8, card.getRace());
				statement.setString(9, card.getAttribute());
				statement.setString(10, card.getArchetype());
				statement.addBatch();
			}
			statement.executeBatch();
			conn.commit();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Clears the data from a specified table
	 * @param table The database table to clear data from
	 * @return true if the data clear was successful
	 */
	public boolean clear(String table) {
		String sql = "DELETE FROM " + table;
		
		try (Connection conn = this.connect()) {
			Statement statement = conn.createStatement();
			statement.executeQuery(sql);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
