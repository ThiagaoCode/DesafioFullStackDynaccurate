package teste.dynaccurate.microBlogPessoal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teste.dynaccurate.microBlogPessoal.model.Tema;
import teste.dynaccurate.microBlogPessoal.repository.TemaRepository;





@Service
public class TemaService {
	
	@Autowired
	private  TemaRepository repositorioT;
	
	public Optional<Tema> alterarTema(Tema temaParaAlterar) {
		return repositorioT.findById(temaParaAlterar.getId()).map(temaExistente -> {
			temaExistente.setDescricao(temaParaAlterar.getDescricao());
			return Optional.ofNullable(repositorioT.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
