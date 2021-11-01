package models;

import lombok.Getter;
import lombok.Setter;
import shared.APessoa;

public class Aluno extends APessoa {
	@Getter @Setter
	private String registro_academico;
}
