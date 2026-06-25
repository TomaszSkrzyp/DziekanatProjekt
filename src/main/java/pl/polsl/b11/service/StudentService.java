package pl.polsl.b11.service;

import pl.polsl.b11.dto.RejestracjaStudentaDto;
import pl.polsl.b11.dto.EdycjaStudentaDto;
import pl.polsl.b11.encje.Kierunek;
import pl.polsl.b11.encje.Ocena;
import pl.polsl.b11.encje.Student;
import pl.polsl.b11.repozytoria.KierunekRepository;
import pl.polsl.b11.repozytoria.OcenaRepository;
import pl.polsl.b11.repozytoria.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final KierunekRepository kierunekRepository;
    private final OcenaRepository ocenaRepository;

    // Scenariusz 1.1: Rejestracja nowego studenta i przypisanie do kierunku
    @Transactional
    public Student zarejestrujStudenta(RejestracjaStudentaDto dto) {
        if (studentRepository.existsById(dto.getNrAlbumu())) {
            throw new RuntimeException("Student z numerem albumu " + dto.getNrAlbumu() + " już istnieje!");
        }
        Kierunek kierunek = kierunekRepository.findById(dto.getNazwaKierunku())
                .orElseThrow(() -> new RuntimeException("Kierunek nie istnieje"));

        Student student = new Student();
        student.setNrAlbumu(dto.getNrAlbumu());
        student.setImie(dto.getImie());
        student.setNazwisko(dto.getNazwisko());
        student.setKierunek(kierunek);

        return studentRepository.save(student);
    }

    // Scenariusz 1.2: Edycja danych osobowych studenta
    @Transactional
    public Student edytujDaneStudenta(String nrAlbumu, EdycjaStudentaDto dto) {
        Student student = studentRepository.findById(nrAlbumu)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono studenta o numerze albumu: " + nrAlbumu));

        student.setImie(dto.getImie());
        student.setNazwisko(dto.getNazwisko());

        return studentRepository.save(student);
    }

    // Scenariusz 1.3: Pobranie profilu studenta
    public Student pobierzProfilStudenta(String nrAlbumu) {
        return studentRepository.findById(nrAlbumu)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono studenta o numerze albumu: " + nrAlbumu));
    }

    // Scenariusz 1.4: Wyświetlenie historii ocen dla konkretnego studenta
    public List<Ocena> pobierzHistorieOcen(String nrAlbumu, Integer idPrzedmiotu) {
        if (!studentRepository.existsById(nrAlbumu)) {
            throw new RuntimeException("Student nie istnieje");
        }
        
        //jesli nie podano id przedmiotu, zwróć wszystkie
        if (idPrzedmiotu != null) {
            return ocenaRepository.findByStudentNrAlbumuAndPrzedmiotIdPrzedmiotu(nrAlbumu, idPrzedmiotu);
        }
        
        return ocenaRepository.findByStudentNrAlbumu(nrAlbumu);
    }

    //pobranie listy wszystkich studentow
    public List<Student> pobierzWszystkichStudentow() {
        return studentRepository.findAll();
    }
}