package br.com.tarefas.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tarefas.model.ERole;
import br.com.tarefas.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	@Query(" select role from Usuario user "
			+ " inner join user.roles role "
			+ " where user.id = ?1 ")
	public Set<Role> findRolesByUserId(Integer usuarioId);
	
	public Role findByName(ERole name);

}
