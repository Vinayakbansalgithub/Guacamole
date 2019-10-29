package com.vinayak.Guacamole.Guacamole_custom;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

public class DatabaseConnection {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/guacamole?autoReconnect=true";
	private static final String DB_USER = "guacamole";
	private static final String DB_PASSWORD = "secure_123";
	private static  BigDecimal salt;
	private static int hash;
	static Connection con = null;

	static Statement stmt = null;

	static ResultSet rs = null;

	static String sql = null;
	static int id;

	static PreparedStatement pstmt = null;

	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {

		
		
		try {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("DatabaseConnection Guacamole");
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM guacamole_entity");

		while (rs.next()) {
			int id = rs.getInt("entity_id");
			String name = rs.getString("name");
			String job = rs.getString("type");
			System.out.println(id + "   " + name + "    " + job);
		}

		System.out.println("Inserting records into the table...");
		stmt = con.createStatement();

		
		
		
		sql = "INSERT INTO guacamole_connection (connection_name, protocol) VALUES ('testuseradmin', 'rdp');";

		stmt.executeUpdate(sql);

		System.out.println("complete");

		rs = stmt.executeQuery("SELECT * FROM guacamole_connection");

		while (rs.next()) {
			id = rs.getInt("connection_id");
			System.out.println(id + "    ");
		}

		sql = "INSERT INTO guacamole_connection_parameter VALUES (?, 'gateway-hostname', '34.83.167.83');";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();

		sql = "INSERT INTO guacamole_connection_parameter VALUES (?, 'gateway-port', '3389');";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();

		sql = "INSERT INTO guacamole_connection_parameter VALUES (?, 'gateway-username', 'administrator');";

		
	
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();

		sql = "INSERT INTO guacamole_connection_parameter VALUES (?, 'gateway-password', 'Functionise123');";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();

		System.out.println("complete--");

		rs = stmt.executeQuery("SELECT * FROM guacamole_connection_parameter");

		System.out.println("complete--");

		rs = stmt.executeQuery("SELECT * FROM guacamole_connection_parameter");

		while (rs.next()) {
			String name = rs.getString("connection_id");
			System.out.println(name + " end   ");
		}

		sql = "INSERT INTO `guacamole_entity` (`entity_id`, `name`, `type`) VALUES(?, ?, 'USER');";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setString(2, "VinayakBansal");
		pstmt.executeUpdate();

		rs = stmt.executeQuery("SELECT * FROM guacamole_entity");

		while (rs.next()) {
			String name = rs.getString("name");
			System.out.println(name + " end   ");
		}
		
		
		

		sql = "INSERT INTO `guacamole_user` (`user_id`, `entity_id`, `password_hash`, `password_salt`, `password_date`, `disabled`, `expired`, `access_window_start`, `access_window_end`, `valid_from`, `valid_until`, `timezone`, `full_name`, `email_address`, `organization`, `organizational_role`) VALUES\n" + 
				"(?, ?, UNHEX(SHA2(CONCAT('VinayakBansal', HEX(0x3ee4312f4e24f39990dfe4ddef8cc107c4749931d685424fc20df19cc2ce00a2)), 256)), 0x3ee4312f4e24f39990dfe4ddef8cc107c4749931d685424fc20df19cc2ce00a2, '2019-10-10 07:33:43', 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setInt(2, id);
		

		pstmt.executeUpdate();
		
		
		sql = "INSERT INTO `guacamole_connection_permission` (`entity_id`, `connection_id`, `permission`) VALUES(?, ?, 'READ');";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setInt(2, id);
		

		pstmt.executeUpdate();

		rs = stmt.executeQuery("SELECT * FROM guacamole_user");

		while (rs.next()) {
			int name = rs.getInt("user_id");
			System.out.println(name + " end   ");
		}

		System.out.println("user create complete--");

	}
}
