package br.com.tarefas.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tarefas.controller.response.JwtResponse;
import br.com.tarefas.model.Role;
import br.com.tarefas.model.Usuario;
import br.com.tarefas.repository.RoleRepository;
import br.com.tarefas.repository.UsuarioRepository;
import br.com.tarefas.security.JwtUtils;
import br.com.tarefas.security.UserDetailsImpl;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public Usuario consultarUsuarioPorId(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public Usuario salvar(Usuario usuario) {
		Set<Role> roles = getRoles(usuario);
		usuario.setRoles(roles);
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	private Set<Role> getRoles(Usuario usuario){
		Set<Role> rolesBanco = usuario.getRoles()
					.stream()
					.map(role -> roleRepository.findByName(role.getName()))
					.collect(Collectors.toSet());
		
		return rolesBanco;
					
	}

	public Usuario atualizar(Integer id, Usuario usuario) {
		if(!usuarioRepository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		usuario.setId(id);
		
		return salvar(usuario);
	}
	
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}

	public JwtResponse autenticarUsuario(String nome, String senha) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nome, senha));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
	}

}
