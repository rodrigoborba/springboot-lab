package br.com.tarefas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialArray;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.response.TarefaResponse;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.service.TarefaService;

@RestController
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/tarefa")
	public List<TarefaResponse> listarTarefas(@RequestParam Map<String, String> parametros) {
		List<Tarefa> tarefas = new ArrayList<>();
		
		if(parametros.isEmpty()) {
			tarefas = tarefaService.listarTarefas();			
		} else {
			String descricao = parametros.get("descricao");
			tarefas = tarefaService.consultarPorDescricao(descricao);
		}
		
		List <TarefaResponse> response = tarefas
				.stream()
				.map(tarefa -> mapper.map(tarefa, TarefaResponse.class))
				.collect(Collectors.toList());
		return response;	
	}
	
	@GetMapping("/tarefa/{id}")
	public TarefaResponse consultarTarefa(@PathVariable Integer id) {
		Tarefa tarefa = tarefaService.consultarPorId(id);
		TarefaResponse response = mapper.map(tarefa, TarefaResponse.class);
		return response;
	}
	
	@PostMapping("/tarefa")
	public TarefaResponse salvarTarefa(@Valid @RequestBody Tarefa tarefa) {
		return mapper.map(tarefaService.salvar(tarefa), TarefaResponse.class);
	}
	
	@DeleteMapping("/tarefa/{id}")
	public void deletarTarefa(@PathVariable Integer id) {
		tarefaService.excluirPorId(id);
	}

}
