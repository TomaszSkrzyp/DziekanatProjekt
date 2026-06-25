package pl.polsl.b11.encje;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "PROWADZACY")
@Getter @Setter @NoArgsConstructor
public class Prowadzacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProwadzacego;
    
    private String imie;
    private String nazwisko;

    @OneToMany(mappedBy = "kierownik")
    @JsonBackReference(value = "przedmiot-kierownik")
    private List<Przedmiot> przedmiotyKierowane;

    @ManyToMany(mappedBy = "prowadzacy")
    @JsonBackReference(value = "przedmiot-prowadzacy")
    private List<Przedmiot> przedmioty;
}
