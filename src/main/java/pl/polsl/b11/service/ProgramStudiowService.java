package pl.polsl.b11.service;

import pl.polsl.b11.encje.Kierunek;
import pl.polsl.b11.encje.Prowadzacy;
import pl.polsl.b11.encje.Przedmiot;
import pl.polsl.b11.repozytoria.KierunekRepository;
import pl.polsl.b11.repozytoria.ProwadzacyRepository;
import pl.polsl.b11.repozytoria.PrzedmiotRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramStudiowService {

    private final KierunekRepository kierunekRepository;
    private final PrzedmiotRepository przedmiotRepository;
    private final ProwadzacyRepository prowadzacyRepository;

    // Scenariusz 2.1: Dodanie nowego kierunku studiów
    @Transactional
    public Kierunek dodajKierunek(String nazwaKierunku) {
        Kierunek kierunek = new Kierunek();
        kierunek.setNazwaKierunku(nazwaKierunku);
        return kierunekRepository.save(kierunek);
    }

    // Scenariusz 2.2: Rejestracja nowego przedmiotu i przypisanie kierownika przedmiotu
    @Transactional
    public Przedmiot dodajPrzedmiot(String nazwaPrzedmiotu, String nazwaKierunku, Integer idKierownika) {
        Kierunek kierunek = kierunekRepository.findById(nazwaKierunku)
                .orElseThrow(() -> new RuntimeException("Kierunek nie istnieje"));

        Prowadzacy kierownik = prowadzacyRepository.findById(idKierownika)
                .orElseThrow(() -> new RuntimeException("Prowadzący (kierownik) nie istnieje"));

        Przedmiot przedmiot = new Przedmiot();
        przedmiot.setNazwa(nazwaPrzedmiotu);
        przedmiot.setKierunek(kierunek);
        przedmiot.setKierownik(kierownik);

        return przedmiotRepository.save(przedmiot);
    }


    // Scenariusz 2.3: Obsada kadrowa przedmiotu
    @Transactional
    public Prowadzacy dodajProwadzacego(String imie, String nazwisko) {
        Prowadzacy prowadzacy = new Prowadzacy();
        prowadzacy.setImie(imie);
        prowadzacy.setNazwisko(nazwisko);
        return prowadzacyRepository.save(prowadzacy);
    }

    @Transactional
    public Przedmiot przypiszProwadzacegoDoPrzedmiotu(Integer idPrzedmiotu, Integer idProwadzacego) {
        Przedmiot przedmiot = przedmiotRepository.findById(idPrzedmiotu)
                .orElseThrow(() -> new RuntimeException("Przedmiot nie istnieje"));

        Prowadzacy prowadzacy = prowadzacyRepository.findById(idProwadzacego)
                .orElseThrow(() -> new RuntimeException("Prowadzący nie istnieje"));

        if (przedmiot.getProwadzacy() == null) {
            przedmiot.setProwadzacy(new java.util.ArrayList<>());
        }
        przedmiot.getProwadzacy().add(prowadzacy);

        return przedmiotRepository.save(przedmiot);
    }

    public List<Prowadzacy> pobierzWszystkichProwadzacych() {
        return prowadzacyRepository.findAll();
    }

    public List<Przedmiot> pobierzPrzedmiotyDlaKierunku(String nazwaKierunku) {
        if (!kierunekRepository.existsById(nazwaKierunku)) {
            throw new RuntimeException("Kierunek nie istnieje");
        }
        return przedmiotRepository.findByKierunekNazwaKierunku(nazwaKierunku);
    }
    
    public List<Kierunek> pobierzWszystkieKierunki() {
        return kierunekRepository.findAll();
    }

}