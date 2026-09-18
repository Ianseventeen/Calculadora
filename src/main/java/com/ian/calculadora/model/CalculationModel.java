package com.ian.calculadora.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CalculationModel {
    double number1;
    String operator;
    double number2;
}
