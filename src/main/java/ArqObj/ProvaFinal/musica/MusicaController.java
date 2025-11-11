package ArqObj.ProvaFinal.musica;

import ArqObj.ProvaFinal.autenticacao.Usuario;
import ArqObj.ProvaFinal.autenticacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<List<Musica>> listarMusicas() {
        try {
            List<Musica> lista = musicaService.listarMusicas();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Musica> cadastrarMusica(
            @RequestBody Musica novaMusica,
            @RequestHeader("Authorization") String token) {

        Usuario usuario = usuarioService.validarToken(token);

        Musica salvo = musicaService.cadastrarMusica(novaMusica);
        return ResponseEntity.ok(salvo);
    }


    @DeleteMapping("/{titulo}")
    public void deletarMusica(
            @PathVariable String titulo,
            @RequestHeader(name = "token") String token) {
        Usuario usuario = usuarioService.validarToken(token);
        musicaService.deletarMusica(titulo);
    }
}