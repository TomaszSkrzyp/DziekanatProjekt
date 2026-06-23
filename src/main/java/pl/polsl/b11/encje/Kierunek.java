package pl.polsl.b11.encje;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "KIERUNKI")
@Getter @Setter @NoArgsConstructor
public class Kierunek {
    @Id
    private String nazwaKierunku;

    @OneToMany(mappedBy = "kierunek")
    @JsonBackReference(value = "student-kierunek") 
    private List<Student> studenci;

    @OneToMany(mappedBy = "kierunek")
    @JsonBackReference(value = "przedmiot-kierunek")
    private List<Przedmiot> przedmioty;
}