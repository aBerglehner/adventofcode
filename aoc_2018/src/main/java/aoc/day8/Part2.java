package aoc.day8;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day8";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int totalsum = 0;
            int memSum = 0;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                totalsum += line.length();

                String[] split = line.split("");
                List<String> resMem = new ArrayList<>();
                // prefix \ with \
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals("\\")) {
                        resMem.add("\\");
                        resMem.add("\\");
                    } else {
                        resMem.add(split[i]);
                    }
                }
                // prefix " with \
                // count the extra symbols in sum and add it later
                int sum = 0;
                for (String s : resMem) {
                    if (s.equals("\"")) {
                        sum++;
                    }
                }
//                System.out.println("resMem = " + resMem);
//                System.out.println("resMem.size() = " + resMem.size());

                // add 2 for ""
                memSum += resMem.size() + sum + 2;
//                System.out.println();


//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("totalsum = " + totalsum);
            System.out.println("memSum = " + memSum);
            int res = memSum - totalsum;
            System.out.println("res = " + res);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}