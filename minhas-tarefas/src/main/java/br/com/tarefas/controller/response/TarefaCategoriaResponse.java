package br.com.tarefas.controller.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TarefaCategoriaResponse {
	
	private Integer id;
	
	@NotBlank(message = "Campo nome não pode estar vazio")
	@Size(min = 5, max = 50, message = "Campo nome deve ter entre 5 e 50 caracteres")
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
