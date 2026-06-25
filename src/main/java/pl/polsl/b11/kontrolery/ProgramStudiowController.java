package pl.polsl.b11.kontrolery;

import jakarta.validation.Valid;
import pl.polsl.b11.dto.DodanieKierunkuDto;
import pl.polsl.b11.dto.DodanieProwadzacegoDto;
import pl.polsl.b11.dto.DodaniePrzedmiotuDto;
import pl.polsl.b11.dto.PrzypisanieProwadzacegoDto;
import pl.polsl.b11.encje.Kierunek;
import pl.polsl.b11.encje.Prowadzacy;
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
    public ResponseEntity<Kierunek> dodajKierunek(@Valid @RequestBody DodanieKierunkuDto dto) {
        return ResponseEntity.ok(programStudiowService.dodajKierunek(dto));
    }

    // POST http://localhost:8080/api/program/przedmioty
    @PostMapping("/przedmioty")
    public ResponseEntity<Przedmiot> dodajPrzedmiot(@Valid @RequestBody DodaniePrzedmiotuDto dto) {
        return ResponseEntity.ok(programStudiowService.dodajPrzedmiot(dto));
    }

    // POST http://localhost:8080/api/program/prowadzacy
    @PostMapping("/prowadzacy")
    public ResponseEntity<Prowadzacy> dodajProwadzacego(@Valid @RequestBody DodanieProwadzacegoDto dto) {
        return ResponseEntity.ok(programStudiowService.dodajProwadzacego(dto));
    }

    // POST http://localhost:8080/api/program/przedmioty/{idPrzedmiotu}/prowadzacy
    @PostMapping("/przedmioty/{idPrzedmiotu}/prowadzacy")
    public ResponseEntity<Przedmiot> przypiszProwadzacegoDoPrzedmiotu(
            @PathVariable Integer idPrzedmiotu,
            @Valid @RequestBody PrzypisanieProwadzacegoDto dto) {
        return ResponseEntity.ok(programStudiowService.przypiszProwadzacegoDoPrzedmiotu(idPrzedmiotu, dto));
    }

    // GET http://localhost:8080/api/program/prowadzacy
    @GetMapping("/prowadzacy")
    public ResponseEntity<List<Prowadzacy>> pobierzWszystkichProwadzacych() {
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