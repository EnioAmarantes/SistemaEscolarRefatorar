/**
 * 
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Aluno;
import models.Professor;
import models.Turma;
import shared.ADatabase;
import shared.IDao;
import shared.database.MySqlDatabase;

/**
 * @author enio
 *
 */
public class TurmaController implements IDao<Turma> {
	ArrayList<Turma> turmas = new ArrayList<Turma>();

	private ResultSet rsdados = null;
	private Connection connection = null;
	private PreparedStatement pstdados = null;

	private static final String sqlconsulta = "SELECT * FROM aluno order by id_aluno";
	private static final String sqlinserir = "INSERT INTO aluno (nome, email, registro_academico) VALUES ( ?, ?, ?)";
	private static final String sqlalterar = "UPDATE aluno SET nome = ?, email = ?, registro_academico = ? WHERE id_aluno = ?";
	private static final String sqlexcluir = "DELETE FROM aluno WHERE id_aluno = ?";

	public TurmaController() {
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
	public ArrayList<Turma> Lista() {
		ArrayList<Turma> alunos = new ArrayList<Turma>();
		try {
			this.ConsultarTodos();
			while (rsdados.next()) {
				Turma turma = new Turma(Integer.parseInt(rsdados.getObject(1).toString()),
						rsdados.getObject(2).toString(), rsdados.getObject(3).toString(),
						rsdados.getObject(4).toString(), (Date) rsdados.getObject(4), (Professor) rsdados.getObject(5),
						(ArrayList<Aluno>) rsdados.getObject(6));

				turmas.add(turma);
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

	protected void prepStatSet(Turma turma) throws SQLException {
		pstdados.setString(1, turma.getNome());
		pstdados.setString(2, turma.getCodigo());
		pstdados.setString(3, turma.getSala());
		pstdados.setDate(4, (Date) turma.getAno());
		pstdados.setObject(5, turma.getProfessor());
		pstdados.setArray(6, (Array) turma.getAlunos());
	}

	@Override
	public boolean Cria(Turma turma) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlinserir, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			this.prepStatSet(turma);
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return turmas.add(turma);
	}

	@Override
	public Turma Modificar(Turma turma) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlalterar, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			this.prepStatSet(turma);
			pstdados.setInt(4, turma.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return turma;
	}

	@Override
	public Turma Excluir(Turma turma) {
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = (PreparedStatement) connection.prepareStatement(sqlexcluir, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			connection.setAutoCommit(false);
			pstdados.setInt(1, turma.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return turma;
	}

}
