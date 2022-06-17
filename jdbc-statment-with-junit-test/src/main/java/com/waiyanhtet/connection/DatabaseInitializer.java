package com.waiyanhtet.connection;

import java.sql.SQLException;

public class DatabaseInitializer {

	public static void truncateTable(String... tables) {

		try (var con = ConnectionManager.getInstance().getConnection(); var stmt = con.createStatement()) {

			stmt.execute("set foreign_key_checks = 0;");

			for (String table : tables) {
				stmt.execute("truncate table %s".formatted(table));
			}

			stmt.execute("set foreign_key_checks = 1;");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
