package com.ian.calculadora.controller;

import com.ian.calculadora.CalculationRequest;
import com.ian.calculadora.service.CalculationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculations")
public class CalculationController {
    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    public String result(@RequestBody CalculationRequest request){
        return calculationService.calculate(request.getExpression());
    }

}
