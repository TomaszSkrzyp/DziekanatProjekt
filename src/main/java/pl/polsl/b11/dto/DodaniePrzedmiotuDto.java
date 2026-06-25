package pl.polsl.b11.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DodaniePrzedmiotuDto {

    @NotBlank(message = "Nazwa przedmiotu jest wymagana")
    private String nazwaPrzedmiotu;

    @NotBlank(message = "Nazwa kierunku jest wymagana")
    private String nazwaKierunku;

    @NotNull(message = "ID kierownika przedmiotu jest wymagane")
    private Integer idKierownika;
}