package aoc.day3;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day3";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            //east > = + | west < = -
            int horizontal = 0;
            //south v = + | north ^ = -
            int vertical = 0;
            Set<String> visited = new HashSet<>();
            String base = "h" + horizontal + "v" + vertical;
            visited.add(base);
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                for (String s : line.split("")) {
                    switch (s) {
                        case ">" -> horizontal++;
                        case "<" -> horizontal--;
                        case "v" -> vertical++;
                        default -> vertical--;
                    }
                    String cur = "h" + horizontal + "v" + vertical;
                    visited.add(cur);
                }
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("visited = " + visited);
            System.out.println("visited.size() = " + visited.size());


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}

//2591 too low