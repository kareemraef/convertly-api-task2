package com.konecta.convertly.service;

import com.konecta.convertly.enums.Category;
import com.konecta.convertly.enums.LengthUnit;
import com.konecta.convertly.enums.TemperatureUnit;
import com.konecta.convertly.enums.TimeUnit;
import com.konecta.convertly.enums.WeightUnit;
import com.konecta.convertly.model.ConversionRequest;
import com.konecta.convertly.model.ConversionResponse;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversionService {

    public List<String> getAllCategories() {
        return Arrays.stream(Category.values())
                .map(Enum::name)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public List<String> getUnitsByCategory(String categoryName) {
        String category = categoryName.toUpperCase();

        switch (category) {
            case "TEMPERATURE":
                return Arrays.stream(TemperatureUnit.values())
                        .map(Enum::name)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList());

            // Placeholder for future categories
            case "LENGTH":
                return Arrays.stream(LengthUnit.values())
                        .map(Enum::name)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList());
            case "WEIGHT":
                return Arrays.stream(WeightUnit.values())
                        .map(Enum::name)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList());
            case "TIME":
                return Arrays.stream(TimeUnit.values())
                        .map(Enum::name)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Unsupported category: " + categoryName);
        }
    }

    public ConversionRequest getSamplePayload() {
        ConversionRequest sample = new ConversionRequest();
        sample.setCategory("temperature");
        sample.setFromUnit("celsius");
        sample.setToUnit("fahrenheit");
        sample.setValue(25);
        return sample;
    }

    public ConversionResponse convert(ConversionRequest request) {
        String category = request.getCategory().toLowerCase();

        if (category.equals("temperature")) {
            return convertTemperature(
                    request.getFromUnit().toLowerCase(),
                    request.getToUnit().toLowerCase(),
                    request.getValue());
        }
        if (category.equals("length")) {
            return convertLength(
                    request.getFromUnit().toLowerCase(),
                    request.getToUnit().toLowerCase(),
                    request.getValue());
        }
        if (category.equals("weight")) {
            return convertWeight(
                    request.getFromUnit().toLowerCase(),
                    request.getToUnit().toLowerCase(),
                    request.getValue());
        }

        if (category.equals("time")) {
            return convertTime(
                    request.getFromUnit().toLowerCase(),
                    request.getToUnit().toLowerCase(),
                    request.getValue());
        }

        return new ConversionResponse(0, "Unsupported category", "failed");
    }

    private ConversionResponse convertTemperature(String from, String to, double value) {
        double result = value;
        String formula = "";

        if (from.equals(to)) {
            formula = "Same units, no conversion";
        } else if (from.equals("celsius") && to.equals("fahrenheit")) {
            result = (value * 9 / 5) + 32;
            formula = "(" + value + "°C × 9/5) + 32 = " + result + "°F";
        } else if (from.equals("fahrenheit") && to.equals("celsius")) {
            result = (value - 32) * 5 / 9;
            formula = "(" + value + "°F − 32) × 5/9 = " + result + "°C";
        } else if (from.equals("celsius") && to.equals("kelvin")) {
            result = value + 273.15;
            formula = value + "°C + 273.15 = " + result + "K";
        } else if (from.equals("kelvin") && to.equals("celsius")) {
            result = value - 273.15;
            formula = value + "K - 273.15 = " + result + "°C";
        } else if (from.equals("fahrenheit") && to.equals("kelvin")) {
            result = (value - 32) * 5 / 9 + 273.15;
            formula = "(" + value + "°F − 32) × 5/9 + 273.15 = " + result + "K";
        } else if (from.equals("kelvin") && to.equals("fahrenheit")) {
            result = (value - 273.15) * 9 / 5 + 32;
            formula = "(" + value + "K - 273.15) × 9/5 + 32 = " + result + "°F";
        } else {
            return new ConversionResponse(0, "Unsupported temperature unit", "failed");
        }

        return new ConversionResponse(result, formula, "success");
    }

    private ConversionResponse convertLength(String from, String to, double value) {
        double result = value;
        String formula;

        // Convert everything to meters first
        double inMeters;

        switch (from) {
            case "meter":
                inMeters = value;
                break;
            case "kilometer":
                inMeters = value * 1000;
                break;
            case "mile":
                inMeters = value * 1609.34;
                break;
            case "inch":
                inMeters = value * 0.0254;
                break;
            case "foot":
                inMeters = value * 0.3048;
                break;
            default:
                return new ConversionResponse(0, "Unsupported length unit", "failed");
        }

        switch (to) {
            case "meter":
                result = inMeters;
                break;
            case "kilometer":
                result = inMeters / 1000;
                break;
            case "mile":
                result = inMeters / 1609.34;
                break;
            case "inch":
                result = inMeters / 0.0254;
                break;
            case "foot":
                result = inMeters / 0.3048;
                break;
            default:
                return new ConversionResponse(0, "Unsupported length unit", "failed");
        }

        formula = String.format("%f %s = %f %s", value, from, result, to);
        return new ConversionResponse(result, formula, "success");
    }

    private ConversionResponse convertWeight(String from, String to, double value) {
        double inGrams;

        switch (from) {
            case "gram":
                inGrams = value;
                break;
            case "kilogram":
                inGrams = value * 1000;
                break;
            case "pound":
                inGrams = value * 453.592;
                break;
            case "ounce":
                inGrams = value * 28.3495;
                break;
            default:
                return new ConversionResponse(0, "Unsupported weight unit", "failed");
        }

        double result;

        switch (to) {
            case "gram":
                result = inGrams;
                break;
            case "kilogram":
                result = inGrams / 1000;
                break;
            case "pound":
                result = inGrams / 453.592;
                break;
            case "ounce":
                result = inGrams / 28.3495;
                break;
            default:
                return new ConversionResponse(0, "Unsupported weight unit", "failed");
        }

        String formula = String.format("%f %s = %f %s", value, from, result, to);
        return new ConversionResponse(result, formula, "success");
    }

    private ConversionResponse convertTime(String from, String to, double value) {
        double inSeconds;

        switch (from) {
            case "second":
                inSeconds = value;
                break;
            case "minute":
                inSeconds = value * 60;
                break;
            case "hour":
                inSeconds = value * 3600;
                break;
            case "day":
                inSeconds = value * 86400;
                break;
            default:
                return new ConversionResponse(0, "Unsupported time unit", "failed");
        }

        double result;

        switch (to) {
            case "second":
                result = inSeconds;
                break;
            case "minute":
                result = inSeconds / 60;
                break;
            case "hour":
                result = inSeconds / 3600;
                break;
            case "day":
                result = inSeconds / 86400;
                break;
            default:
                return new ConversionResponse(0, "Unsupported time unit", "failed");
        }

        String formula = String.format("%f %s = %f %s", value, from, result, to);
        return new ConversionResponse(result, formula, "success");
    }

}
