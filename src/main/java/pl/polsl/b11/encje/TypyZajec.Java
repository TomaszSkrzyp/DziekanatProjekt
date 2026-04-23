@Entity
@Table(name = "TYPY_ZAJEC")
@Getter @Setter @NoArgsConstructor
public class TypZajec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_typu;
    
    private String nazwa;

    @OneToMany(mappedBy = "typZajec")
    private List<Zajecia> zajecia;
}