package aoc.day6;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day6";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            String[] occurrences = new String[0];
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                occurrences = line.split("");
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("occurrences = " + Arrays.toString(occurrences));

            int l = 0;
            Set<String> lookup = new HashSet<>();
            for (int r = 0; r < occurrences.length; r++) {
                String cur = occurrences[r];
                if (r - l == 14) {
                    System.out.println("result: " + r);
                    break;
                }
                if (!lookup.contains(cur)) {
                    lookup.add(cur);
                } else {
                    while (true) {
                        if (occurrences[l].equals(cur)) {
                            l++;
                            break;
                        }
                        lookup.remove(occurrences[l]);
                        l++;
                    }
                }
            }

            if (lookup.size() == 4) {
                System.out.println("wooooo!!!");
            }


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
