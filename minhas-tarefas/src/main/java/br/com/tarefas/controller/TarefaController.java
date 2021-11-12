package br.com.tarefas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.assembler.TarefaModelAssembler;
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
	
	@Autowired
	private TarefaModelAssembler assembler;
	
	@GetMapping
	public CollectionModel<EntityModel<TarefaResponse>> listarTarefas(@RequestParam Map<String, String> parametros) {
		List<Tarefa> tarefas = new ArrayList<>();
		
		if(parametros.isEmpty()) {
			tarefas = tarefaService.listarTarefas();			
		} else {
			String descricao = parametros.get("descricao");
			tarefas = tarefaService.consultarPorDescricao(descricao);
		}
		
		List <EntityModel<TarefaResponse>> responseModel = tarefas
				.stream()
				.map(assembler:: toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(responseModel,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).listarTarefas(new HashMap<>())).withSelfRel()
				);	
	}
	
	@GetMapping("/{id}")
	public EntityModel<TarefaResponse> consultarTarefa(@PathVariable Integer id) {
		Tarefa tarefa = tarefaService.consultarPorId(id);
		return assembler.toModel(tarefa);
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<TarefaResponse>> salvarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest) {
		Tarefa tarefa = mapper.map(tarefaRequest, Tarefa.class);
		Tarefa tarefaSalva = tarefaService.salvar(tarefa);
				
		EntityModel<TarefaResponse> tarefaModel = assembler.toModel(tarefaSalva);
		
		return ResponseEntity.created(tarefaModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(tarefaModel);
	}
	
	@DeleteMapping("/{id}")
	public void deletarTarefa(@PathVariable Integer id) {
		tarefaService.excluirPorId(id);
	}
	
	@PutMapping("/{id}/iniciar")
	public EntityModel<TarefaResponse> iniciarTarefa(@PathVariable Integer id) {
		Tarefa tarefa = tarefaService.iniciarTarefaPorId(id);
		return assembler.toModel(tarefa);
	}
	
	@PutMapping("/{id}/concluir")
	public EntityModel<TarefaResponse> concluirTarefa(@PathVariable Integer id) {
		Tarefa tarefa = tarefaService.concluirTarefaPorId(id);
		return assembler.toModel(tarefa);
	}
	
	@PutMapping("/{id}/cancelar")
	public EntityModel<TarefaResponse> cancelarTarefa(@PathVariable Integer id) {
		Tarefa tarefa = tarefaService.cancelarTarefaPorId(id);
		return assembler.toModel(tarefa);
	}

}
