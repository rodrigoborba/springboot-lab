package br.com.tarefas.controller.response;

import java.util.List;

import br.com.tarefas.model.ERole;

public class JwtResponse {
	
	private String token;
	private String type =  "Bearer";
	private Integer id;
	private String username;
	private List<String> roles;
	
	public JwtResponse(String token, Integer id, String username, List<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}
	
	public boolean isAdmin() {
		return roles.contains(ERole.ROLE_ADMIN.name());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
