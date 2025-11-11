package ArqObj.ProvaFinal.playlist;


import ArqObj.ProvaFinal.musica.Musica;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true, nullable = false)
    private String nome;


    @Column(nullable = true)
    private String descricao;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate criadoEm;


    @Column(nullable = false)
    private String email;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "playlist_musicas",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    @JsonManagedReference
    private Set<Musica> musicas = new HashSet<>();


    public Playlist() {}


    public Playlist(String nome, String descricao,String email) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Set<Musica> listarMusicas() {
        return this.musicas;
    }


}
