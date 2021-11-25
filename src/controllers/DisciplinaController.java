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

import models.Disciplina;
import shared.ADatabase;
import shared.IDao;
import shared.consts.Config;
import shared.database.MySqlDatabase;

/**
 * @author enio
 *
 */
public class DisciplinaController implements IDao<Disciplina> {

	ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

	private ResultSet rsdados = null;
	private Connection connection = null;
	private PreparedStatement pstdados = null;

	private static final String sqlconsulta = "SELECT * FROM disciplina order by id_disciplina";
	private static final String sqlConsultaById = "SELECT * FROM disciplina WHERE id_disciplina = ?";
	private static final String sqlinserir = "INSERT INTO disciplina (nome) VALUES ( ? )";
	private static final String sqlalterar = "UPDATE disciplina SET nome = ? WHERE id_disciplina = ?";
	private static final String sqlexcluir = "DELETE FROM disciplina WHERE id_disciplina = ?";

	public DisciplinaController() {
		try {
			ADatabase.init(Config.PATHDB);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Disciplina> Lista() {
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		try {
			this.ConsultarTodos();
			while (rsdados.next()) {
				Disciplina disciplina = new Disciplina(
						Integer.parseInt(rsdados.getObject(1).toString()),
						rsdados.getObject(2).toString()
					);

				disciplinas.add(disciplina);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return disciplinas;
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

	protected void prepStatSet(Disciplina disciplina) throws SQLException {
		pstdados.setString(1, disciplina.getNome());
	}

	@Override
	public boolean Cria(Disciplina disciplina) {
    try {
      connection = MySqlDatabase.getConnection();
      pstdados = (PreparedStatement) connection.prepareStatement(
          sqlinserir, 
          ResultSet.TYPE_SCROLL_SENSITIVE,
          ResultSet.CONCUR_UPDATABLE);
      connection.setAutoCommit(false);
      this.prepStatSet(disciplina);
      pstdados.executeUpdate();
      connection.commit();
      return true;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
	}

	@Override
	public Disciplina Modificar(Disciplina Disciplina) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlalterar, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			this.prepStatSet(Disciplina);
			pstdados.setInt(2, Disciplina.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return Disciplina;
	}

	@Override
	public Disciplina Excluir(Disciplina Disciplina) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlexcluir, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			pstdados.setInt(1, Disciplina.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return Disciplina;
	}

	public Disciplina getDisciplinaById(int id_disciplina) {
		Disciplina disciplina = new Disciplina();
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = connection.prepareStatement(sqlConsultaById, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstdados.setInt(1, id_disciplina);
			rsdados = pstdados.executeQuery();
			while (rsdados.next()) {
				disciplina = new Disciplina(rsdados.getObject(1).toString());
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consulta = " + erro);
		}
		return disciplina;
	}

}
