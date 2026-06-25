package pl.polsl.b11.kontrolery;

import jakarta.validation.Valid;
import pl.polsl.b11.dto.RejestracjaStudentaDto;
import pl.polsl.b11.dto.EdycjaStudentaDto;
import pl.polsl.b11.encje.Ocena;
import pl.polsl.b11.encje.Student;
import pl.polsl.b11.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studenci")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // POST http://localhost:8080/api/studenci
    @PostMapping
    public ResponseEntity<Student> zarejestrujStudenta(@Valid @RequestBody RejestracjaStudentaDto dto) {
        return ResponseEntity.ok(studentService.zarejestrujStudenta(dto));
    }

    // PUT http://localhost:8080/api/studenci/{nrAlbumu}
    @PutMapping("/{nrAlbumu}")
    public ResponseEntity<Student> edytujDaneStudenta(
            @PathVariable String nrAlbumu,
            @Valid @RequestBody EdycjaStudentaDto dto) {
        return ResponseEntity.ok(studentService.edytujDaneStudenta(nrAlbumu, dto));
    }

    // GET http://localhost:8080/api/studenci/{nrAlbumu}
    @GetMapping("/{nrAlbumu}")
    public ResponseEntity<Student> pobierzProfilStudenta(@PathVariable String nrAlbumu) {
        return ResponseEntity.ok(studentService.pobierzProfilStudenta(nrAlbumu));
    }

    // GET http://localhost:8080/api/studenci/123456/oceny?idPrzedmiotu=1
    @GetMapping("/{nrAlbumu}/oceny")
    public ResponseEntity<List<Ocena>> pobierzHistorieOcen(
            @PathVariable String nrAlbumu,
            @RequestParam(required = false) Integer idPrzedmiotu) {
        return ResponseEntity.ok(studentService.pobierzHistorieOcen(nrAlbumu, idPrzedmiotu));
    }
    // GET http://localhost:8080/api/studenci
    @GetMapping
    public ResponseEntity<List<Student>> pobierzWszystkichStudentow() {
        return ResponseEntity.ok(studentService.pobierzWszystkichStudentow());
    }
}