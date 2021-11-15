package br.com.tarefas.config;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.tarefas.model.ERole;
import br.com.tarefas.model.Role;
import br.com.tarefas.model.Tarefa;
import br.com.tarefas.model.TarefaCategoria;
import br.com.tarefas.model.TarefaStatus;
import br.com.tarefas.model.Usuario;
import br.com.tarefas.repository.RoleRepository;
import br.com.tarefas.repository.TarefaCategoriaRepository;
import br.com.tarefas.repository.TarefaRepository;
import br.com.tarefas.repository.UsuarioRepository;

@Configuration
//@Profile({"local","dev"})
@Profile("!prod")
public class CarregaBaseDeDados {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TarefaCategoriaRepository tarefaCategoriaRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Bean
	CommandLineRunner executar() {
		return args -> {
			
			Role roleAdmin = new Role(ERole.ROLE_ADMIN);
			roleAdmin = roleRepository.save(roleAdmin);
			
			Role roleUser = new Role(ERole.ROLE_USER);
			roleUser = roleRepository.save(roleUser);
			
			Usuario usuario = new Usuario();
			usuario.setNome("Admin");
			usuario.setSenha(encoder.encode("123456"));
			usuario.setRoles(Set.of(roleAdmin, roleUser));
			usuarioRepository.save(usuario);
			
			TarefaCategoria categoria = new TarefaCategoria();
			categoria.setNome("categoria 1");
			tarefaCategoriaRepository.save(categoria);
			
			Tarefa tarefa = new Tarefa();
			tarefa.setDescricao("tarefa 1");
			tarefa.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa.setStatus(TarefaStatus.ABERTO);
			tarefa.setVisivel(true);
			tarefa.setCategoria(categoria);
			tarefa.setUsuario(usuario);
			tarefaRepository.save(tarefa);
		};
		
	}
	
}
