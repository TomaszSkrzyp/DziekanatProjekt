package pl.polsl.b11.encje;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JsonBackReference(value = "student-ocena") //zapobiega nieskonczonej petli podczas mapowania studenta
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idPrzedmiotu")
    private Przedmiot przedmiot;

    private LocalDate dataWystawienia;
}