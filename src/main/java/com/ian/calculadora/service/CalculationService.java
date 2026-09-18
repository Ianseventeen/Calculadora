package com.ian.calculadora.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class CalculationService {
    public String calculate(String expression) {
        String cleanExpression = expression.replaceAll("\\s+", "");
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(cleanExpression.split("(?<=[+\\-*/])|(?=[+\\-*/])")));

        tratarSinaisNegativos(tokens);
        while (tokens.size() > 1) {
            int findIndex = findIndexOfOperator(tokens);
            if (findIndex == -1) {
                break;
            }
            double resultado = getResultado(tokens ,findIndex);
            tokens.set(findIndex-1, String.valueOf(resultado));
            tokens.remove(findIndex+1);
            tokens.remove(findIndex);
        }
        System.out.println(tokens);
        return tokens.isEmpty() ? "0" :  tokens.getFirst();
    }

    private static double getResultado(ArrayList<String> tokens, int operatorIndex) {
        double number1 = Double.parseDouble(tokens.get(operatorIndex - 1));
        String operator = tokens.get(operatorIndex);
        double number2 = Double.parseDouble(tokens.get(operatorIndex + 1));
        return switch (operator) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> {
                if (number2 == 0) {
                    throw new IllegalArgumentException("Divisão por zero não é permitida.");
                }
                yield number1 / number2;
            }
            default -> throw new IllegalArgumentException("Operador inválido: " + operator);
        };
    }

    public int findIndexOfOperator(ArrayList<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("*") || token.equals("/")) {
                return i;
            }
        }
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("+") || token.equals("-")) {
                return i;
            }
        }
        return -1;
    }

    private void tratarSinaisNegativos(ArrayList<String> tokens) {
        // Caso 1: O primeiro token é '-' (ex: ["-", "5", "+", "2"])
        if (!tokens.isEmpty() && tokens.get(0).equals("-") && tokens.size() > 1) {
            tokens.set(0, "-" + tokens.get(1)); // Junta "-" com "5" -> "-5"
            tokens.remove(1);                   // Remove o "5" duplicado
        }

        // Caso 2: Operador seguido de '-' (ex: ["5", "-", "-", "2"] ou ["5", "*", "-", "2"])
        for (int i = 0; i < tokens.size() - 2; i++) {
            String tokenAtual = tokens.get(i);
            String proximoToken = tokens.get(i + 1);

            if (isOperator(tokenAtual) && proximoToken.equals("-")) {
                String numeroNegativo = "-" + tokens.get(i + 2); // Junta "-" com "2" -> "-2"
                tokens.set(i + 1, numeroNegativo);               // Substitui o segundo '-' por '-2'
                tokens.remove(i + 2);                            // Remove o '2' sobressalente
            }
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

}
