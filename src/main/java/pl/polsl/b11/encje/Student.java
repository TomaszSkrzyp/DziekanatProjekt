package pl.polsl.b11.encje;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "STUDENCI")
@Getter @Setter @NoArgsConstructor
public class Student {
    @Id
    private String nrAlbumu;
    
    private String imie;
    private String nazwisko;

    @ManyToOne
    @JoinColumn(name = "nazwaKierunku")
    private Kierunek kierunek;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference(value = "student-ocena")
    private List<Ocena> oceny;
}