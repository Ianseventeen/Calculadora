package com.ian.calculadora.controller;

import com.ian.calculadora.CalculationRequest;
import com.ian.calculadora.service.CalculationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculations")
@CrossOrigin(origins = "*")
public class CalculationController {
    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    public String result(@Valid @RequestBody CalculationRequest request){
        return calculationService.calculate(request.getExpression());
    }

}
