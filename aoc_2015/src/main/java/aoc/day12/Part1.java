package aoc.day12;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day12";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                String removedBrackets = line.replaceAll("\\[|\\]|:|\\}|\\{|,", " ");
                System.out.println("removedBrackets = " + removedBrackets);
                String replacedString = removedBrackets.replaceAll("\"[^\"]*\"", " ");
                System.out.println("replacedString = " + replacedString);
                String removedWhiteSpace = replacedString.replaceAll("\\s+", " ");
                System.out.println("removedWhiteSpace = " + removedWhiteSpace);

                int sum = Arrays.stream(removedWhiteSpace.split(" ")).filter(e -> !e.isBlank()).mapToInt(Integer::parseInt).sum();
                System.out.println("sum = " + sum);
                System.out.println("---------------------");
            }
//            System.out.println();


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
