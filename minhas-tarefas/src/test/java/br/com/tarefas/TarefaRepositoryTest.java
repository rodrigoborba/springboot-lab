package br.com.tarefas;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tarefas.model.Tarefa;
import br.com.tarefas.repository.TarefaRepository;

@SpringBootTest
public class TarefaRepositoryTest {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Test
	void testFindByDescricao() {
		
		List<Tarefa> tarefas = tarefaRepository.findByDescricaoLike("tarefa");
		Assertions.assertEquals(1, tarefas.size());
	}
	
	@Test
	void testFindByNomeCategoria() {
		
		List<Tarefa> tarefas = tarefaRepository.findByNomeCategoria("categoria 1");
		Assertions.assertEquals(1, tarefas.size());
	}

}
