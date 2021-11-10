package br.com.tarefas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tarefa")
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "ds_tarefa", nullable = false, length = 150)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TarefaStatus status;
	
	@Column(name = "dt_entrega")
	private LocalDate dataEntrega;
	
	private Boolean visivel;
	
//	private TarefaCategoria categoria;
//	
//	private Usuario usuario;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TarefaStatus getStatus() {
		return status;
	}
	public void setStatus(TarefaStatus status) {
		this.status = status;
	}
	public LocalDate getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getVisivel() {
		return visivel;
	}
	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}
	
	
}
