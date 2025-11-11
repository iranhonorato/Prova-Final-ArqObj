package ArqObj.ProvaFinal.musica;


import ArqObj.ProvaFinal.autenticacao.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository repository;


    public List<Musica> listarMusicas() {
        return repository.findAll();
    }


    public Musica cadastrarMusica(Musica novaMusica) {
        if (novaMusica == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "É necessário inserir os dados da música");
        }
        if (novaMusica.getTitulo() == null || novaMusica.getTitulo().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Observe se todos os campos estão preenchidos corretamente");
        }
        repository.save(novaMusica);
        return novaMusica;
    }



    public void deletarMusica(String titulo) {
        Musica existente =  repository.findByTitulo(titulo);
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada");
        }
        repository.delete(existente);
    }
}
