package pl.polsl.b11.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RejestracjaStudentaDto {
    @NotBlank(message = "Numer albumu jest wymagany")
    @Size(min = 4, max = 10, message = "Numer albumu musi mieć od 4 do 10 znaków")
    private String nrAlbumu;

    @NotBlank(message = "Imię jest wymagane")
    private String imie;

    @NotBlank(message = "Nazwisko jest wymagane")
    private String nazwisko;

    @NotBlank(message = "Nazwa kierunku jest wymagana")
    private String nazwaKierunku;
}