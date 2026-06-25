package pl.polsl.b11.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KorektaOcenyDto {

    @NotNull(message = "Wartość oceny jest wymagana")
    @Min(value = 2, message = "Ocena nie może być niższa niż 2.0")
    @Max(value = 5, message = "Ocena nie może być wyższa niż 5.0")
    private Float nowaWartosc;
}