package br.com.tarefas.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.tarefas.exception.TarefaStatusException;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {
	
	@Mock
	private TarefaRepository tarefaRepository;
	
	@InjectMocks
	private TarefaService service;
	
	@Test
	void naoDeveConcluirTarefa() {
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(1);
		tarefa.setDescricao("teste 1 ");
		tarefa.setStatus(TarefaStatus.CANCELADA);
		
		Mockito.when(tarefaRepository.findById(1)).thenReturn(Optional.of(tarefa ));
		
		Assertions.assertThrows(TarefaStatusException.class, () -> service.concluirTarefaPorId(1));
	}

}
