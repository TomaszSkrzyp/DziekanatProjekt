package pl.polsl.b11.service;

import pl.polsl.b11.dto.DodanieKierunkuDto;
import pl.polsl.b11.dto.DodanieProwadzacegoDto;
import pl.polsl.b11.dto.DodaniePrzedmiotuDto;
import pl.polsl.b11.dto.PrzypisanieProwadzacegoDto;
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
    public Kierunek dodajKierunek(DodanieKierunkuDto dto) {
        Kierunek kierunek = new Kierunek();
        kierunek.setNazwaKierunku(dto.getNazwaKierunku());
        return kierunekRepository.save(kierunek);
    }

    // Scenariusz 2.2: Rejestracja nowego przedmiotu i przypisanie kierownika przedmiotu
    @Transactional
    public Przedmiot dodajPrzedmiot(DodaniePrzedmiotuDto dto) {
        Kierunek kierunek = kierunekRepository.findById(dto.getNazwaKierunku())
                .orElseThrow(() -> new RuntimeException("Kierunek nie istnieje"));

        Prowadzacy kierownik = prowadzacyRepository.findById(dto.getIdKierownika())
                .orElseThrow(() -> new RuntimeException("Prowadzący (kierownik) nie istnieje"));

        Przedmiot przedmiot = new Przedmiot();
        przedmiot.setNazwa(dto.getNazwaPrzedmiotu());
        przedmiot.setKierunek(kierunek);
        przedmiot.setKierownik(kierownik);

        return przedmiotRepository.save(przedmiot);
    }

    // Scenariusz 2.3: Obsada kadrowa przedmiotu (dodanie prowadzacego)
    @Transactional
    public Prowadzacy dodajProwadzacego(DodanieProwadzacegoDto dto) {
        Prowadzacy prowadzacy = new Prowadzacy();
        prowadzacy.setImie(dto.getImie());
        prowadzacy.setNazwisko(dto.getNazwisko());
        return prowadzacyRepository.save(prowadzacy);
    }

    // Scenariusz 2.3: Obsada kadrowa przedmiotu (przypisanie do przedmiotu)
    @Transactional
    public Przedmiot przypiszProwadzacegoDoPrzedmiotu(Integer idPrzedmiotu, PrzypisanieProwadzacegoDto dto) {
        Przedmiot przedmiot = przedmiotRepository.findById(idPrzedmiotu)
                .orElseThrow(() -> new RuntimeException("Przedmiot nie istnieje"));

        Prowadzacy prowadzacy = prowadzacyRepository.findById(dto.getIdProwadzacego())
                .orElseThrow(() -> new RuntimeException("Prowadzący nie istnieje"));

        if (przedmiot.getProwadzacy() == null) {
            przedmiot.setProwadzacy(new java.util.ArrayList<>());
        }
        przedmiot.getProwadzacy().add(prowadzacy);

        return przedmiotRepository.save(przedmiot);
    }

    //pobranie listy wszystkich prowadzacych
    public List<Prowadzacy> pobierzWszystkichProwadzacych() {
        return prowadzacyRepository.findAll();
    }

    //wyswietlanie przedmiotow przypisanych do wybranego kierunku
    public List<Przedmiot> pobierzPrzedmiotyDlaKierunku(String nazwaKierunku) {
        if (!kierunekRepository.existsById(nazwaKierunku)) {
            throw new RuntimeException("Kierunek nie istnieje");
        }
        return przedmiotRepository.findByKierunekNazwaKierunku(nazwaKierunku);
    }
    
    //pobranie listy wszystkich kierunków
    public List<Kierunek> pobierzWszystkieKierunki() {
        return kierunekRepository.findAll();
    }
}