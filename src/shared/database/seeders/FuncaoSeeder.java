package shared.database.seeders;

import java.util.ArrayList;

import controllers.FuncaoController;
import models.Funcao;

public class FuncaoSeeder extends ASeeder<Funcao> {
	public FuncaoSeeder() {
		this.controller = new FuncaoController();
		ArrayList<Funcao> funcoes = new ArrayList<Funcao>() {
			{
				add(new Funcao(1, "Professor Substituto"));
				add(new Funcao(2, "Auxiliar"));
			}
		};
		
		this.models = funcoes;
	}
}
