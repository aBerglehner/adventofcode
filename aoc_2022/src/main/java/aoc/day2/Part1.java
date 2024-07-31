package aoc.day2;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day2";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


//            enemy hand first card:
//            A is Rock
//            B is Paper
//            C is Scissor
//            own hand second card:
//          X is Rock
//          Y is Paper
//          Z is Scissor
            Map<String, Integer> points = new HashMap<>();
            points.put("X", 1);
            points.put("Y", 2);
            points.put("Z", 3);
            Map<String, Integer> resultLookup = new HashMap<>();
            resultLookup.put("AX", 3);
            resultLookup.put("AY", 6);
            resultLookup.put("AZ", 0);
            resultLookup.put("BX", 0);
            resultLookup.put("BY", 3);
            resultLookup.put("BZ", 6);
            resultLookup.put("CX", 6);
            resultLookup.put("CY", 0);
            resultLookup.put("CZ", 3);

            String line;
            List<Integer> result = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                String joinedString = Arrays.stream(line.split("")).filter(e -> !e.isBlank()).collect(Collectors.joining());
                System.out.println("joinedString = " + joinedString);
                Integer lookupPoints = resultLookup.get(joinedString);
                System.out.println("lookupPoints = " + lookupPoints);
                String lastChar = String.valueOf(line.charAt(line.length() - 1));

                System.out.println("lastChar = " + lastChar);
                Integer p = points.get(lastChar);
                System.out.println("points = " + p);
                result.add(lookupPoints + p);
                System.out.println("---------------------");
            }
//            System.out.println();
            System.out.println("result = " + result);
            Integer reduce = result.stream().reduce(0, Integer::sum);
            System.out.println("reduce = " + reduce);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
