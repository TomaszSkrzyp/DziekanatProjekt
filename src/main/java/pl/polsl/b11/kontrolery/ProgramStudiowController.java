package pl.polsl.b11.kontrolery;

import pl.polsl.b11.encje.Kierunek;
import pl.polsl.b11.encje.Przedmiot;
import pl.polsl.b11.service.ProgramStudiowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/program")
@RequiredArgsConstructor
public class ProgramStudiowController {

    private final ProgramStudiowService programStudiowService;

    // POST http://localhost:8080/api/program/kierunki
    @PostMapping("/kierunki")
    public ResponseEntity<Kierunek> dodajKierunek(@RequestParam String nazwaKierunku) {
        return ResponseEntity.ok(programStudiowService.dodajKierunek(nazwaKierunku));
    }

    // POST http://localhost:8080/api/program/przedmioty
    @PostMapping("/przedmioty")
    public ResponseEntity<Przedmiot> dodajPrzedmiot(
            @RequestParam String nazwaPrzedmiotu,
            @RequestParam String nazwaKierunku,
            @RequestParam Integer idKierownika) {
        return ResponseEntity.ok(programStudiowService.dodajPrzedmiot(nazwaPrzedmiotu, nazwaKierunku, idKierownika));
    }

    // POST http://localhost:8080/api/program/prowadzacy
    @PostMapping("/prowadzacy")
    public ResponseEntity<pl.polsl.b11.encje.Prowadzacy> dodajProwadzacego(
            @RequestParam String imie,
            @RequestParam String nazwisko) {
        return ResponseEntity.ok(programStudiowService.dodajProwadzacego(imie, nazwisko));
    }

    // POST http://localhost:8080/api/program/przedmioty/{idPrzedmiotu}/prowadzacy
    @PostMapping("/przedmioty/{idPrzedmiotu}/prowadzacy")
    public ResponseEntity<Przedmiot> przypiszProwadzacegoDoPrzedmiotu(
            @PathVariable Integer idPrzedmiotu,
            @RequestParam Integer idProwadzacego) {
        return ResponseEntity.ok(programStudiowService.przypiszProwadzacegoDoPrzedmiotu(idPrzedmiotu, idProwadzacego));
    }

    // GET http://localhost:8080/api/program/prowadzacy
    @GetMapping("/prowadzacy")
    public ResponseEntity<List<pl.polsl.b11.encje.Prowadzacy>> pobierzWszystkichProwadzacych() {
        return ResponseEntity.ok(programStudiowService.pobierzWszystkichProwadzacych());
    }
    
    // GET http://localhost:8080/api/program/kierunki/{nazwaKierunku}/przedmioty
    @GetMapping("/kierunki/{nazwaKierunku}/przedmioty")
    public ResponseEntity<List<Przedmiot>> pobierzPrzedmiotyDlaKierunku(@PathVariable String nazwaKierunku) {
        return ResponseEntity.ok(programStudiowService.pobierzPrzedmiotyDlaKierunku(nazwaKierunku));
    }
    
    // GET http://localhost:8080/api/program/kierunki
    @GetMapping("/kierunki")
    public ResponseEntity<List<Kierunek>> pobierzWszystkieKierunki() {
        return ResponseEntity.ok(programStudiowService.pobierzWszystkieKierunki());
    }
}