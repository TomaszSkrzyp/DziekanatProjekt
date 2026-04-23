@Entity
@Table(name = "OCENY")
@Getter @Setter @NoArgsConstructor
public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_oceny;
    
    private Float wartosc;

    @ManyToOne
    @JoinColumn(name = "id_studenta")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_przedmiotu")
    private Przedmiot przedmiot;

    @ManyToOne
    @JoinColumn(name = "id_prowadzacego")
    private Prowadzacy prowadzacy;

    @Temporal(TemporalType.DATE)
    private Date data_wystawienia;
}