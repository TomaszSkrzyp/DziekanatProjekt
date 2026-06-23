package pl.polsl.b11.service;

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
public Student zarejestrujStudenta(String nrAlbumu, String imie, String nazwisko, String nazwaKierunku) {
    if (studentRepository.existsById(nrAlbumu)) {
        throw new RuntimeException("Student z numerem albumu " + nrAlbumu + " już istnieje!");
    }

    // Reszta Twojego dotychczasowego kodu:
    Kierunek kierunek = kierunekRepository.findById(nazwaKierunku)
            .orElseThrow(() -> new RuntimeException("Kierunek nie istnieje"));

    Student student = new Student();
    student.setNrAlbumu(nrAlbumu);
    student.setImie(imie);
    student.setNazwisko(nazwisko);
    student.setKierunek(kierunek);

    return studentRepository.save(student);
}

    // Scenariusz 1.2: Edycja danych osobowych studenta
    @Transactional
    public Student edytujDaneStudenta(String nrAlbumu, String noweImie, String noweNazwisko) {
        Student student = studentRepository.findById(nrAlbumu)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono studenta o numerze albumu: " + nrAlbumu));

        student.setImie(noweImie);
        student.setNazwisko(noweNazwisko);

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

    public List<Student> pobierzWszystkichStudentow() {
        return studentRepository.findAll();
    }
}