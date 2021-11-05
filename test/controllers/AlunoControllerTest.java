package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Aluno;

class AlunoControllerTest {
	
	private static AlunoController alunoController;
	private Aluno aluno;
	
	@BeforeAll
	static void iniciar(){
		alunoController = new AlunoController();
	}
	
	@BeforeEach
	void carregaAluno() {
		aluno = new Aluno(1, "NomeAluno", "email@email.com", "123456");
	}

	@Test
	void testAlunoController() {
		assertTrue(alunoController.alunos.isEmpty());
	}

	@Test
	void testSalvar() {
		fail("Not yet implemented");
	}

	@Test
	void testExcluir() {
		alunoController.Cria(aluno);
		
		assertEquals(aluno.getId(), alunoController.Excluir(aluno).getId());
	}

	@Test
	void testModificar() {
		fail("Not yet implemented");
	}

	@Test
	void testCria() {
		assertTrue(alunoController.Cria(aluno));
	}

	@Test
	void testLista() {
		fail("Not yet implemented");
	}

}
