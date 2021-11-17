package shared.database.seeders;

import java.util.ArrayList;

import controllers.AlunoController;
import models.Aluno;

public class AlunoSeeder extends ASeeder<Aluno> {
	public AlunoSeeder() {
		this.controller = new AlunoController();
		ArrayList<Aluno> alunos = new ArrayList<Aluno>(){
			{
				add(new Aluno(0, "Matheus Silva", "matheussilva@gmail.com", "1916041"));
				add(new Aluno(0, "Fábio Silva", "fabiosilva@gmail.com", "1923233"));
			}
		};
		this.models = alunos;
	}
	
}
