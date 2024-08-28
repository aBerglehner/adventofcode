package aoc.day7;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day7";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<Integer> crabs = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                crabs = Arrays.stream(line.split(",")).map(Integer::parseInt).sorted().toList();
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("crabs = " + crabs);
            int len = crabs.size();
            System.out.println("crabs.size() = " + len);
            int mid = len / 2;
            System.out.println("mid = " + mid);
            Integer midCrab = crabs.get(mid);
            System.out.println("midCrab = " + midCrab);

            int res = 0;
            for (Integer crab : crabs) {
                res += Math.abs(midCrab - crab);
            }
            System.out.println("res = " + res);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
