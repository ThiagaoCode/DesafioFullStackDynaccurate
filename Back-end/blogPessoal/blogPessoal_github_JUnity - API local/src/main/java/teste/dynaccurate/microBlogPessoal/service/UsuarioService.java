package teste.dynaccurate.microBlogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64; // a que tinha ido no automatico -> org.apache.commons.codec.binary.Base64 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import teste.dynaccurate.microBlogPessoal.model.Usuario;
import teste.dynaccurate.microBlogPessoal.model.UsuarioLogin;
import teste.dynaccurate.microBlogPessoal.repository.UsuarioRepository;



@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		
		String senhaEnconder = enconder.encode(usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		
		return repository.save(usuario);
		
	}
	
	
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user){
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuarioContainingIgnoreCase(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(enconder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getSenha() + " : " + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII"))); 
				String authHeader = "Basic " + new String(encodedAuth); // criação do token
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				return user;
			}
			
		}
		
		return null;
		
	}
	
	public Optional<?> alterarUsuario(UsuarioLogin usuarioParaAlterar) {
		return repository.findById(usuarioParaAlterar.getId()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());

			usuarioExistente.setNome(usuarioParaAlterar.getNome());
			usuarioExistente.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(usuarioExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
