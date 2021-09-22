package teste.dynaccurate.microBlogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.dynaccurate.microBlogPessoal.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);

}
