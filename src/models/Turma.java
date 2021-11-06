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
	
	public boolean matricularAluno(Aluno aluno) {
		return alunos.add(aluno);
	}
}
