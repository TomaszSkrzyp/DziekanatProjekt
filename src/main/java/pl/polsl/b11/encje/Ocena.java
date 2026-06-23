package pl.polsl.b11.encje;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "OCENY")
@Getter @Setter @NoArgsConstructor
public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOceny;
    
    private Float wartosc;

    @ManyToOne
    @JoinColumn(name = "nrAlbumuStudenta")
    //ignoruje jego listę ocen i kierunek w tym podglądzie
    @JsonIgnoreProperties({"oceny", "kierunek"})
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idPrzedmiotu")
    // odcina pobieranie kierowników, prowadzących i kierunku
    @JsonIgnoreProperties({"kierownik", "prowadzacy", "kierunek"})
    private Przedmiot przedmiot;

    private LocalDate dataWystawienia;
}