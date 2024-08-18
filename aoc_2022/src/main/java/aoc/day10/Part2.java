package aoc.day10;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) throws Exception {
        String day = "day10";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<String>> matrix = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                List<String> row = new ArrayList<>();
                for (int j = 0; j < 40; j++) {
                    row.add(".");
                }
                matrix.add(row);
            }
            Helper.prettyPrintList(matrix);

            int row = 0;
            int cycle = 0;
            int spritePos = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                if (line.contains("noop")) {
                    cycle++;
                    int curCol = cycle - 1;
                    if (curCol >= spritePos - 1 && curCol <= spritePos + 1) {
                        matrix.get(row).set(curCol, "#");
                    }
                    if (cycle == 40) {
                        cycle = 0;
                        row++;
                    }
                } else {
                    cycle++;
                    int curCol = cycle - 1;
                    if (curCol >= spritePos - 1 && curCol <= spritePos + 1) {
                        matrix.get(row).set(curCol, "#");
                    }
                    if (cycle == 40) {
                        cycle = 0;
                        row++;
                    }

                    cycle++;
                    curCol = cycle - 1;
                    if (curCol >= spritePos - 1 && curCol <= spritePos + 1) {
                        matrix.get(row).set(curCol, "#");
                    }
                    if (cycle == 40) {
                        cycle = 0;
                        row++;
                    }

                    int num = Integer.parseInt(line.split(" ")[1]);
                    spritePos += num;
                }
//                    System.out.println("---------------------");
            }
            System.out.println();
            Helper.prettyPrintList(matrix);
            for (int curRow = 0; curRow < matrix.size(); curRow++) {
                for (int curCol = 0; curCol < matrix.get(0).size(); curCol++) {
                    if (matrix.get(curRow).get(curCol).equals(".")) {
                        matrix.get(curRow).set(curCol, " ");
                    } else {
                        matrix.get(curRow).set(curCol, "#");
                    }
                }
                String collect = matrix.get(curRow).stream().collect(Collectors.joining(""));
                System.out.println(collect);
            }
            System.out.println();
            Helper.prettyPrintList(matrix);


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
