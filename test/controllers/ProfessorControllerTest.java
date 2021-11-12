package controllers;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Professor;

class ProfessorControllerTest {
	
	private static ProfessorController professorController;
	private Professor professor;
	
	@BeforeAll
	static void iniciar(){
		professorController = new ProfessorController();
	}
	
	@BeforeEach
	void carregaProfessor() {
		professor = new Professor(1, "NomeProfessor", "email@email.com", "Disciplina");
	}

	@Test
	void testProfessorController() {
		assertTrue(professorController.professores.isEmpty());
	}

	@Test
	void testSalvar() {
		fail("Not yet implemented");
	}

	@Test
	void testExcluir() {
		professorController.Cria(professor);
		
		assertEquals(professor.getId(), professorController.Excluir(professor).getId());
	}

	@Test
	void testModificar() {
		professorController.Cria(professor);
        Professor professorModificar = new Professor(1, "NomeProfessorModificado", "emailmodificado@email.com", "DisciplinaModificada");
        professorController.Modificar(professorModificar);
        assertEquals(professorController.professores.get(0).id, professorModificar.id);
        assertEquals(professorController.professores.get(0).getNome(), professorModificar.getNome());
        assertEquals(professorController.professores.get(0).getEmail(), professorModificar.getEmail());
        assertEquals(professorController.professores.get(0).getDisciplina(), professorModificar.getDisciplina());
	}

	@Test
	void testCria() {
		assertTrue(professorController.Cria(professor));
	}

	@Test
	void testLista() {
		fail("Not yet implemented");
	}

}
