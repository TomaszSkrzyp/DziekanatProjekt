package pl.polsl.b11.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DodanieKierunkuDto {

    @NotBlank(message = "Nazwa kierunku jest wymagana")
    private String nazwaKierunku;
}