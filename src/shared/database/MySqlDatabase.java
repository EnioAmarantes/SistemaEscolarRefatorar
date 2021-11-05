package shared.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import shared.ADatabase;

public class MySqlDatabase extends ADatabase {

	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
