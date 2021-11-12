package br.com.tarefas.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.model.Usuario;
import br.com.tarefas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario consultarUsuarioPorId(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

}
