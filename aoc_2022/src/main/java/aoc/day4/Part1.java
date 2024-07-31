package aoc.day4;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day4";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            int counter = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                String[] pairs = line.split(",");
                String[] firstPair = pairs[0].split("-");
//                System.out.println("firstPair = " + Arrays.toString(firstPair));
                int lowFirstPair = Integer.parseInt(firstPair[0]);
                int highFirstPair = Integer.parseInt(firstPair[1]);


                String[] secondPair = pairs[1].split("-");
//                System.out.println("secondPair = " + Arrays.toString(secondPair));
                int lowSecondPair = Integer.parseInt(secondPair[0]);
                int highSecondPair = Integer.parseInt(secondPair[1]);

                boolean firstIsIncluder = lowFirstPair <= lowSecondPair && highFirstPair >= highSecondPair;
                boolean secondIsIncluder = lowSecondPair <= lowFirstPair && highSecondPair >= highFirstPair;
                if (firstIsIncluder || secondIsIncluder) {
                    counter++;
                }

//                System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("counter = " + counter);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
