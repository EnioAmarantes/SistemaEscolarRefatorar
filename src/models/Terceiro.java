package models;

import shared.APessoa;
import lombok.Getter;
import lombok.Setter;

 
public class Terceiro extends APessoa {
	@Getter @Setter
	private Funcao funcao;
	
	public Terceiro(int id, String nome, String email, Funcao funcao) {
		this.id = id;
		this.setNome(nome);
		this.setEmail(email);
		this.setFuncao(funcao);
	}
}
