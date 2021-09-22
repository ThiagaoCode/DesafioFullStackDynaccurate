package teste.dynaccurate.microBlogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import teste.dynaccurate.microBlogPessoal.model.Usuario;
import teste.dynaccurate.microBlogPessoal.model.UsuarioLogin;
import teste.dynaccurate.microBlogPessoal.repository.UsuarioRepository;
import teste.dynaccurate.microBlogPessoal.service.UsuarioService;
import io.swagger.annotations.ApiResponse;





@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation(value = "Salva novo usuario no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna usuario cadastrado"),
			@ApiResponse(code = 400, message = "Erro na requisição")
	})
	
	
	
	/* @PostMapping("/logar") 
	 * public ResponseEntity<Object> Logar(@Valid @RequestBody UsuarioLogin loginSenha) {
		Optional<?> objetoCredenciado = usuarioService.Logar(loginSenha);

		if (objetoCredenciado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCredenciado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}
	*/ 
	
	@PostMapping("/logar") 
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	
	
	@ApiOperation(value = "Autentica usuario no sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna credenciais de usuario"),
			@ApiResponse(code = 400, message = "Erro na requisição ou usuario não credenciado")
	})
	
	
	
	@PostMapping("/cadastrar")	
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	}
	/* 
	 * post cadastrar com falha
	 * public ResponseEntity<Object> CadastrarUsuario(@Valid @RequestBody Usuario novoUsuario) {
		Optional<Object> objetoCadastrado = usuarioService.CadastrarUsuario(novoUsuario);

		if (objetoCadastrado.isPresent()) {
			return ResponseEntity.status(201).body(objetoCadastrado.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}
	
	
	*/
	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	/*
	 * get all com verificacao -- deu falha no front
	 * 
	@GetMapping("/todes")
	public ResponseEntity<Object> buscarTodes() {
		List<Usuario> listaUsuarios = repository.findAll();

		if (listaUsuarios.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaUsuarios);
		}

	} */
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	/* 
	 * get by id com falha
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable(value = "id_usuario") Long id) {
		Optional<Usuario> objetoUsuario = repository.findById(id);
		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	*/
	
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(defaultValue = "") String nome) {
		return ResponseEntity.status(200).body(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	
	
	@PutMapping
	public ResponseEntity<Usuario> Put(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	/* 
	 * 
	 * Put usuario com falha
	@PutMapping("/alterar")
	public ResponseEntity<Object> alterar(@Valid @RequestBody UsuarioLogin novoUsuario) {
		Optional<?> objetoAlterado = usuarioService.alterarUsuario(novoUsuario);

		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	
	*/
	
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id_usuario") Long id) {
		Optional<Usuario> objetoExistente = repository.findById(id);
		if (objetoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}

}
