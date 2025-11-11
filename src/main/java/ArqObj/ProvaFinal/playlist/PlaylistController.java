package ArqObj.ProvaFinal.playlist;


import ArqObj.ProvaFinal.autenticacao.Usuario;
import ArqObj.ProvaFinal.autenticacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Playlist>> listarPlaylists() {
        try {
            List<Playlist> lista = playlistService.listarPlaylists();
            return ResponseEntity.ok(lista);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> buscarPorId(@PathVariable Integer id) {
        try {
            Playlist playlist = playlistService.findeById(id);
            return ResponseEntity.ok(playlist);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Playlist> cadastrarPlaylist(
            @RequestBody Playlist novaPlaylist,
            @RequestHeader("Authorization") String token) {

        Usuario usuario = usuarioService.validarToken(token);

        Playlist salvo = playlistService.cadastrarPlaylist(novaPlaylist);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public void deletarPlaylist(
            @PathVariable Integer id,
            @RequestHeader(name = "token") String token) {

        Usuario usuario = usuarioService.validarToken(token);

        playlistService.deletarPlaylist(id);
    }


    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<?> adicionarMusicaNaPlaylist(
            @PathVariable Integer playlistId,
            @PathVariable Integer musicaId,
            @RequestHeader("Authorization") String token) {

        try {
            Usuario usuario = usuarioService.validarToken(token);
            Playlist atualizada = playlistService.adicionarMusicaNaPlaylist(playlistId, musicaId);
            return ResponseEntity.ok(atualizada);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
        }
    }

    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<?> removerMusicaDaPlaylist(
            @PathVariable Integer playlistId,
            @PathVariable Integer musicaId,
            @RequestHeader("Authorization") String token) {

        try {
            Usuario usuario = usuarioService.validarToken(token);
            Playlist atualizada = playlistService.removerMusicaDaPlaylist(playlistId, musicaId);
            return ResponseEntity.ok(atualizada);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
        }
    }
}
