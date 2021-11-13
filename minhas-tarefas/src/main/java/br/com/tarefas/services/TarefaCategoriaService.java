package br.com.tarefas.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.model.TarefaCategoria;
import br.com.tarefas.repository.TarefaCategoriaRepository;

@Service
public class TarefaCategoriaService {

	@Autowired
	TarefaCategoriaRepository tarefaCategoriaRepository;

	public List<TarefaCategoria> listarTarefas() {
		return tarefaCategoriaRepository.findAll();
	}

	public List<TarefaCategoria> consultarPorNome(String nome) {
		return tarefaCategoriaRepository.findByNomeLike("%" + nome + "%");
	}
	
	public TarefaCategoria consultarCategoria(Integer id) {
		return tarefaCategoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	

	public TarefaCategoria salvar(@Valid TarefaCategoria categoria) {
		return tarefaCategoriaRepository.save(categoria);
	}

	public void excluirPorId(Integer id) {
		tarefaCategoriaRepository.deleteById(id);
		
	}
	
}
