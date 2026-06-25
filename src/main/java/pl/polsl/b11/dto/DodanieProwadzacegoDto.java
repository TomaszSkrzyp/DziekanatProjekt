package pl.polsl.b11.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DodanieProwadzacegoDto {

    @NotBlank(message = "Imię prowadzącego jest wymagane")
    private String imie;

    @NotBlank(message = "Nazwisko prowadzącego jest wymagane")
    private String nazwisko;
}