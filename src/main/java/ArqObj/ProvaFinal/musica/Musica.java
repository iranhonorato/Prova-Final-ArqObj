package ArqObj.ProvaFinal.musica;


import ArqObj.ProvaFinal.playlist.Playlist;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true, nullable = false)
    private String titulo;


    @Column(nullable = false)
    private String artista;


    @Column(nullable = true)
    private String album;


    @Column(nullable = true)
    private Integer duracao;


    @Column(nullable = true)
    private String genero;


    @ManyToMany(mappedBy = "musicas", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Playlist> playlists = new HashSet<>();

    public Musica() {}


    public Musica(String titulo, String artista, String album, Integer duracao, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.duracao = duracao;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }
}
