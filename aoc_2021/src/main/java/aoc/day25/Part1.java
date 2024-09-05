package aoc.day25;

import aoc.helper.Helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws Exception {
        String day = "day25";
        InputStream inputStream = Helper.iStream(day, "input");

        if (inputStream != null) {
            // Wrap the InputStream in a BufferedReader to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<List<String>> matrix = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the file
//                System.out.println(line);
                matrix.add(Arrays.stream(line.split("")).toList());
//                    System.out.println("---------------------");
            }
            System.out.println();
            //east >  will move first
            //south v

            int rows = matrix.size();
//            System.out.println("rows = " + rows);
            int cols = matrix.get(0).size();
            Set<String> eastSet = getSet(rows, cols, matrix, ">");
            Set<String> southSet = getSet(rows, cols, matrix, "v");
//            System.out.println("eastSet = " + eastSet);
//            System.out.println("southSet = " + southSet);
//            Helper.prettyPrintList(matrix);

            int count = 600;
            boolean moved = false;
            int rounds = 0;
            while (--count >= 0) {
                //to know -> east move row+1 | south move col+1

                moved = false;
                Set<String> newEastSet = new HashSet<>();
                for (String east : eastSet) {
                    String[] split = east.split("-");
                    String col = split[1];
                    String newCol = String.valueOf(Integer.parseInt(col) + 1);
                    newCol = Integer.parseInt(newCol) >= cols ? "0" : newCol;
                    String row = split[0];
                    String newEast = row + "-" + newCol;
                    if (eastSet.contains(newEast) || southSet.contains(newEast)) {
                        newEastSet.add(east);
                    } else {
                        moved = true;
                        newEastSet.add(newEast);
                    }
                }
                eastSet = newEastSet;

                Set<String> newSouthSet = new HashSet<>();
                for (String south : southSet) {
                    String[] split = south.split("-");
                    String row = split[0];
                    String newRow = String.valueOf(Integer.parseInt(row) + 1);
                    newRow = Integer.parseInt(newRow) >= rows ? "0" : newRow;
                    String col = split[1];
                    String newSouth = newRow + "-" + col;
                    if (eastSet.contains(newSouth) || southSet.contains(newSouth)) {
                        newSouthSet.add(south);
                    } else {
                        moved = true;
                        newSouthSet.add(newSouth);
                    }
                }
                southSet = newSouthSet;
                rounds++;
                if (moved == false) {
                    System.out.println("rounds = " + rounds);
                    break;
                }

//                System.out.println();
//                System.out.println("rounds = " + rounds);
//                System.out.println("eastSet = " + eastSet);
//                System.out.println("southSet = " + southSet);
//                Helper.prettyPrintList(drawNewMatrix(eastSet, southSet, rows, cols));
            }


            reader.close();
        } else {
            System.out.println("No input file not found.");
        }
    }

    private static List<List<String>> drawNewMatrix(Set<String> eastSet, Set<String> southSet, int rows, int cols) {
        List<List<String>> matrix = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                matrix.get(row).add(".");
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String sync = row + "-" + col;
                if (eastSet.contains(sync)) {
                    matrix.get(row).set(col, ">");
                } else if (southSet.contains(sync)) {
                    matrix.get(row).set(col, "v");
                }
            }
        }
        return matrix;
    }

    private static Set<String> getSet(int rows, int cols, List<List<String>> matrix, String searchValue) {
        Set<String> result = new HashSet<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String cur = matrix.get(row).get(col);
                if (cur.equals(searchValue)) {
                    result.add(row + "-" + col);
                }
            }
        }
        return result;
    }
}
