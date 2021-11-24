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

import models.Aluno;
import shared.ADatabase;
import shared.IDao;
import shared.consts.Config;
import shared.database.MySqlDatabase;

/**
 * @author enio
 *
 */
public class AlunoController implements IDao<Aluno> {

	ArrayList<Aluno> alunos = new ArrayList<Aluno>();

	private ResultSet rsdados = null;
	private Connection connection = null;
	private PreparedStatement pstdados = null;

	private static final String sqlconsulta = "SELECT * FROM aluno order by id_aluno";
	private static final String sqlinserir = "INSERT INTO aluno (nome, email, registro_academico) VALUES ( ?, ?, ?)";
	private static final String sqlalterar = "UPDATE aluno SET nome = ?, email = ?, registro_academico = ? WHERE id_aluno = ?";
	private static final String sqlexcluir = "DELETE FROM aluno WHERE id_aluno = ?";

	public AlunoController() {
		try {
			ADatabase.init(Config.PATHDB);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Aluno> Lista() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		try {
			this.ConsultarTodos();
			while (rsdados.next()) {
				Aluno aluno = new Aluno(Integer.parseInt(rsdados.getObject(1).toString()),
						rsdados.getObject(2).toString(), rsdados.getObject(3).toString(),
						rsdados.getObject(4).toString());

				alunos.add(aluno);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alunos;
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

	protected void prepStatSet(Aluno aluno) throws SQLException {
		pstdados.setString(1, aluno.getNome());
		pstdados.setString(2, aluno.getEmail());
		pstdados.setString(3, aluno.getRegistro_academico());
	}

	@Override
	public boolean Cria(Aluno aluno) {
    try {
      connection = MySqlDatabase.getConnection();
      pstdados = (PreparedStatement) connection.prepareStatement(
          sqlinserir, 
          ResultSet.TYPE_SCROLL_SENSITIVE,
          ResultSet.CONCUR_UPDATABLE);
      connection.setAutoCommit(false);
      this.prepStatSet(aluno);
      pstdados.executeUpdate();
      connection.commit();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return alunos.add(aluno);
	}

	@Override
	public Aluno Modificar(Aluno aluno) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlalterar, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			this.prepStatSet(aluno);
			pstdados.setInt(4, aluno.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return aluno;
	}

	@Override
	public Aluno Excluir(Aluno aluno) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlexcluir, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			pstdados.setInt(1, aluno.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return aluno;
	}

	public ArrayList<Aluno> getByTurmaId(int id_turma) {
		// TODO Auto-generated method stub
		return new ArrayList<Aluno>();
	}

}
