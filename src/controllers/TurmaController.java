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
	private ResultSet rsdadosAlunos = null;
	private ResultSet rsdadosProfessor = null;
	private PreparedStatement pstdadosAlunos = null;
	private PreparedStatement pstdadosProfessor = null;
	private PreparedStatement pstdados = null;
	private Connection connection = null;

	private static final String sqlconsulta = "SELECT id_turma, nome, codigo, sala, ano, id_professor FROM turma order by id_turma";
	private static final String sqlconsultaById = "SELECT id_turma, nome, codigo, sala, ano, id_professor FROM turma WHERE id_turma = ?";
	private static final String sqlGetProfessorByTurmaId = "SELECT id_professor, nome, email, disciplina FROM professor where id_professor = ?";
	private static final String sqlGetAlunosByTurmaId = "SELECT a.id_aluno, a.nome, a.email, a.registro_academico FROM matricula_turma_aluno mta inner join aluno a on a.id_aluno = mta.id_aluno where mta.id_turma = ?";
	private static final String sqlinserir = "INSERT INTO turma (nome, codigo, sala, id_professor) VALUES ( ?, ?, ?, ?)";
	private static final String sqlMatriculaAluno = "INSERT INTO matricula_turma_aluno (id_aluno, id_turma) values ( ?, ? )";
	private static final String sqlalterar = "UPDATE turma SET nome = ?, codigo = ?, sala = ?, id_professor = ? WHERE id_turma = ?";
	private static final String sqlexcluir = "DELETE FROM turma WHERE id_turma = ?; DELETE FROM matricula WHERE id_turma = ?";

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
		ArrayList<Turma> turmas = new ArrayList<Turma>();
		try {
			this.ConsultarTodos();
			while (rsdados.next()) {
				Turma turma = new Turma(
						Integer.parseInt(rsdados.getObject(1).toString()),
						rsdados.getObject(2).toString(), 
						rsdados.getObject(3).toString(),
						rsdados.getObject(4).toString(), 
						rsdados.getObject(5).toString(), 
						getProfessorByTurmaId(Integer.parseInt(rsdados.getObject(6).toString())),
						getAlunosByTurmaId(Integer.parseInt(rsdados.getObject(1).toString())));

				turmas.add(turma);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return turmas;
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
	
	public Turma getTurmaById(int id_turma) {
		Turma turma = new Turma();
		
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = connection.prepareStatement(sqlconsultaById, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstdados.setInt(1, id_turma);
			rsdados = pstdados.executeQuery();
			while (rsdados.next()) {
				turma = new Turma(
						Integer.parseInt(rsdados.getObject(1).toString()),
						rsdados.getObject(2).toString(), 
						rsdados.getObject(3).toString(),
						rsdados.getObject(4).toString(), 
						rsdados.getObject(5).toString(), 
						getProfessorByTurmaId(Integer.parseInt(rsdados.getObject(6).toString())),
						getAlunosByTurmaId(Integer.parseInt(rsdados.getObject(1).toString())));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consulta = " + erro);
		}
		return turma;
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
			pstdados.setInt(2, turma.getId());
			pstdados.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return turma;
	}
	
	public Professor getProfessorByTurmaId(int id) {
		Professor professor = new Professor();
			try {
				connection = MySqlDatabase.getConnection();
				pstdadosProfessor = (PreparedStatement) connection.prepareStatement(sqlGetProfessorByTurmaId, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				pstdadosProfessor.setInt(1, id);
				rsdadosProfessor = pstdadosProfessor.executeQuery();
				while(rsdadosProfessor.next()) {
					professor = new Professor(
						Integer.parseInt(rsdadosProfessor.getObject(1).toString()),
						rsdadosProfessor.getObject(2).toString(), 
						rsdadosProfessor.getObject(3).toString(),
						rsdadosProfessor.getObject(4).toString());
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			return professor;
	}
	
	public ArrayList<Aluno> getAlunosByTurmaId(int id){
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			connection = MySqlDatabase.getConnection();
			pstdadosAlunos = (PreparedStatement) connection.prepareStatement(sqlGetAlunosByTurmaId, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstdadosAlunos.setInt(1, id);
			rsdadosAlunos = pstdadosAlunos.executeQuery();
			while(rsdadosAlunos.next()) {
				alunos.add(new Aluno(
					Integer.parseInt(rsdadosAlunos.getObject(1).toString()),
					rsdadosAlunos.getObject(2).toString(), 
					rsdadosAlunos.getObject(3).toString(),
					rsdadosAlunos.getObject(4).toString()
					)
				);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return alunos;
	}
	
	public boolean matriculaAlunos(ArrayList<Aluno> alunos, int id_turma) {
		
		try {
			connection = MySqlDatabase.getConnection();
			connection.setAutoCommit(false);
			pstdadosAlunos = (PreparedStatement) connection.prepareStatement(sqlMatriculaAluno, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			for(Aluno aluno : alunos) {
				pstdadosAlunos.setInt(1, aluno.getId());
				pstdadosAlunos.setInt(2, id_turma);
				pstdados.executeUpdate();
			}
			
			connection.commit();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
