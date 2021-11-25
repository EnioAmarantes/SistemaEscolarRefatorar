package shared.database.seeders;

import java.util.ArrayList;

import controllers.ProfessorController;
import models.Disciplina;
import models.Professor;

public class ProfessorSeeder extends ASeeder<Professor> {
	public ProfessorSeeder() {
		this.controller = new ProfessorController();
		ArrayList<Professor> professores = new ArrayList<Professor>(){
			{
				add(new Professor(0, "Jo�o Antonio", "zegatinho@gmail.com", new Disciplina(1, "programa��o orientada a objetos")));
				add(new Professor(0, "Fabricio Lopes", "fabrilopes@gmail.com", new Disciplina(2, "programa��o desktop")));
				add(new Professor(0, "Fulano de tal", "teste@gmail.com", new Disciplina(2, "programa��o distribuida")));
			}
		};
		this.models = professores;
	}
	
}
