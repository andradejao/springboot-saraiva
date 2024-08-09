package br.com.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.domain.Dadospessoais;
import br.com.projeto.domain.Usuario;
import br.com.projeto.repository.DadosPessoaisRepository;


@RestController
@RequestMapping("/dadospessoais")
public class DadosPessoaisController {

	@Autowired
	private DadosPessoaisRepository dpr;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Dadospessoais dp) {
		dpr.save(dp);
		if(dp == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Dados Pessoais cadastrados");
	}
	
	@GetMapping("/listar")
	public List<Dadospessoais> listar(){
		return dpr.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public ResponseEntity<?> consultar(@PathVariable Integer id){
		Optional<Dadospessoais> cid = dpr.findById(id);
		if(!cid.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados Pessoais não encontrados");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cid);
	}
	
	@GetMapping("/consultarcpf/{cpf}")
	public ResponseEntity<?> consultarcpf(@PathVariable String cpf){
		Optional<Dadospessoais> conscpf = dpr.findByCpf(cpf);
		if(!conscpf.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados Pessoais não encontrados");
		}
		return ResponseEntity.status(HttpStatus.OK).body(conscpf);
	}
	
	@GetMapping("/consultaremail/{email}")
	public ResponseEntity<?> consultaremail(@PathVariable String email){
		Optional<Dadospessoais> consemail = dpr.findByEmail(email);
		if(!consemail.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados Pessoais não encontrados");
		}
		return ResponseEntity.status(HttpStatus.OK).body(consemail);
	}
	
	@PatchMapping("/alterarnome/{id}")
	public ResponseEntity<?> alterarnome(@PathVariable Integer id, @RequestBody Dadospessoais dp) {
		Optional<Dadospessoais> pessoa = dpr.findById(id);
		if(!pessoa.isPresent()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pessoa não encontrada");
		}
		dp.setIddadospessoais(id);
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Envie o id");
		}
		dp.setCpf(pessoa.get().getCpf());
		dp.setEmail(pessoa.get().getEmail());
		dp.setEndereco(pessoa.get().getEndereco());
		dp.setIdusuario(pessoa.get().getIdusuario());
		dp.setTelefone(pessoa.get().getTelefone());
		dpr.save(dp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Nome alterado");
	}
	
	@PatchMapping("/alteraremail/{id}")
	public ResponseEntity<?> alteraremail(@PathVariable Integer id, @RequestBody Dadospessoais dp) {
		Optional<Dadospessoais> pessoa = dpr.findById(id);
		if(!pessoa.isPresent()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pessoa não encontrada");
		}
		dp.setIddadospessoais(id);
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Envie o id");
		}
		dp.setNomecompleto(pessoa.get().getNomecompleto());
		dp.setCpf(pessoa.get().getCpf());
		dp.setEndereco(pessoa.get().getEndereco());
		dp.setIdusuario(pessoa.get().getIdusuario());
		dp.setTelefone(pessoa.get().getTelefone());
		dpr.save(dp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Email alterado");
	}
	
	@PatchMapping("/alterarendereco/{id}")
	public ResponseEntity<?> alterarendereco(@PathVariable Integer id, @RequestBody Dadospessoais dp) {
		Optional<Dadospessoais> pessoa = dpr.findById(id);
		if(!pessoa.isPresent()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pessoa não encontrada");
		}
		dp.setIddadospessoais(id);
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Envie o id");
		}
		dp.setNomecompleto(pessoa.get().getNomecompleto());
		dp.setCpf(pessoa.get().getCpf());
		dp.setEmail(pessoa.get().getEmail());
		dp.setIdusuario(pessoa.get().getIdusuario());
		dp.setTelefone(pessoa.get().getTelefone());
		dpr.save(dp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Endereço alterado");
	}
	@PatchMapping("/alterartelefone/{id}")
	public ResponseEntity<?> alterartelefone(@PathVariable Integer id, @RequestBody Dadospessoais dp) {
		Optional<Dadospessoais> pessoa = dpr.findById(id);
		if(!pessoa.isPresent()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Pessoa não encontrada");
		}
		dp.setIddadospessoais(id);
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Envie o id");
		}
		dp.setNomecompleto(pessoa.get().getNomecompleto());
		dp.setCpf(pessoa.get().getCpf());
		dp.setEndereco(pessoa.get().getEndereco());
		dp.setIdusuario(pessoa.get().getIdusuario());
		dp.setEmail(pessoa.get().getEmail());
		dpr.save(dp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Telefone alterado");
	}
	
	@DeleteMapping("/apagarpessoa/{id}")
	public ResponseEntity<?> apagarpessoa(@PathVariable Integer id) {
		Optional<Dadospessoais> people = dpr.findById(id);
		if(!people.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		dpr.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário apagado");
	}
	
}

