package br.com.tarefas.controller.assembler;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.tarefas.controller.TarefaCategoriaController;
import br.com.tarefas.controller.TarefaController;
import br.com.tarefas.controller.UsuarioController;
import br.com.tarefas.controller.response.TarefaResponse;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaStatus;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {
		
		TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);
		
		EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse, 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).consultarTarefa(tarefa.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).listarTarefas(new HashMap<>())).withRel("tarefas"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaCategoriaController.class).consultarCategoria(tarefaResponse.getCategoriaId())).withRel("categoria"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).consultarUsuario(tarefaResponse.getUsuarioId())).withRel("usuario"));
		
		if(TarefaStatus.EM_ANDAMENTO.equals(tarefa.getStatus())) {
			tarefaModel.add(
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).concluirTarefa(tarefa.getId())).withRel("concluir"),
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).concluirTarefa(tarefa.getId())).withRel("cancelar")
					);
		}
		
		if(TarefaStatus.ABERTO.equals(tarefa.getStatus())) {
			tarefaModel.add(
					WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).iniciarTarefa(tarefa.getId())).withRel("iniciar")
					);
		}
		
		return tarefaModel;
	}
	
	

}
