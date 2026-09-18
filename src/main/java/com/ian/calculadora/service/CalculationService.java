package com.ian.calculadora.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class CalculationService {
    public String calculate(String expression) {
        String cleanExpression = expression.replaceAll("\\s+", "");
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(cleanExpression.split("(?<=[+\\-*/])|(?=[+\\-*/])")));
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
            case "/" -> number1 / number2;
            default -> 0;
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

}
