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

import br.com.projeto.domain.Usuario;
import br.com.projeto.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository ur;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody Usuario us) {
		ur.save(us);
		if(us == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado");
	}
	
	@GetMapping("/listar")
	public List<Usuario> listar(){
		return ur.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public ResponseEntity<?> consultar(@PathVariable Integer id) {
		Optional<Usuario> cid = ur.findById(id);
		if(!cid.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cid);
		
	}
	
	@GetMapping("/consultarusuario/{nomeusuario}")
	public ResponseEntity<?> consultarusuario(@PathVariable String nomeusuario){
		Optional<Usuario> cnu = ur.findByNomeusuario(nomeusuario);
		if(!cnu.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cnu);
	}
	
	@PatchMapping("/alterarfoto/{id}")
	public ResponseEntity<?> alterarfoto(@PathVariable Integer id, @RequestBody Usuario us) {
		Optional<Usuario> user = ur.findById(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		us.setIdusuario(id);
		us.setNomeusuario(user.get().getNomeusuario());
		us.setSenha(user.get().getNomeusuario());
		us.setDataalteracao(user.get().getDataalteracao());
		ur.save(us);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Foto alterada");
	}
	
	@PatchMapping("/alterarsenha/{id}")
	public ResponseEntity<?> alterarsenha(@PathVariable Integer id, @RequestBody Usuario us) {
		Optional<Usuario> user = ur.findById(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		us.setIdusuario(id);
		us.setNomeusuario(user.get().getNomeusuario());
		us.setFoto(user.get().getFoto());
		us.setDataalteracao(user.get().getDataalteracao());
		ur.save(us);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Senha alterada");
	}
	
	@DeleteMapping("/apagarusuario/{id}")
	public ResponseEntity<?> apagarusuario(@PathVariable Integer id) {
		Optional<Usuario> user = ur.findById(id);
		if(!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		ur.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário apagado");
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody Usuario us) {
		Usuario user = ur.findByNomeusuario(us.getNomeusuario(), us.getSenha());
		if(user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("[{idusuario:'"+user.getIdusuario()+"'"+"nomeusuario:'"
		+user.getNomeusuario()+"',"+"foto:'"+user.getFoto()+"}]");
	}
	
//	@PostMapping("/login")
//	public String login(@RequestBody Usuario us) {
//		String msg = "";
//		Usuario user = ur.findByNomeusuario(us.getNomeusuario(), us.getSenha());
//		if(user == null) {
//			msg = "Usuário ou senha inválidos";
//		}
//		else {
//			msg = "Autenticado";
//		}
//		return msg;
//	}
	
}


