package br.com.tarefas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class TarefaCategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Campo nome não pode estar vazio")
	@Size(min = 5, max = 50, message = "Campo nome deve ter entre 5 e 50 caracteres")
	@Column(length = 50)
	private String nome;

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
