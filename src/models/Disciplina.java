package models;

import lombok.Getter;
import shared.AModel;

public class Disciplina extends AModel {
	@Getter
	private String nome;
	
	public Disciplina(String string) {
		this.nome = nome;
	}

	public Disciplina() {
		// TODO Auto-generated constructor stub
	}

	public Disciplina(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
}
