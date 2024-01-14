package aoc.day3;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day3";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            //east > = + | west < = -
            int horizontalSanta = 0;
            int horizontalRobo = 0;
            //south v = + | north ^ = -
            int verticalSanta = 0;
            int verticalRobo = 0;
            Set<String> visited = new HashSet<>();
            String base = "h" + horizontalSanta + "v" + verticalSanta;
            visited.add(base);
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                String[] split = line.split("");
                for (int i = 0; i < split.length; i++) {
                    String s = split[i];
                    if (i % 2 == 0) {
                        switch (s) {
                            case ">" -> horizontalSanta++;
                            case "<" -> horizontalSanta--;
                            case "v" -> verticalSanta++;
                            default -> verticalSanta--;
                        }
                    } else {
                        switch (s) {
                            case ">" -> horizontalRobo++;
                            case "<" -> horizontalRobo--;
                            case "v" -> verticalRobo++;
                            default -> verticalRobo--;
                        }
                    }
                    String curSanta = "h" + horizontalSanta + "v" + verticalSanta;
                    visited.add(curSanta);
                    String curRobo = "h" + horizontalRobo + "v" + verticalRobo;
                    visited.add(curRobo);
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