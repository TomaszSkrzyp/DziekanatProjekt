package pl.polsl.b11.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrzypisanieProwadzacegoDto {

    @NotNull(message = "ID prowadzącego jest wymagane")
    private Integer idProwadzacego;
}