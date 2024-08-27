package aoc.day6;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day6";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // numbers 0-8
            long[] state = new long[9];
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
                System.out.println(line);
                long[] finalState = state;
                Arrays.stream(line.split(",")).map(Integer::parseInt).forEach(i -> {
                    finalState[i]++;
                });
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("state = " + Arrays.toString(state));
            System.out.println();


            int days = 256;
            while (days-- > 0) {
                long[] newState = new long[9];
                for (int i = 0; i < state.length; i++) {
                    if (i == 0) {
                        newState[6] += state[i];
                        newState[8] += state[i];
                    } else {
                        newState[i - 1] += state[i];
                    }
                }
                state = newState;
            }
            System.out.println("state = " + Arrays.toString(state));
            System.out.println();
            long sum = Arrays.stream(state).sum();
            System.out.println("sum = " + sum);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
