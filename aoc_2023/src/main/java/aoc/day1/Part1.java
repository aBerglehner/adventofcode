package aoc.day1;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day1";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            List<Integer> digits = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                String s = line.replaceAll("\\D", "");
                String first = String.valueOf(s.charAt(0));
                String last = String.valueOf(s.charAt(s.length() - 1));
                Integer digit = Integer.valueOf(first + last);
//                System.out.println("digit: " + digit);
//                System.out.println();
                digits.add(digit);

//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println(digits);
            System.out.println();
            int sum = digits.stream().reduce(0, Integer::sum);
            System.out.println(sum);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

}
