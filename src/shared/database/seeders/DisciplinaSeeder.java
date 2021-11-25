package shared.database.seeders;

import java.util.ArrayList;

import controllers.DisciplinaController;

import models.Disciplina;

public class DisciplinaSeeder extends ASeeder<Disciplina> {
	public DisciplinaSeeder() {
		this.controller = new DisciplinaController();
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>() {
			{
				add(new Disciplina(1, "Programação"));
				add(new Disciplina(2, "Matemática"));
			}
		};
		
		this.models = disciplinas;
	}
}
