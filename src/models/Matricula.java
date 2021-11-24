package models;

import java.util.ArrayList;

public class Matricula {
	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	private ArrayList<Aluno> alunosMatriculados = new ArrayList<Aluno>();
	private ArrayList<Aluno> alunosRemoverMatricula = new ArrayList<Aluno>();
	
	public void matricularAluno(Aluno aluno) {
		alunosMatriculados.add(aluno);
		alunos.remove(aluno);
		alunosRemoverMatricula.remove(aluno);
	}
	
	public void desmatricularAluno(Aluno aluno) {
		alunos.add(aluno);
		alunosRemoverMatricula.add(aluno);
		alunosMatriculados.remove(aluno);
	}
	
	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos.addAll(alunos);
	}
	
	public void setAlunosMatriculados(ArrayList<Aluno> alunos) {
		this.alunosMatriculados.addAll(alunos);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Aluno> getAlunos(){
		return (ArrayList<Aluno>) alunos.clone();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Aluno> getAlunosRemoverMatricula(){
		return (ArrayList<Aluno>) alunosRemoverMatricula.clone();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Aluno> getAlunosMatriculados(){
		return (ArrayList<Aluno>) alunosMatriculados.clone();
	}
}
