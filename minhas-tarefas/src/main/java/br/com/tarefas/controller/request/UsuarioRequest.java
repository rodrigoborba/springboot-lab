package br.com.tarefas.controller.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {
	
	private Integer id;
	
	@NotBlank(message = "Campo nome não pode estar vazio")
	@Size(min = 5, max = 255)
	private String nome;
	
	@NotBlank(message = "Campo senha não pode estar vazio")
	@Size(min = 6, max = 12)
	private String senha;
	
	private Set<RoleRequest> roles;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<RoleRequest> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleRequest> roles) {
		this.roles = roles;
	}
	
	

}
