package br.com.tarefas.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.model.Tarefa;
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
	
	

}