package aoc.day15;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day15";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                Integer result = Arrays.stream(line.split(","))
                        .filter(e -> !e.isBlank())
                        .map(e -> getHash(e))
                        .reduce(Integer::sum)
                        .orElse(-1);
                System.out.println("result = " + result);
//                    System.out.println("---------------------");
            }
//            System.out.println();


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static int getHash(String str) {
        int sum = 0;
        for (char c : str.toCharArray()) {
            int valueC = c;
            sum += valueC;
            sum *= 17;
            sum = sum % 256;
        }
        return sum;
    }
}
