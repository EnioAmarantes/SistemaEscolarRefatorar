/**
 * 
 */
package controllers;

import java.util.ArrayList;

import models.Professor;
import shared.IDao;

/**
 * @author enio
 *
 */
public class ProfessorController implements IDao<Professor> {
	
	ArrayList<Professor> professores = new ArrayList<Professor>();
	
	public ProfessorController() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Professor> Lista() {
		// TODO Auto-generated method stub
		return (ArrayList<Professor>) professores.clone();
	}
	
	@Override
	public boolean Cria(Professor professor) {
		return professores.add(professor);
	}
	
	@Override
	public Professor Modificar(Professor professor) {
		int index = 0;
		
		for(Professor prof : professores) {
			if(prof.getId() == professor.getId())
				index = professores.indexOf(prof);
		}
		
		return professores.set(index, professor);
	}
	
	@Override
	public Professor Excluir(Professor professor) {
		int index = 0;
		
		for(Professor prof : professores) {
			if(prof.getId() == professor.getId())
				index = professores.indexOf(prof);
		}
		
		return professores.remove(index);
	}
}
