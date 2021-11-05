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
		this.alunos.add(new Aluno(1, "Aluno1", "email@email.com.br", "0123456"));
		this.alunos.add(new Aluno(2, "Aluno2", "email2@email.com.br", "12345"));
	}

	@Override
	public boolean Salvar(Aluno aluno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Excluir(Aluno aluno) {
		// TODO Auto-generated method stub
		return false;
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
