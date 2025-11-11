package ArqObj.ProvaFinal.musica;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Integer> {
    public Musica findByTitulo(String titulo);
}
