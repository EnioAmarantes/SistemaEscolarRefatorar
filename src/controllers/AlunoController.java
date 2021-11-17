/**
 * 
 */
package controllers;

import java.util.ArrayList;

import models.Aluno;
import shared.IDao;

/**
 * @author enio
 *
 */
public class AlunoController implements IDao<Aluno> {
	
	ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	
	public AlunoController() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Aluno> Lista() {
		// TODO Auto-generated method stub
		return (ArrayList<Aluno>) alunos.clone();
	}
	
	@Override
	public boolean Cria(Aluno aluno) {
		boolean status = false;
		
		if(!alunos.contains(aluno));{
			status = alunos.add(aluno);			
		}
		
		return status;
	}
	
	@Override
	public Aluno Modificar(Aluno aluno) {
		int index = 0;
		
		for(Aluno al : alunos) {
			if(al.getId() == aluno.getId())
				index = alunos.indexOf(al);
		}
		
		return alunos.set(index, aluno);
	}
	
	@Override
	public Aluno Excluir(Aluno aluno) {
		int index = 0;
		
		for(Aluno al : alunos) {
			if(al.getId() == aluno.getId())
				index = alunos.indexOf(al);
		}
		
		return alunos.remove(index);
	}
}
