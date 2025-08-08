package com.konecta.convertly.model;

public class ConversionResponse {
    private double result;
    private String formula;
    private String status;

    public ConversionResponse(double result, String formula, String status) {
        this.result = result;
        this.formula = formula;
        this.status = status;
    }

    public double getResult() {
        return result;
    }

    public String getFormula() {
        return formula;
    }

    public String getStatus() {
        return status;
    }
}
