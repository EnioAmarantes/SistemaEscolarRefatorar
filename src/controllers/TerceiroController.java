/**
 * 
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Terceiro;
import shared.ADatabase;
import shared.IDao;
import shared.database.MySqlDatabase;

/**
 * @author enio
 *
 */
public class TerceiroController implements IDao<Terceiro> {
	
	ArrayList<Terceiro> terceiros = new ArrayList<Terceiro>();
	
	private ResultSet rsdados = null;
	private Connection connection = null;
	private PreparedStatement pstdados = null;
	
	private static final String sqlconsulta = "SELECT * FROM terceiro order by id_terceiro";
	private static final String sqlinserir = "INSERT INTO terceiro (nome, email, funcao) VALUES ( ?, ?, ?)";
    private static final String sqlalterar = "UPDATE terceiro SET nome = ?, email = ?, funcao = ? WHERE id_terceiro = ?";
    private static final String sqlexcluir = "DELETE FROM terceiro WHERE id_terceiro = ?";
	
	public TerceiroController() {
		String path = System.getProperty("user.dir");
        File fileName = new File(path + "/src/shared/database/configBd.properties");
		try {
			ADatabase.init(fileName);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Terceiro> Lista() {
		ArrayList<Terceiro> terceiros = new ArrayList<Terceiro>();
		try {
        	this.ConsultarTodos();
			while(rsdados.next()){
				Terceiro terceiro = new Terceiro(
						Integer.parseInt(rsdados.getObject(1).toString()), 
						rsdados.getObject(2).toString(), 
						rsdados.getObject(3).toString(), 
						rsdados.getObject(4).toString()
				);

			    terceiros.add(terceiro);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return terceiros;
	}
	
	public boolean ConsultarTodos() {
        try {
        	connection = MySqlDatabase.getConnection();
            pstdados = connection.prepareStatement(sqlconsulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }
	
	protected void prepStatSet(Terceiro terceiro) throws SQLException{
        pstdados.setString(1, terceiro.getNome());
        pstdados.setString(2, terceiro.getEmail());
        pstdados.setString(3, terceiro.getFuncao());
	}
	
	@Override
	public boolean Cria(Terceiro terceiro) {
		 try {
	            connection = MySqlDatabase.getConnection();
				pstdados = (PreparedStatement) connection.prepareStatement(sqlinserir, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            connection.setAutoCommit(false);
	            this.prepStatSet(terceiro);

	            pstdados.executeUpdate();
				connection.commit();
				return terceiros.add(terceiro);
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }

		boolean criaStatus = IsValid(terceiro);
		
		if(criaStatus)
		criaStatus = terceiros.add(terceiro);
		
		return criaStatus;

	}
	
	@Override
	public Terceiro Modificar(Terceiro terceiro) {
		try {
            connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlalterar, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.setAutoCommit(false);
            this.prepStatSet(terceiro);
            pstdados.setInt(4, terceiro.getId());
            pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return terceiro;
	}
	
	@Override
	public Terceiro Excluir(Terceiro terceiro) {
		try {
            connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlexcluir, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            connection.setAutoCommit(false);
            pstdados.setInt(1, terceiro.getId());
            pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return terceiro;
	}

	@Override
	public boolean IsValid(Terceiro terceiro) {
		return !terceiros.contains(terceiro);
	}
}
