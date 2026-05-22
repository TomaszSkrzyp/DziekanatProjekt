@Entity
@Table(name = "KIERUNKI")
@Getter @Setter @NoArgsConstructor
public class Kierunek {
    @Id
    private String nazwa_kierunku;

    @OneToMany(mappedBy = "kierunek")
    private List<Student> studenci;

    @OneToMany(mappedBy = "kierunek")
    private List<Przedmiot> przedmioty;
}