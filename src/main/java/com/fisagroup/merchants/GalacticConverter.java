package com.fisagroup.merchants;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GalacticConverter {
    private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>();
    private static final Map<String, Integer> UNIT_VALUES = new HashMap<>();
    private static final Map<String, Double> CREDITS_PER_UNIT = new HashMap<>();

    public static void main(String[] args) {
        initializeConversionMaps();

        String[] input = {
                "glob is I",
                "prok is V",
                "pish is X",
                "tegj is L",
                "glob glob Silver is 34 Credits",
                "glob prok Gold is 57800 Credits",
                "pish pish Iron is 3910 Credits",
                "how much is pish tegj glob glob ?",
                "how many Credits is glob prok Silver ?",
                "how many Credits is glob prok Gold ?",
                "how many Credits is glob prok Iron ?",
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
        };

        for (String line : input) {
            processLine(line);
        }
    }

    private static void initializeConversionMaps() {
        ROMAN_NUMERALS.put("I", 1);
        ROMAN_NUMERALS.put("V", 5);
        ROMAN_NUMERALS.put("X", 10);
        ROMAN_NUMERALS.put("L", 50);
        ROMAN_NUMERALS.put("C", 100);
        ROMAN_NUMERALS.put("D", 500);
        ROMAN_NUMERALS.put("M", 1000);

        UNIT_VALUES.put("glob", 1);
        UNIT_VALUES.put("prok", 5);
        UNIT_VALUES.put("pish", 10);
        UNIT_VALUES.put("tegj", 50);

        CREDITS_PER_UNIT.put("Silver", 17.0);
        CREDITS_PER_UNIT.put("Gold", 14450.0);
        CREDITS_PER_UNIT.put("Iron", 195.5);
    }

    private static void processLine(String line) {
        line = line.trim();  // Eliminar espacios al principio y al final
        String[] parts = line.split(" is ");

        if (parts.length == 2) {
            // Handle unit to Roman numeral conversion
            String unit = parts[0].trim();
            String numeral = parts[1].trim();

            if (ROMAN_NUMERALS.containsKey(numeral)) {
                UNIT_VALUES.put(unit, ROMAN_NUMERALS.get(numeral));
            } else {
                System.out.println(line);
            }
        } else {
            // Handle Credits calculation
            Pattern pattern = Pattern.compile("(how much is|how many Credits is) (.+) \\?");
            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {
                String questionType = matcher.group(1);
                String tokensString = matcher.group(2).trim();

                // Manejar el caso especial de consultas que no tienen tokens
                if (tokensString.isEmpty()) {
                    System.out.println("I have no idea what you are talking about");
                    return;
                }

                String[] tokens = tokensString.split(" ");
                double result = calculateResult(tokens);
                if (result >= 0) {
                    String output = tokensToString(tokens) + " is " + (int) result +
                            (questionType.equals("how many Credits is") ? " Credits" : "");
                    System.out.println(output);
                } else {
                    System.out.println("I have no idea what you are talking about");
                }
            } else {
                // Handling unknown queries
                if (line.endsWith("?")) {
                    System.out.println("I have no idea what you are talking about");
                } else {
                    System.out.println(line);
                }
            }
        }
    }

    private static double calculateResult(String[] tokens) {
        int result = 0;
        double creditsMultiplier = 1;

        for (String token : tokens) {
            if (UNIT_VALUES.containsKey(token)) {
                result += UNIT_VALUES.get(token);
            } else if (CREDITS_PER_UNIT.containsKey(token)) {
                creditsMultiplier *= CREDITS_PER_UNIT.get(token);
            } else {
                return -1;
            }
        }

        return result * creditsMultiplier;
    }

    private static String tokensToString(String[] tokens) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : tokens) {
            stringBuilder.append(token).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}