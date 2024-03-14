package com.fisagroup;

import com.fisagroup.merchants.GalacticConverter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GalacticConverterTest {

    public static final Map<String, Integer> UNIT_VALUES = new HashMap<>();
    @Test
    void processLineTest() {
        // Mocking System.out for capturing output
        System.setOut(new java.io.PrintStream(System.out) {
            @Override
            public void println(String message) {
                // Do nothing to suppress output during test
            }
        });

        GalacticConverter galacticConverter = Mockito.spy(new GalacticConverter());

        // Test for unit to Roman numeral conversion
        String unitToNumeralLine = "glob is I";
        galacticConverter.processLine(unitToNumeralLine);
        verify(galacticConverter, times(1)).initializeConversionMaps();
        assertEquals(1, GalacticConverter.UNIT_VALUES.get("glob"));

        // Test for Credits calculation
        String creditsCalculationLine = "how many Credits is glob prok Silver ?";
        galacticConverter.processLine(creditsCalculationLine);
        String[] tokens = {"glob", "prok", "Silver"};
        assertEquals(578, GalacticConverter.calculateResult(tokens), 0.01);
    }

    @Test
    void calculateResultTest() {
        GalacticConverter galacticConverter = new GalacticConverter();
        String[] tokens = {"glob", "prok", "Silver"};
        assertEquals(578, galacticConverter.calculateResult(tokens), 0.01);
    }
}
