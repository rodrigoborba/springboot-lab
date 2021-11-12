package br.com.tarefas.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.exception.TarefaStatusException;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.repository.TarefaRepository;

@Service
public class TarefaService {
	
	@Autowired
	private TarefaRepository tarefaRepository;

	public List<Tarefa> listarTarefas() {
		return tarefaRepository.findAll();
	}

	public List<Tarefa> consultarPorDescricao(String descricao) {
		return tarefaRepository.findByDescricaoLike("%" + descricao + "%");
	}

	public Tarefa consultarPorId(Integer id) {
		return tarefaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public Tarefa salvar(@Valid Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}

	public void excluirPorId(Integer id) {
		tarefaRepository.deleteById(id);
		
	}
	
	public Tarefa iniciarTarefaPorId(Integer id) {
		Tarefa tarefa = consultarPorId(id);
		
		if(!TarefaStatus.ABERTO.equals(tarefa.getStatus())) {
			throw new TarefaStatusException();
		}
		
		tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	public Tarefa concluirTarefaPorId(Integer id) {
		Tarefa tarefa = consultarPorId(id);
		
		if(TarefaStatus.CANCELADA.equals(tarefa.getStatus())) {
			throw new TarefaStatusException();
		}
		
		tarefa.setStatus(TarefaStatus.CONCLUIDA);
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	public Tarefa cancelarTarefaPorId(Integer id) {
		Tarefa tarefa = consultarPorId(id);
		
		if(TarefaStatus.CANCELADA.equals(tarefa.getStatus())) {
			throw new TarefaStatusException();
		}
		
		tarefa.setStatus(TarefaStatus.CANCELADA);
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	

}
