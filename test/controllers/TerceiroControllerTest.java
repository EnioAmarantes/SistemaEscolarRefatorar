package controllers;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Funcao;
import models.Terceiro;

class TerceiroControllerTest {
	
	private static TerceiroController terceiroController;
	private Terceiro terceiro;
	
	@BeforeAll
	static void iniciar(){
		terceiroController = new TerceiroController();
	}
	
	@BeforeEach
	void carregaTerceiro() {
		terceiro = new Terceiro(1, "NomeTerceiro", "email@email.com", new Funcao("Funcao"));
	}

	@Test
	void testTerceiroController() {
		assertTrue(terceiroController.terceiros.isEmpty());
	}

	@Test
	void testSalvar() {
		fail("Not yet implemented");
	}

	@Test
	void testExcluir() {
		terceiroController.Cria(terceiro);
		
		assertEquals(terceiro.getId(), terceiroController.Excluir(terceiro).getId());
	}

	@Test
	void testModificar() {
		terceiroController.Cria(terceiro);
        Terceiro TerceiroModificar = new Terceiro(1, "NomeTerceiroModificado", "emailmodificado@email.com", new Funcao("FuncaoModificada"));
        terceiroController.Modificar(TerceiroModificar);
        assertEquals(terceiroController.terceiros.get(0).id, TerceiroModificar.id);
        assertEquals(terceiroController.terceiros.get(0).getNome(), TerceiroModificar.getNome());
        assertEquals(terceiroController.terceiros.get(0).getEmail(), TerceiroModificar.getEmail());
        assertEquals(terceiroController.terceiros.get(0).getFuncao(), TerceiroModificar.getFuncao());
	}

	@Test
	void testCria() {
		assertTrue(terceiroController.Cria(terceiro));
	}

	@Test
	void testLista() {
		fail("Not yet implemented");
	}

}
