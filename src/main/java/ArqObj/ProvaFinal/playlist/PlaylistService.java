package ArqObj.ProvaFinal.playlist;


import ArqObj.ProvaFinal.musica.Musica;
import ArqObj.ProvaFinal.musica.MusicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository repository;

    @Autowired
    private MusicaRepository musicaRepository;

    public Playlist findeById(Integer id) {
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não enconrada.");
        }
    }


    public List<Playlist> listarPlaylists() {
        return repository.findAll();
    }

    public Playlist cadastrarPlaylist(Playlist novaPlaylist) {
        if (repository.findByNome(novaPlaylist.getNome()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa playlist já existe");
        }

        if (novaPlaylist == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "É necessário inserir os dados da playlist.");
        }

        if (novaPlaylist.getNome() == null || novaPlaylist.getNome().isBlank() || novaPlaylist.getNome().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Observe se todos os campos estão preenchidos corretamente.");
        }


        return repository.save(novaPlaylist);
    }



    @Transactional
    public Playlist adicionarMusicaNaPlaylist(Integer playlistId, Integer musicaId) {

        Playlist playlist;
        try {
            playlist = repository.findById(playlistId).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada");
        }

        Musica musica;
        try {
            musica = musicaRepository.findById(musicaId).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada");
        }

        playlist.adicionarMusica(musica);

        return repository.save(playlist);
    }

    @Transactional
    public Playlist removerMusicaDaPlaylist(Integer playlistId, Integer musicaId) {
        Playlist playlist;
        try {
            playlist = repository.findById(playlistId).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada");
        }

        Musica musica;
        try {
            musica = musicaRepository.findById(musicaId).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada");
        }

        if (!playlist.getMusicas().contains(musica)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A música não pertence a essa playlist");
        }

        playlist.removerMusica(musica);

        return repository.save(playlist);
    }

    @Transactional
    public void deletarPlaylist(Integer playlistId) {

        Playlist playlist;
        try {
            playlist = repository.findById(playlistId).get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada");
        }

        if (playlist.getMusicas() != null && !playlist.getMusicas().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não é possível excluir: a playlist contém uma ou mais músicas");
        }

        repository.delete(playlist);
    }

}
