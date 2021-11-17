package shared.database.migrations;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shared.ADatabase;
import shared.AModel;
import shared.consts.Config;
import shared.database.MySqlDatabase;
import shared.database.seeders.ASeeder;

public abstract class AMigrate {

	private ResultSet rsdados = null;
	protected String sqlCreat;
	protected String table;
	
	public void AMigrate() {

	}
	
	protected void upTable(){
		try {
	        ADatabase.init(Config.PATHDB);
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
	

	protected boolean tableExist() {
		try {
			ADatabase.init(Config.PATHDB);
        	Connection connection = MySqlDatabase.getConnection();
			PreparedStatement pstdados = connection.prepareStatement(
					"SHOW TABLES LIKE '"+ table+ "'", 
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE);
			rsdados = pstdados.executeQuery();
			return rsdados.next();
        } catch (SQLException erro) {
            System.out.println("Erro ao executar query = " + erro);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public void up() {
		if(tableExist()) {
			System.out.println("tabela "+ table +" já existe");
		}else {
			this.upTable();
			System.out.println("tabela "+ table +" criada com sucesso!");
		}
	}
	
	public void up(ASeeder seed) {
		if(tableExist()) {
			System.out.println("tabela "+ table +" já existe");
		}else {
			this.upTable();
			System.out.println("tabela "+ table +" criada com sucesso!");
			seed.run();
			System.out.println("tabela "+ table +" populada");
		}
		
	}
	
	public void down() {
		try {
			ADatabase.init(Config.PATHDB);
        	Connection connection = MySqlDatabase.getConnection();
			PreparedStatement pstdados = connection.prepareStatement("drop table "+ table, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstdados.executeQuery();
        } catch (SQLException erro) {
            System.out.println("Erro ao executar query = " + erro);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
