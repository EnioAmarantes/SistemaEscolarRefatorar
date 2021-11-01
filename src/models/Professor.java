package models;

import lombok.Getter;
import lombok.Setter;
import shared.APessoa;

public class Professor extends APessoa {
	@Getter @Setter
	private String disciplina;
}
