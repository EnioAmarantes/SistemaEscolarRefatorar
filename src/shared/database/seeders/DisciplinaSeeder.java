package shared.database.seeders;

import java.util.ArrayList;

import controllers.DisciplinaController;

import models.Disciplina;

public class DisciplinaSeeder extends ASeeder<Disciplina> {
	public DisciplinaSeeder() {
		this.controller = new DisciplinaController();
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>() {
			{
				add(new Disciplina(1, "Programa��o"));
				add(new Disciplina(2, "Matem�tica"));
			}
		};
		
		this.models = disciplinas;
	}
}
