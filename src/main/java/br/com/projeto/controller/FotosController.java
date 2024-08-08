package br.com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.domain.Fotos;
import br.com.projeto.repository.FotosRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@Autowired
	private FotosRepository fr;
	
	@GetMapping("/listar")
	public List<Fotos> listar(){
		return fr.findAll();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Fotos f) {
		fr.save(f);
		if(f == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Fotos cadastradas");
	}
	
}
