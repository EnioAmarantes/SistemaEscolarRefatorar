package shared.database.seeders;

import java.util.ArrayList;

import controllers.ProfessorController;
import models.Professor;

public class ProfessorSeeder extends ASeeder<Professor> {
	public ProfessorSeeder() {
		this.controller = new ProfessorController();
		ArrayList<Professor> professores = new ArrayList<Professor>(){
			{
				add(new Professor(0, "João Antonio", "zegatinho@gmail.com", "programação orientada a objetos"));
				add(new Professor(0, "Fabricio Lopes", "fabrilopes@gmail.com", "programação desktop"));
				add(new Professor(0, "Fulano de tal", "teste@gmail.com", "programação distribuida"));
			}
		};
		this.models = professores;
	}
	
}
