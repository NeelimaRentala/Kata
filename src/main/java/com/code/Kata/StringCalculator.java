package com.code.Kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public static final Integer add(String numberString) {

        String regex = "[,\n]";
        String numbersList = null;

        if (numberString.startsWith("//")) {
            String delimeterWithBraces = numberString.split("\n")[0].split("//")[1];
            numbersList = numberString.split("\n")[1];
            List<String> delimeter = delimeterWithBraces.chars().mapToObj(x -> (char) x).filter(x -> x != '[').filter(x -> x != ']').map(x -> String.valueOf(x)).collect(Collectors.toList());
            regex = "[" + String.join("", delimeter) + ",\n]";
        } else {
            numbersList = numberString;
        }

        ArrayList<Integer> negativeNumberList = new ArrayList<>();
        Arrays.stream(numbersList.split(regex)).filter(x -> !x.equals("")).map(x -> Integer.valueOf(x)).filter(x -> x < 0).forEach(negInteger -> negativeNumberList.add(negInteger));
        if (negativeNumberList.size() > 0) {
            throw new RuntimeException("Negatives not allowed: " + negativeNumberList);
        }
        return Arrays.stream(numbersList.split(regex)).filter(x -> !x.equals("")).map(x -> Integer.valueOf(x)).filter(x -> x <= 1000).reduce(0, (subtotal, element) -> subtotal + element);
    }
}
