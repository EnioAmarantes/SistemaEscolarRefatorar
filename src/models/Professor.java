package models;

import lombok.Getter;
import lombok.Setter;
import shared.APessoa;

public class Professor extends APessoa {
	@Getter @Setter
	private Disciplina disciplina;

	public Professor(int id, String name, String email, Disciplina disciplina2) {
		this.id = id;
		this.setNome(name);
		this.setEmail(email);
		this.setDisciplina(disciplina2);
	}

	public Professor() {
		// TODO Auto-generated constructor stub
	}

	public String getNomeDisciplina() {
		// TODO Auto-generated method stub
		return this.getNome() + " / " + this.getDisciplina();
	}
	
	@Override
	public String toString() {
		return getNomeDisciplina();
	}
}
