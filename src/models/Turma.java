package models;

import shared.AModel;

import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class Turma extends AModel{
	@Getter @Setter
	private String nome;
	@Getter @Setter
	private String codigo;
	@Getter @Setter
	private String sala;
	@Getter @Setter
	private Date ano;
	@Getter @Setter
	private Professor professor;
	@Getter
	private ArrayList<Aluno> alunos;
	
	public Turma(int id, String nome, String codigo, String sala, Date ano, Professor professor, ArrayList<Aluno> alunos) {
		this.id = id;
		this.setNome(nome);
		this.setCodigo(codigo);
		this.setSala(sala);
		this.setProfessor(professor);
		for(Aluno aluno : alunos) {
			this.alunos.add(aluno);
		}
	}

	public boolean matricularAluno(Aluno aluno) {
		return alunos.add(aluno);
	}
	
	public boolean desmatricularAluno(Aluno aluno) {
		return alunos.remove(aluno);
	}
}
