package br.com.tarefas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.request.TarefaRequest;
import br.com.tarefas.controller.response.TarefaResponse;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
	
	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
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
	
	@GetMapping("/{id}")
	public EntityModel<TarefaResponse> consultarTarefa(@PathVariable Integer id) {
		Tarefa tarefa = tarefaService.consultarPorId(id);
		TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);
		
		EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse, 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).consultarTarefa(id)).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).listarTarefas(new HashMap<>())).withRel("tarefas"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaCategoriaController.class).consultarCategoria(tarefaResponse.getCategoriaId())).withRel("categoria"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).consultarUsuario(tarefaResponse.getUsuarioId())).withRel("usuario"));
		
		return tarefaModel;
	}
	
	@PostMapping
	public TarefaResponse salvarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest) {
		Tarefa tarefa = mapper.map(tarefaRequest, Tarefa.class);
		return mapper.map(tarefaService.salvar(tarefa), TarefaResponse.class);
	}
	
	@DeleteMapping("/{id}")
	public void deletarTarefa(@PathVariable Integer id) {
		tarefaService.excluirPorId(id);
	}

}
