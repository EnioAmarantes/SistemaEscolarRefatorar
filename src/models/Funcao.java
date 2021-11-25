package models;

import lombok.Getter;
import shared.AModel;

public class Funcao extends AModel {
	@Getter
	private String nome;
	
	public Funcao(String string) {
		this.nome = nome;
	}

	public Funcao(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
}
