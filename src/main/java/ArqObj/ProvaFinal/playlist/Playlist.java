package ArqObj.ProvaFinal.playlist;


import ArqObj.ProvaFinal.musica.Musica;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    @Column(nullable = true)
    private final List<Musica> musicas = new ArrayList<>();


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


    public List<Musica> listarMusicas() {
        return this.musicas;
    }


}
