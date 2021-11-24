package models;

import shared.AModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private Professor professor = new Professor();
	@Getter
	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	
	public Turma(int id, String nome, String codigo, String sala, String ano, Professor professor, ArrayList<Aluno> alunos){		
		this.id = id;
		this.setNome(nome);
		this.setCodigo(codigo);
		this.setSala(sala);
		fillAno(ano);
		this.setProfessor(professor);
		this.alunos.addAll(alunos);
	}

	public Turma() {
		// TODO Auto-generated constructor stub
	}

	private void fillAno(String ano) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy"); 
		
		try {
			this.setAno(formato.parse(ano));			
		}catch(ParseException ex) {
			System.out.println(ex.getMessage());
		}
				
		
	}

	public boolean matricularAluno(Aluno aluno) {
		return alunos.add(aluno);
	}
	
	public boolean desmatricularAluno(Aluno aluno) {
		return alunos.remove(aluno);
	}
}
