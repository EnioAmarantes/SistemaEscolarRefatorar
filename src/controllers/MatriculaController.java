package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Getter;
import models.Matricula;
import models.Turma;
import shared.database.MySqlDatabase;
import models.Aluno;

public class MatriculaController {

	private Turma turma;
	@Getter
	private Matricula matricula;
	private AlunoController alunoController;
	
	private ResultSet rsdados = null;
	private Connection connection = null;
	private PreparedStatement pstdados = null;
	
	private static final String sqlGetAlunosMatriculados = "SELECT a.id_aluno, a.nome, a.email, a.registro_academico FROM aluno a inner join matricula_turma_aluno mta on mta.id_aluno = a.id_aluno WHERE mta.id_turma = ? ORDER BY a.nome ASC";
	private static final String sqlDesmatricularAluno = "INSERT INTO matricula_turma_aluno (id_aluno, id_turma) VALUES ( ?, ? )";
	private static final String sqlMatricularAluno = "DELETE FROM matricula_turma_aluno where id_aluno = ? and id_turma = ?";
	
	public MatriculaController(int id_turma) {
		this.turma = new TurmaController().getTurmaById(id_turma);
		this.matricula = new Matricula();
		this.alunoController = new AlunoController();
		
		LoadAlunos();
		
	}
	
	
	private void LoadAlunos() {
		this.matricula.setAlunosMatriculados(getAlunosMatriculados());
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		for(Aluno aluno : alunoController.Lista()) {
			if(!this.matricula.getAlunosMatriculados().contains(aluno)) {
				alunos.add(aluno);
			}
		}
		this.matricula.setAlunos(alunos);
		
	}
	
	private ArrayList<Aluno> getAlunosMatriculados() {
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		try {
			connection = MySqlDatabase.getConnection();
			pstdados = connection.prepareStatement(sqlGetAlunosMatriculados, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstdados.setInt(1, turma.getId());
			rsdados = pstdados.executeQuery();
			
			while (rsdados.next()) {
				Aluno aluno = new Aluno(
						Integer.parseInt(rsdados.getObject(1).toString()),
						rsdados.getObject(2).toString(), 
						rsdados.getObject(3).toString(),
						rsdados.getObject(4).toString()
						);

				alunos.add(aluno);
			}

		} catch (SQLException erro) {
			System.out.println("Erro ao executar consulta = " + erro);
		}

		return alunos;
	}

	
	public boolean MatricularAlunos(ArrayList<Aluno> alunos) {
	    try {
	        connection = MySqlDatabase.getConnection();
	        pstdados = (PreparedStatement) connection.prepareStatement(
	            sqlMatricularAluno, 
	            ResultSet.TYPE_SCROLL_SENSITIVE,
	            ResultSet.CONCUR_UPDATABLE);
	        connection.setAutoCommit(false);
	        for(Aluno aluno : alunos) {
	        	pstdados.setInt(1, aluno.getId());
	        	pstdados.setInt(2, turma.getId());
	        	
	        	pstdados.executeUpdate();	        	
	        }
	        connection.commit();
	        return true;
	      } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	      }
	}
	
	public boolean DesmatricularAlunos(ArrayList<Aluno> alunos) {
	    try {
	        connection = MySqlDatabase.getConnection();
	        pstdados = (PreparedStatement) connection.prepareStatement(
	            sqlDesmatricularAluno, 
	            ResultSet.TYPE_SCROLL_SENSITIVE,
	            ResultSet.CONCUR_UPDATABLE);
	        connection.setAutoCommit(false);
	        for(Aluno aluno : alunos) {
	        	pstdados.setInt(1, aluno.getId());
	        	pstdados.setInt(2, turma.getId());
	        	
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
