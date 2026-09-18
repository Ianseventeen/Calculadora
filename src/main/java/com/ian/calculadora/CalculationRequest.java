package com.ian.calculadora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
// O notBlank e o pattern neste caso estão inúteis,
// por conto do código em JS, eles estão aqui puramente
// para a fase de desenvolvimento, onde eu utilizei para
// aprender mais sobre a API
@Getter
public class CalculationRequest {
    @NotBlank(message = "A expressão matemática não pode estar vazia.")
    @Pattern(
            regexp = "^[0-9+\\-*/.%\\s]+$",
            message = "A expressão contém caracteres ou símbolos inválidos."
    )
    String expression;
}
