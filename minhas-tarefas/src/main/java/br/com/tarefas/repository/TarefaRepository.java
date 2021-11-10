package br.com.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}
