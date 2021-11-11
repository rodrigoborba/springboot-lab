package br.com.tarefas.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.model.Tarefa;
import br.com.tarefas.service.TarefaService;

@RestController
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping("/tarefa")
	public List<Tarefa> listarTarefas(@RequestParam Map<String, String> parametros) {
		if(parametros.isEmpty()) {
			return tarefaService.listarTarefas();			
		}
		String descricao = parametros.get("descricao");
		return tarefaService.consultarPorDescricao(descricao);
	}
	
	@GetMapping("/tarefa/{id}")
	public Tarefa consultarTarefa(@PathVariable Integer id) {
		return tarefaService.consultarPorId(id);
	}
	
	@PostMapping("/tarefa")
	public Tarefa salvarTarefa(@Valid @RequestBody Tarefa tarefa) {
		return tarefaService.salvar(tarefa);
	}
	
	@DeleteMapping("/tarefa/{id}")
	public void deletarTarefa(@PathVariable Integer id) {
		tarefaService.excluirPorId(id);
	}

}
