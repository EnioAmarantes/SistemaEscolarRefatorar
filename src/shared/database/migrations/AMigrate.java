package shared.database.migrations;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shared.ADatabase;
import shared.database.MySqlDatabase;

public abstract class AMigrate {

	protected String sqlCreat;
	protected String table;
	
	public void AMigrate() {

	}
	
	public void up() {
		try {
			String path = System.getProperty("user.dir");
	        File fileName = new File(path + "/src/shared/database/configBd.properties");
	        ADatabase.init(fileName);
        	Connection connection = MySqlDatabase.getConnection();
			PreparedStatement pstdados = connection.prepareStatement(sqlCreat, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstdados.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro ao executar query = " + erro);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void down() {
		try {
        	Connection connection = MySqlDatabase.getConnection();
			PreparedStatement pstdados = connection.prepareStatement("drop table "+ table, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstdados.executeQuery();
        } catch (SQLException erro) {
            System.out.println("Erro ao executar query = " + erro);
        }
	}
}
