package teste.dynaccurate.microBlogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import teste.dynaccurate.microBlogPessoal.model.Usuario;
import teste.dynaccurate.microBlogPessoal.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositorioTeste {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		Usuario usuario = new Usuario(0L, "Frederico da Silva", "frederico@email.com.br", "13465278", data);
		if (!usuarioRepository.findByUsuarioContainingIgnoreCase(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278", data);
		if (!usuarioRepository.findByUsuarioContainingIgnoreCase(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0L, "Gabriela Silva", "gabis@email.com.br", "13465278", data);
		if (!usuarioRepository.findByUsuarioContainingIgnoreCase(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

		usuario = new Usuario(0L, "Tamaia Sanrs", "tama@email.com.br", "13465278", data);
		if (!usuarioRepository.findByUsuarioContainingIgnoreCase(usuario.getUsuario()).isPresent())
			usuarioRepository.save(usuario);

	}

	@Test
	@DisplayName("💾 Retorna o nome")
	public void findByNomeRetornaNome() {
		Usuario usuario = usuarioRepository.findByNome("Paulo Antunes");
		assertTrue(usuario.getNome().equals("Paulo Antunes"));
	}

	@Test
	@DisplayName("💾 Retorna 2 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaDoisUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(2, listaDeUsuarios.size());
	}

	@AfterAll
	public void end() {
		System.out.println("Teste Finalizado!");
	}

}
