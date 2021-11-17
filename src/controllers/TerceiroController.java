/**
 * 
 */
package controllers;

import java.util.ArrayList;

import models.Terceiro;
import shared.IDao;

/**
 * @author enio
 *
 */
public class TerceiroController implements IDao<Terceiro> {
	
	ArrayList<Terceiro> terceiros = new ArrayList<Terceiro>();
	
	public TerceiroController() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Terceiro> Lista() {
		// TODO Auto-generated method stub
		return (ArrayList<Terceiro>) terceiros.clone();
	}
	
	@Override
	public boolean Cria(Terceiro terceiro) {
		boolean criaStatus = IsValid(terceiro);
		
		if(criaStatus)
		criaStatus = terceiros.add(terceiro);
		
		return criaStatus;
	}
	
	@Override
	public Terceiro Modificar(Terceiro terceiro) {
		int index = 0;
		
		for(Terceiro al : terceiros) {
			if(al.getId() == terceiro.getId())
				index = terceiros.indexOf(al);
		}
		
		return terceiros.set(index, terceiro);
	}
	
	@Override
	public Terceiro Excluir(Terceiro terceiro) {
		int index = 0;
		
		for(Terceiro al : terceiros) {
			if(al.getId() == terceiro.getId())
				index = terceiros.indexOf(al);
		}
		
		return terceiros.remove(index);
	}

	@Override
	public boolean IsValid(Terceiro terceiro) {
		return !terceiros.contains(terceiro);
	}
}
