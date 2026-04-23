@Entity
@Table(name = "PRZEDMIOTY")
@Getter @Setter @NoArgsConstructor
public class Przedmiot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_przedmiotu;
    
    private String nazwa;

    @ManyToOne
    @JoinColumn(name = "id_kierownika")
    private Prowadzacy kierownik;

    @ManyToOne
    @JoinColumn(name = "id_kierunku")
    private Kierunek kierunek;

    @OneToMany(mappedBy = "przedmiot")
    private List<Ocena> oceny;
}