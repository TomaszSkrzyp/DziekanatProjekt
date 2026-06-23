package pl.polsl.b11.repozytoria;

import pl.polsl.b11.encje.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    
    List<Student> findByKierunekNazwaKierunku(String nazwaKierunku);
}