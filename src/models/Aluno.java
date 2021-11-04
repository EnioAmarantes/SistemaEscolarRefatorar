package models;

import lombok.Getter;
import lombok.Setter;
import shared.APessoa;

public class Aluno extends APessoa {
	@Getter @Setter
	private String registro_academico;

	public Aluno(int id, String nome, String email, String registro_academico) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.setNome(nome);
		this.setEmail(email);
		this.setRegistro_academico(registro_academico);
	}
}
