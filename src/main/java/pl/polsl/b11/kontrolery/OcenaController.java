package pl.polsl.b11.kontrolery;

import jakarta.validation.Valid;
import pl.polsl.b11.dto.WystawienieOcenyDto;
import pl.polsl.b11.dto.KorektaOcenyDto;
import pl.polsl.b11.encje.Ocena;
import pl.polsl.b11.service.OcenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oceny")
@RequiredArgsConstructor
public class OcenaController {

    private final OcenaService ocenaService;

    // POST http://localhost:8080/api/oceny
    @PostMapping
    public ResponseEntity<Ocena> wystawOcene(@Valid @RequestBody WystawienieOcenyDto dto) {
        return ResponseEntity.ok(ocenaService.wystawOcene(dto));
    }

    // PUT http://localhost:8080/api/oceny/{idOceny}
    @PutMapping("/{idOceny}")
    public ResponseEntity<Ocena> zmienWartoscOceny(
            @PathVariable Integer idOceny,
            @Valid @RequestBody KorektaOcenyDto dto) {
        return ResponseEntity.ok(ocenaService.zmienWartoscOceny(idOceny, dto));
    }

    // DELETE http://localhost:8080/api/oceny/{idOceny}
    @DeleteMapping("/{idOceny}")
    public ResponseEntity<Void> usunOcene(@PathVariable Integer idOceny) {
        ocenaService.usunOcene(idOceny);
        return ResponseEntity.noContent().build();
    }

    // GET http://localhost:8080/api/oceny/raport/{idPrzedmiotu}
    @GetMapping("/raport/{idPrzedmiotu}")
    public ResponseEntity<List<Ocena>> pobierzOcenyDoRaportu(@PathVariable Integer idPrzedmiotu) {
        return ResponseEntity.ok(ocenaService.pobierzOcenyDoRaportu(idPrzedmiotu));
    }
    
    // GET http://localhost:8080/api/oceny/student/{nrAlbumu}/srednia?idPrzedmiotu=1
    @GetMapping("/student/{nrAlbumu}/srednia")
    public ResponseEntity<Double> pobierzSredniaStudenta(
            @PathVariable String nrAlbumu,
            @RequestParam(required = false) Integer idPrzedmiotu) {
        return ResponseEntity.ok(ocenaService.obliczSredniaStudenta(nrAlbumu, idPrzedmiotu));
    }

    // GET http://localhost:8080/api/oceny/przedmiot/1/srednia
    @GetMapping("/przedmiot/{idPrzedmiotu}/srednia")
    public ResponseEntity<Double> pobierzSredniaZPrzedmiotu(@PathVariable Integer idPrzedmiotu) {
        return ResponseEntity.ok(ocenaService.obliczSredniaZPrzedmiotu(idPrzedmiotu));
    }
}