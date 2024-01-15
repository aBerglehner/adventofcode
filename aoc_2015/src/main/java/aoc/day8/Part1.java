package aoc.day8;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
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

                String mem = line.substring(1, line.length() - 1);
                if (mem.isEmpty()) {
                    continue;
                }
                String[] split = mem.split("");
                List<String> resMem = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equals("\\")) {
                        if (split[i + 1].equals("x")) {
                            resMem.add(split[i + 1]);
                            i += 3;
                        } else {
                            resMem.add(split[i + 1]);
                            i++;
                        }
                    } else {
                        resMem.add(split[i]);
                    }
                }
//                System.out.println("resMem = " + resMem);
//                System.out.println("resMem.size() = " + resMem.size());
                memSum += resMem.size();
//                System.out.println();


//                    System.out.println("---------------------");
            }
            System.out.println();
            System.out.println("totalsum = " + totalsum);
            System.out.println("memSum = " + memSum);
            int res = totalsum - memSum;
            System.out.println("res = " + res);

            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }
}
// trim first and last = substring
// split("")
// \x23 backspace and x +2 = 1
// \ something else = 1
