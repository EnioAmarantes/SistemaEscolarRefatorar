package shared.database.seeders;

import shared.IDao;

import java.util.ArrayList;

import shared.AModel;

public abstract class ASeeder<t extends AModel> {

	protected IDao<t> controller;
	protected ArrayList<t> models;
	
	public void run() {
		for(t model : models) {
			try {
				controller.Cria(model);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
