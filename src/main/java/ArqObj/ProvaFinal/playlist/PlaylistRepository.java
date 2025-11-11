package ArqObj.ProvaFinal.playlist;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    public Playlist findByNome(String nome);

}
