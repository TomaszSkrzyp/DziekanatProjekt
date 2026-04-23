@Entity
@Table(name = "KIERUNKI")
@Getter @Setter @NoArgsConstructor
public class Kierunek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_kierunku;
    
    private String nazwa;

    @OneToMany(mappedBy = "kierunek")
    private List<Student> studenci;

    @OneToMany(mappedBy = "kierunek")
    private List<Przedmiot> przedmioty;
}