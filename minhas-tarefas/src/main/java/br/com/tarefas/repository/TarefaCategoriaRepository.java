package br.com.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.model.TarefaCategoria;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria, Integer>{

}
