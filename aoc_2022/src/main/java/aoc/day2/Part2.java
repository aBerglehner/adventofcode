package aoc.day2;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {
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

//            own hand says if loose or win:
//            X means lose
//            Y means draw
//            Z means win

//            points per indiviuall play:
//            rock gives still 1 point
//            paper gives 2 points
//            scissors gives 3 points
            Map<String, Integer> points = new HashMap<>();
            points.put("X", 0);
            points.put("Y", 3);
            points.put("Z", 6);
            Map<String, Integer> resultLookup = new HashMap<>();
            resultLookup.put("AX", 3);
            resultLookup.put("AY", 1);
            resultLookup.put("AZ", 2);
            resultLookup.put("BX", 1);
            resultLookup.put("BY", 2);
            resultLookup.put("BZ", 3);
            resultLookup.put("CX", 2);
            resultLookup.put("CY", 3);
            resultLookup.put("CZ", 1);

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

                result.add(p + lookupPoints);

                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("result = " + result);
            Integer reduce = result.stream().reduce(0, Integer::sum);
            System.out.println("reduce = " + reduce);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
