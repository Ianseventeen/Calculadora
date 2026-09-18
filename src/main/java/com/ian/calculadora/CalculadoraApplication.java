package com.ian.calculadora;

import com.ian.calculadora.service.CalculationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculadoraApplication {

	 static void main(String[] args) {
		CalculationService calculationService = new CalculationService();
		System.out.println(calculationService.calculate("2+3*4-5/6"));


		SpringApplication.run(CalculadoraApplication.class, args);
	}

}
