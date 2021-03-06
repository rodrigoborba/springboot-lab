package br.com.tarefas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tarefas.exception.TarefaStatusException;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.services.TarefaService;

@SpringBootTest
public class TarefaServiceIntegrationTest {
	
	@Autowired
	private TarefaService tarefaService;
	
	@Test
	void deveIniciarTarefaTest() {
		Tarefa tarefa = tarefaService.iniciarTarefaPorId(3);
		Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getStatus());
	}

	@Test
	void naoDeveIniciarTarefaTest() {
		Tarefa tarefa = tarefaService.consultarPorId(3);
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		tarefaService.salvar(tarefa);
		
		Assertions.assertThrows(TarefaStatusException.class ,() -> tarefaService.iniciarTarefaPorId(3));
	}
	
}
