package aoc.day10;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day10";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            Map<Integer, Integer> lookup = new HashMap<>();
            int[] register = new int[]{20, 60, 100, 140, 180, 220};
            String line;
            int cycle = 0;
            int x = 1;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                if (line.contains("noop")) {
                    cycle++;
                    setLookup(register, cycle, lookup, x);
                } else {
                    cycle++;
                    int num = Integer.parseInt(line.split(" ")[1]);
                    setLookup(register, cycle, lookup, x);
                    cycle++;
                    setLookup(register, cycle, lookup, x);
                    x += num;
                }
//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("lookup = " + lookup);
            Integer res = lookup.entrySet().stream().reduce(0, (acc, cur) -> acc + cur.getKey() * cur.getValue(), Integer::sum);
            System.out.println("res = " + res);


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static void setLookup(int[] register, int cycle, Map<Integer, Integer> lookup, int x) {
        for (int j : register) {
            if (j == cycle) {
                lookup.put(cycle, x);
            }
        }
    }
}
