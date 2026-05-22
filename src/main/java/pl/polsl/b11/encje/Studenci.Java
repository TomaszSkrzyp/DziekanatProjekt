@Entity
@Table(name = "STUDENCI")
@Getter @Setter @NoArgsConstructor
public class Student {
    @Id
    private String nr_albumu;
    
    private String imie;
    private String nazwisko;

    @ManyToOne
    @JoinColumn(name = "nazwa_kierunku")
    private Kierunek kierunek;

    @OneToMany(mappedBy = "student")
    private List<Ocena> oceny;
}