package models;

import lombok.Getter;
import lombok.Setter;
import shared.APessoa;

public class Professor extends APessoa {
	@Getter @Setter
	private String disciplina;

	public Professor(int id, String name, String email, String disciplina) {
		this.id = id;
		this.setNome(name);
		this.setEmail(email);
		this.setDisciplina(disciplina);
	}

	public String getNomeDisciplina() {
		// TODO Auto-generated method stub
		return this.getNome() + " / " + this.getDisciplina();
	}
}
