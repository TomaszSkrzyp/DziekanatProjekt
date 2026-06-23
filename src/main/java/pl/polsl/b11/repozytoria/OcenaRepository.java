package pl.polsl.b11.repozytoria;

import pl.polsl.b11.encje.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OcenaRepository extends JpaRepository<Ocena, Integer> {
    List<Ocena> findByStudentNrAlbumu(String nrAlbumu);
    List<Ocena> findByPrzedmiotIdPrzedmiotu(Integer idPrzedmiotu);
    List<Ocena> findByStudentNrAlbumuAndPrzedmiotIdPrzedmiotu(String nrAlbumu, Integer idPrzedmiotu);
}
