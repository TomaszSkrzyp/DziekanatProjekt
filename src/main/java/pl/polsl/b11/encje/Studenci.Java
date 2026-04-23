@Entity
@Table(name = "STUDENCI")
@Getter @Setter @NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_studenta;
    
    @Column(unique = true)
    private String nr_albumu;
    
    private String imie;
    private String nazwisko;

    @ManyToOne
    @JoinColumn(name = "id_kierunku")
    private Kierunek kierunek;

    @OneToMany(mappedBy = "student")
    private List<Ocena> oceny;
}