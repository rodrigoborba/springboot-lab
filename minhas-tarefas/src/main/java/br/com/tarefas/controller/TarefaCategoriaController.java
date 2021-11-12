package br.com.tarefas.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.request.TarefaCategoriaRequest;
import br.com.tarefas.controller.response.TarefaCategoriaResponse;
import br.com.tarefas.model.TarefaCategoria;
import br.com.tarefas.service.TarefaCategoriaService;

@RestController
@RequestMapping("/categoria")
public class TarefaCategoriaController {
	
	@Autowired
	private TarefaCategoriaService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public List<TarefaCategoriaResponse> listarCategorias(@PathVariable Integer id) {
		List<TarefaCategoria> categorias = service.listarTarefas();
		return categorias.stream()
				.map(categoria -> mapper.map(categoria, TarefaCategoriaResponse.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public TarefaCategoriaResponse consultarCategoria(@PathVariable Integer id) {
		return mapper.map(
				service.consultarCategoria(id), TarefaCategoriaResponse.class);
	}
	
	@PostMapping
	public TarefaCategoriaResponse salvarCategoria(@Valid @RequestBody TarefaCategoriaRequest categoriaRequest) {
		TarefaCategoria categoria = mapper.map(categoriaRequest, TarefaCategoria.class);
		return mapper.map(
				service.salvar(categoria), TarefaCategoriaResponse.class);
	}
	
	@DeleteMapping("/{id}")
	public void excluirCategoria(@PathVariable Integer id) {
		service.excluirPorId(id);
	}

}
