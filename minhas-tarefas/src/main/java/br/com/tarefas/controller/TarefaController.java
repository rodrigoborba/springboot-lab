package br.com.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.model.Tarefa;
import br.com.tarefas.repository.TarefaRepository;

@RestController
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@GetMapping("/tarefa")
	public List<Tarefa> listarTarefas() {
		return tarefaRepository.findAll();
	}
	
	@GetMapping("/tarefa/{id}")
	public Tarefa consultarTarefa(@PathVariable Integer id) {
		return tarefaRepository.findById(id).orElse(null);
	}
	
	@PostMapping("/tarefa")
	public Tarefa salvarTarefa(@RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	
	@DeleteMapping("/tarefa/{id}")
	public void deletarTarefa(@PathVariable Integer id) {
		tarefaRepository.deleteById(id);
	}

}
