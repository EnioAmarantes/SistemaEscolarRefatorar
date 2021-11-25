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
				add(new Professor(0, "João Antonio", "zegatinho@gmail.com", new Disciplina(1, "programação orientada a objetos")));
				add(new Professor(0, "Fabricio Lopes", "fabrilopes@gmail.com", new Disciplina(2, "programação desktop")));
				add(new Professor(0, "Fulano de tal", "teste@gmail.com", new Disciplina(2, "programação distribuida")));
			}
		};
		this.models = professores;
	}
	
}
