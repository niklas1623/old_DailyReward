package de.niklas1623.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.niklas1623.database.MySQL;

public class DailyRewardManager {

	private static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public static void FirstJoin(String playername, String uuid) {

		try {
			String firstjoin = "INSERT INTO player (spielername, uuid, date) values (?, ?, ?);";
			PreparedStatement ps_firstjoin = MySQL.con.prepareStatement(firstjoin);
			ps_firstjoin.setString(1, playername);
			ps_firstjoin.setString(2, uuid);
			ps_firstjoin.setDate(3, getCurrentDate());

			ps_firstjoin.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void Join(String uuid) {

		try {
			String join = "UPDATE player SET date = ? WHERE uuid = ?";
			PreparedStatement ps_join = MySQL.con.prepareStatement(join);
			ps_join.setDate(1, getCurrentDate());
			ps_join.setString(2, uuid);

			ps_join.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean isJoined(String uuid) {
		ResultSet rs = MySQL.getResult("SELECT * FROM player WHERE uuid='" + uuid + "'");
		try {
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Date getDate(String uuid) {
		String getDate = "SELECT date FROM player WHERE uuid = ?";
		try {
			PreparedStatement ps_getDate = MySQL.con.prepareStatement(getDate);
			ps_getDate.setString(1, uuid);
			ResultSet rs = ps_getDate.executeQuery();
			while (rs.next()) {
				return rs.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
