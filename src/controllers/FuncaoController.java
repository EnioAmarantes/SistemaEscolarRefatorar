/**
 * 
 */
package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Funcao;
import shared.ADatabase;
import shared.IDao;
import shared.consts.Config;
import shared.database.MySqlDatabase;

/**
 * @author enio
 *
 */
public class FuncaoController implements IDao<Funcao> {

	ArrayList<Funcao> funcao = new ArrayList<Funcao>();

	private ResultSet rsdados = null;
	private Connection connection = null;
	private PreparedStatement pstdados = null;

	private static final String sqlconsulta = "SELECT * FROM funcao order by id_funcao";
	private static final String sqlconsultaById = "SELECT * FROM funcao where id_funcao = ? order by id_funcao";
	private static final String sqlinserir = "INSERT INTO funcao (nome) VALUES ( ? )";
	private static final String sqlalterar = "UPDATE funcao SET nome = ? WHERE id_funcao = ?";
	private static final String sqlexcluir = "DELETE FROM funcao WHERE id_funcao = ?";

	public FuncaoController() {
		try {
			ADatabase.init(Config.PATHDB);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Funcao> Lista() {
		ArrayList<Funcao> funcoes = new ArrayList<Funcao>();
		try {
			this.ConsultarTodos();
			while (rsdados.next()) {
				Funcao funcao = new Funcao(
						Integer.parseInt(rsdados.getObject(1).toString()),
						rsdados.getObject(2).toString()
					);

				funcoes.add(funcao);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcoes;
	}

	public boolean ConsultarTodos() {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = connection.prepareStatement(sqlconsulta, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rsdados = pstdados.executeQuery();
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consulta = " + erro);
		}
		return false;
	}

	protected void prepStatSet(Funcao funcao) throws SQLException {
		pstdados.setString(1, funcao.getNome());
	}

	@Override
	public boolean Cria(Funcao funcao) {
    try {
      connection = MySqlDatabase.getConnection();
      pstdados = (PreparedStatement) connection.prepareStatement(
          sqlinserir, 
          ResultSet.TYPE_SCROLL_SENSITIVE,
          ResultSet.CONCUR_UPDATABLE);
      connection.setAutoCommit(false);
      this.prepStatSet(funcao);
      pstdados.executeUpdate();
      connection.commit();
      return true;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
	}

	@Override
	public Funcao Modificar(Funcao funcao) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlalterar, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			this.prepStatSet(funcao);
			pstdados.setInt(2, funcao.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return funcao;
	}

	@Override
	public Funcao Excluir(Funcao funcao) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlexcluir, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			pstdados.setInt(1, funcao.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return funcao;
	}

	public Funcao getFuncaoById(int parseInt) {
		Funcao funcao = new Funcao("");
		
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = connection.prepareStatement(sqlconsultaById, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rsdados = pstdados.executeQuery();
			while (rsdados.next()) {
				funcao = new Funcao(Integer.parseInt(rsdados.getObject(1).toString()), rsdados.getObject(2).toString());
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao executar consulta = " + erro);
		}
		
		return funcao;
	}

}
