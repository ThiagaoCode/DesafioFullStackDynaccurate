package teste.dynaccurate.microBlogPessoal.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teste.dynaccurate.microBlogPessoal.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByUsuarioContainingIgnoreCase(String usuario);
	
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
	
	public Usuario findByNome(String nome);

}
