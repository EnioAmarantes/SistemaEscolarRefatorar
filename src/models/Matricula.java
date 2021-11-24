package models;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class Matricula {
	@Getter @Setter
	private ArrayList<Aluno> alunos;
	@Getter @Setter
	private ArrayList<Aluno> alunosMatriculados;
	@Getter
	private ArrayList<Aluno> alunosRemoverMatricula;
	
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
}
