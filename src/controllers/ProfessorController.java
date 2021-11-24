/**
 * 
 */
package controllers;

import java.util.ArrayList;

import models.Professor;
import shared.ADatabase;
import shared.IDao;
import shared.consts.Config;
import shared.database.MySqlDatabase;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author enio
 *
 */
public class ProfessorController implements IDao<Professor> {

	ArrayList<Professor> professores = new ArrayList<Professor>();

	private ResultSet rsdados = null;
	private Connection connection = null;
	private PreparedStatement pstdados = null;

	private static final String sqlconsulta = "SELECT * FROM professor order by id_professor";
	private static final String sqlinserir = "INSERT INTO professor (nome, email, disciplina) VALUES ( ?, ?, ?)";
	private static final String sqlalterar = "UPDATE professor SET nome = ?, email = ?, disciplina = ? WHERE id_professor = ?";
	private static final String sqlexcluir = "DELETE FROM professor WHERE id_professor = ?";

	public ProfessorController() {
		try {
			ADatabase.init(Config.PATHDB);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Professor> Lista() {

		ArrayList<Professor> professores = new ArrayList<Professor>();
		try {
			this.ConsultarTodos();
			while (rsdados.next()) {
				Professor professor = new Professor(Integer.parseInt(rsdados.getObject(1).toString()),
						rsdados.getObject(2).toString(), rsdados.getObject(3).toString(),
						rsdados.getObject(4).toString());

				professores.add(professor);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professores;
		// return (ArrayList<Professor>) professores.clone();
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

	protected void prepStatSet(Professor professor) throws SQLException {
		pstdados.setString(1, professor.getNome());
		pstdados.setString(2, professor.getEmail());
		pstdados.setString(3, professor.getDisciplina());
	}

	@Override
	public boolean Cria(Professor professor) {
    try {
      connection = MySqlDatabase.getConnection();
      pstdados = (PreparedStatement) connection.prepareStatement(
          sqlinserir, 
          ResultSet.TYPE_SCROLL_SENSITIVE,
          ResultSet.CONCUR_UPDATABLE);
      connection.setAutoCommit(false);
      this.prepStatSet(professor);
      pstdados.executeUpdate();
      connection.commit();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
		return professores.add(professor);

	}

	@Override
	public Professor Modificar(Professor professor) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlalterar, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			this.prepStatSet(professor);
			pstdados.setInt(4, professor.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return professor;
	}

	@Override
	public Professor Excluir(Professor professor) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlexcluir, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			pstdados.setInt(1, professor.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return professor;
	}
	

}
