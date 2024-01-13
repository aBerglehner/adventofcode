package aoc.day1;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day1";
        InputStream inputStream = Helper.iStream(day, "in");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int sum = 0;
            int i = 1;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                for (String s : line.split("")) {
                    if (s.equals("(")) {
                        ++sum;
                    } else {
                        --sum;
                    }
                    if (sum == -1) {
                        System.out.println("result: " + i);
                        break;
                    }
                    ++i;
                }
//                    System.out.println("---------------------");
            }
            System.out.println();


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
