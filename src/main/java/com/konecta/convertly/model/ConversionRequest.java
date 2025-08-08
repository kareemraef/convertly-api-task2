package com.konecta.convertly.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.NotBlank;

public class ConversionRequest {
    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "From unit is required")
    private String fromUnit;

    @NotBlank(message = "To unit is required")
    private String toUnit;

    @NotNull(message = "Value is required")
    @PositiveOrZero(message = "Value must be zero or positive")
    private Double value;

    public ConversionRequest() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
