package dev.horyza.mcc.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dev.horyza.mcc.model.Card;
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
			System.out.println(e.getMessage());
		}
		return cardList;
	}

	public List<Card> selectFiltered(String table, HashMap<Integer, Integer> cardMap) {
		List<Card> cardList = new ArrayList<Card>();
		String sql = "SELECT * FROM " + table + " WHERE id IN (";

		List<Integer> idList = new ArrayList<Integer>(cardMap.keySet());
		for (int i = 0; i < idList.size(); i++) {
			if (i == idList.size() - 1)
				sql += idList.get(i) + ")";
			else
				sql += idList.get(i) + ",";
		}

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
				int quantity = cardMap.get(id);
				Card card = new Card(id, name, desc, type, attribute, race, archetype, atk, def, level);
				for (int i = 0; i < quantity; i++) {
					cardList.add(card);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return cardList;
	}
}
