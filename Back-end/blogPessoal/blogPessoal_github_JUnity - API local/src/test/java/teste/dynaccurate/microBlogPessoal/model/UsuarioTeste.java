package teste.dynaccurate.microBlogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import teste.dynaccurate.microBlogPessoal.model.Usuario;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTeste {
	
	public Usuario usuario;
	public Usuario usuarioNulo = new Usuario();
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
	LocalDate data = LocalDate.parse("1995-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	usuario = new Usuario(0L, "Thiago Pedroso", "thiagao", "13465278", data);
	} 
	
	@Test
	@DisplayName("✔ Valida Atributos Não Nulos")
	void testValidaAtributos() {
		
	Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
	
	System.out.println(violacao.toString());
	
	assertTrue(violacao.isEmpty());
	} 
	
	
	@Test
	@DisplayName("✖ Não Valida Atributos Nulos")
	void testNaoValidaAtributos() {
		
	Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioNulo);
	
	System.out.println(violacao.toString());
	
	assertFalse(violacao.isEmpty());
	
	}
	
	
	}


