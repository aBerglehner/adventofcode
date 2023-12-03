package aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws Exception {
        String day = "day1";
        InputStream inputStream = Day1.class.getResourceAsStream("../" + day + "/test.txt");
//        InputStream inputStream = Day1.class.getResourceAsStream("../" + day + "/input.txt");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);

//                    System.out.println("---------------------");
            }
            System.out.println();


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
