package pl.polsl.b11.repozytoria;

import pl.polsl.b11.encje.Kierunek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KierunekRepository extends JpaRepository<Kierunek, String> {
}