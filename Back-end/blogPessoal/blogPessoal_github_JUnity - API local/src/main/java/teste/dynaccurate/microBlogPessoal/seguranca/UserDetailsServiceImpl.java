package teste.dynaccurate.microBlogPessoal.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import teste.dynaccurate.microBlogPessoal.model.Usuario;
import teste.dynaccurate.microBlogPessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName)  throws UsernameNotFoundException {
		Optional<Usuario> user = userRepository.findByUsuarioContainingIgnoreCase(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		
		/* user details em if --> forma diferente do codigo a cima
		 * 
		 * if (usuario.isPresent()){
		 * return new UserDetailsImpl(usuario.get());
		 * }
		 * else {
		 * throw new UsernameNotFoundException(userName + " not found.");
		 * }
		 * 
		 * */
		
		
		return user.map(UserDetailsImpl::new).get();
	}
	
	

}
