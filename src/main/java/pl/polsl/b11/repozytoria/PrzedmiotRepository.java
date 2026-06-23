package pl.polsl.b11.repozytoria;

import pl.polsl.b11.encje.Przedmiot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrzedmiotRepository extends JpaRepository<Przedmiot, Integer> {
    
    List<Przedmiot> findByKierunekNazwaKierunku(String nazwaKierunku);
}