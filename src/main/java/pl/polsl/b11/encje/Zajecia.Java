@Entity
@Table(name = "ZAJECIA")
@Getter @Setter @NoArgsConstructor
public class Zajecia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_zajecia;

    @ManyToOne
    @JoinColumn(name = "id_przedmiotu")
    private Przedmiot przedmiot;

    @ManyToOne
    @JoinColumn(name = "id_prowadzacego")
    private Prowadzacy prowadzacy;

    @ManyToOne
    @JoinColumn(name = "id_typu")
    private TypZajec typZajec;

    private Integer liczba_godzin;
}