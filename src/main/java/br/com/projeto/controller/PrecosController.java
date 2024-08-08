package br.com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.domain.Precos;
import br.com.projeto.repository.PrecosRepository;

@RestController
@RequestMapping("/precos")
public class PrecosController {

	@Autowired
	private PrecosRepository pr;
	
	@GetMapping("/listar")
	public List<Precos> listar(){
		return pr.findAll();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Precos p) {
		 pr.save(p);
		 if(p == null) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar");
		 }
		 return ResponseEntity.status(HttpStatus.CREATED).body("Preço cadastrado");
	}
}
