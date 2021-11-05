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

	@Override
	public boolean Salvar(Aluno aluno) {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public boolean Modificar(Aluno aluno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Cria(Aluno aluno) {
		return alunos.add(aluno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Aluno> Lista() {
		// TODO Auto-generated method stub
		return (ArrayList<Aluno>) alunos.clone();
	}

}
