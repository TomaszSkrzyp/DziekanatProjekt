@Entity
@Table(name = "PROWADZACY")
@Getter @Setter @NoArgsConstructor
public class Prowadzacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_prowadzacego;
    
    private String imie;
    private String nazwisko;

    @OneToMany(mappedBy = "kierownik")
    private List<Przedmiot> przedmiotyKierowane;

    @ManyToMany(mappedBy = "prowadzacy")
    private List<Przedmiot> przedmioty;
}