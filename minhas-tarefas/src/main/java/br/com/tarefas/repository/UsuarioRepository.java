package br.com.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
