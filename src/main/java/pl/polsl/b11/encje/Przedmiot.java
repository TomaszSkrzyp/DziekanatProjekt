package pl.polsl.b11.encje;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "PRZEDMIOTY")
@Getter @Setter @NoArgsConstructor
public class Przedmiot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrzedmiotu;
    
    private String nazwa;

    @ManyToOne
    @JoinColumn(name = "idKierownika")
    @JsonManagedReference(value = "przedmiot-kierownik")
    private Prowadzacy kierownik;

    @ManyToMany
    @JoinTable(
        name = "PRZEDMIOT_PROWADZACY",
        joinColumns = @JoinColumn(name = "idPrzedmiotu"),
        inverseJoinColumns = @JoinColumn(name = "idProwadzacego")
    )
    @JsonManagedReference(value = "przedmiot-prowadzacy")
    private List<Prowadzacy> prowadzacy;

    @ManyToOne
    @JoinColumn(name = "nazwaKierunku")
    @JsonManagedReference(value = "przedmiot-kierunek")
    private Kierunek kierunek;

    @OneToMany(mappedBy = "przedmiot")
    @JsonBackReference(value = "przedmiot-ocena")
    private List<Ocena> oceny;
}