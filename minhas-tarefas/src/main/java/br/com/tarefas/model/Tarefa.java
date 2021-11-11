package br.com.tarefas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Tarefa")
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "{tarefa.descricao.not-blank}")
	@Size(min = 5, max = 150, message = "{tarefa.descricao.size}")
	@Column(name = "ds_tarefa", nullable = false, length = 150)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TarefaStatus status;
	
	@FutureOrPresent(message = "{tarefa.descricao.future-or-present}")
	@Column(name = "dt_entrega")
	private LocalDate dataEntrega;
	
	private Boolean visivel;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private TarefaCategoria categoria;
	
	@ManyToOne
	private Usuario usuario;
	
	
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public TarefaCategoria getCategoria() {
		return categoria;
	}
	public void setCategoria(TarefaCategoria categoria) {
		this.categoria = categoria;
	}
	public Boolean getVisivel() {
		return visivel;
	}
	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}
	
	
}
