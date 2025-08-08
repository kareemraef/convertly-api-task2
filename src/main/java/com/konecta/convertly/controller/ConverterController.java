package com.konecta.convertly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.konecta.convertly.model.ConversionRequest;
import com.konecta.convertly.model.ConversionResponse;
import com.konecta.convertly.service.ConversionService;

import jakarta.validation.Valid;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@RestController
public class ConverterController {

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        return Map.of("status", "Unit Converter API is up and running");
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return conversionService.getAllCategories();
    }

    @GetMapping("/units")
    public List<String> getUnitsByCategory(@RequestParam String category) {
        return conversionService.getUnitsByCategory(category);
    }

    @GetMapping("/sample-payload")
    public ConversionRequest getSamplePayload() {
        return conversionService.getSamplePayload();
    }

    @PostMapping("/convert")
    public ConversionResponse convert(@Valid @RequestBody ConversionRequest request) {
        return conversionService.convert(request);
    }

}
