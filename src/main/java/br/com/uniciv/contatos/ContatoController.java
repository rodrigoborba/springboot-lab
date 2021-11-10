package br.com.uniciv.contatos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@GetMapping("/contato")
	public List<Contato> todosContatos(){
		return contatoRepository.findAll();
	}
	
	@PostMapping("/contato")
	public Contato salvar(@RequestBody Contato contato) {
		return contatoRepository.save(contato);
	}
	
}
