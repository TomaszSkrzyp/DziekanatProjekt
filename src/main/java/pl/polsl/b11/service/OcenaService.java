package pl.polsl.b11.service;

import pl.polsl.b11.encje.Ocena;
import pl.polsl.b11.encje.Student;
import pl.polsl.b11.encje.Przedmiot;
import pl.polsl.b11.repozytoria.OcenaRepository;
import pl.polsl.b11.repozytoria.StudentRepository;
import pl.polsl.b11.repozytoria.PrzedmiotRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OcenaService {

    private final OcenaRepository ocenaRepository;
    private final StudentRepository studentRepository;
    private final PrzedmiotRepository przedmiotRepository;

    // Scenariusz 3.1: Wystawienie oceny
    @Transactional
    public Ocena wystawOcene(String nrAlbumu, Integer idPrzedmiotu, Float wartosc) {
        Student student = studentRepository.findById(nrAlbumu)
                .orElseThrow(() -> new RuntimeException("Student nie istnieje"));

        Przedmiot przedmiot = przedmiotRepository.findById(idPrzedmiotu)
                .orElseThrow(() -> new RuntimeException("Przedmiot nie istnieje"));

        Ocena ocena = new Ocena();
        ocena.setStudent(student);
        ocena.setPrzedmiot(przedmiot);
        ocena.setWartosc(wartosc);
        ocena.setDataWystawienia(LocalDate.now());

        return ocenaRepository.save(ocena);
    }

    // Scenariusz 3.2: Korekta oceny
    @Transactional
    public Ocena zmienWartoscOceny(Integer idOceny, Float nowaWartosc) {
        Ocena ocena = ocenaRepository.findById(idOceny)
                .orElseThrow(() -> new RuntimeException("Ocena nie istnieje"));
        
        ocena.setWartosc(nowaWartosc);
        ocena.setDataWystawienia(LocalDate.now()); 
        return ocenaRepository.save(ocena);
    }

    // Usunięcie oceny z systemu
    @Transactional
    public void usunOcene(Integer idOceny) {
        if (!ocenaRepository.existsById(idOceny)) {
            throw new RuntimeException("Ocena nie istnieje");
        }
        ocenaRepository.deleteById(idOceny);
    }

    // Scenariusz 3.3: Kontrola wyników i zaliczeń (Lista ocen do raportu)
    public List<Ocena> pobierzOcenyDoRaportu(Integer idPrzedmiotu) {
       return ocenaRepository.findByPrzedmiotIdPrzedmiotu(idPrzedmiotu);
    }

    // Scenariusz 3.3: Kontrola wyników i zaliczeń (Średnia przedmiotu)
    public Double obliczSredniaZPrzedmiotu(Integer idPrzedmiotu) {
        if (!przedmiotRepository.existsById(idPrzedmiotu)) {
            throw new RuntimeException("Przedmiot nie istnieje");
        }

        List<Ocena> oceny = ocenaRepository.findByPrzedmiotIdPrzedmiotu(idPrzedmiotu);
        
        if (oceny.isEmpty()) {
            return 0.0;
        }

        return oceny.stream().mapToDouble(Ocena::getWartosc).average().orElse(0.0);
    }

    // Scenariusz 1.4: Kontrola historii ocen i postępów studenta 
    public Double obliczSredniaStudenta(String nrAlbumu, Integer idPrzedmiotu) {
        if (!studentRepository.existsById(nrAlbumu)) {
            throw new RuntimeException("Student nie istnieje");
        }
        
        List<Ocena> oceny;
        if (idPrzedmiotu != null) {
            oceny = ocenaRepository.findByStudentNrAlbumuAndPrzedmiotIdPrzedmiotu(nrAlbumu, idPrzedmiotu);
        } else {
            oceny = ocenaRepository.findByStudentNrAlbumu(nrAlbumu);
        }

        if (oceny.isEmpty()) {
            return 0.0;
        }

        return oceny.stream().mapToDouble(Ocena::getWartosc).average().orElse(0.0);
    }
}